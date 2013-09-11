package com.aliyun.common.comm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NoHttpResponseException;

import com.aliyun.common.parser.ResultParseException;
import com.aliyun.common.parser.ResultParser;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.common.utils.HttpUtil;
import com.aliyun.common.utils.ResourceManager;
import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ServiceException;

public abstract class ServiceClient
{
  private static final int DEFAULT_RETRY_PAUSE_SCALE = 300;
  private static final int DEFAULT_MARK_LIMIT = 4096;
  
  public static class Request
    extends HttpMesssage
  {
    private String uri;
    private HttpMethod method;
    
    public String getUri()
    {
      return this.uri;
    }
    
    public void setUrl(String uri) {
      this.uri = uri;
    }
    
    public HttpMethod getMethod()
    {
      return this.method;
    }
    
    public void setMethod(HttpMethod method)
    {
      this.method = method;
    }
  }
  
  private static final Log log = LogFactory.getLog(ServiceClient.class);
  
  private static ResourceManager rm = ResourceManager.getInstance("common");
  private ClientConfiguration config;
  
  protected ServiceClient(ClientConfiguration config)
  {
    this.config = config;
  }
  
  public <T> T sendRequest(RequestMessage request, ExecutionContext context, ResultParser parser)
    throws ServiceException, ClientException
  {
    CodingUtils.assertParameterNotNull(parser, "parser");
    
    ResponseMessage response = sendRequest(request, context);
    try
    {
      handleResponse(response, context.getResponseHandlers());
      return (T) parser.getObject(response);
    } catch (ResultParseException e) {
      throw new ClientException(rm.getString("FailedToParseResponse"), e);
    }
    finally {
      try {
        response.close();
      }
      catch (IOException e) {}
    }
  }
  
  public ResponseMessage sendRequest(RequestMessage request, ExecutionContext context)
    throws ServiceException, ClientException
  {
    CodingUtils.assertParameterNotNull(request, "request");
    CodingUtils.assertParameterNotNull(context, "context");
    try
    {
      return sendRequestImpl(request, context);
    }
    finally {
      try {
        request.close();
      }
      catch (IOException e) {}
    }
  }
  
  private ResponseMessage sendRequestImpl(RequestMessage request, ExecutionContext context)
    throws ClientException, ServiceException
  {
    if (context.getSigner() != null) {
      context.getSigner().sign(request);
    }
    
    int retries = 0;
    ResponseMessage response = null;
    
    InputStream content = request.getContent();
    
    if ((content != null) && (content.markSupported())) {
      content.mark(4096);
    }
    for (;;)
    {
      try {
        if (retries > 0) {
          pause(retries);
          if ((content != null) && (content.markSupported())) {
            content.reset();
          }
        }
        
        response = null;
        
        response = sendRequestCore(buildRequest(request, context), context);
        
        handleResponse(response, context.getResponseHandlers());
        return response;
      } catch (ServiceException e) {
        log.debug("Unable to execute the request due a service error: " + e.getMessage());
        
        closeResponseSilently(response);
        
        if (!shouldRetry(request, e, response != null ? response.getStatusCode() : 0, retries)) {
          throw e;
        }
      } catch (Exception e) {
        log.debug("Unable to execute the request due to exception: [" + e.getClass().getName() + "] " + e.getMessage());
        
        closeResponseSilently(response);
        
        if (!shouldRetry(request, e, 0, retries))
          throw new ClientException(rm.getFormattedString("ConnectionError", new Object[] { e.getMessage() }), e);
      } finally {
        retries++;
      }
    }
  }
  
  protected abstract ResponseMessage sendRequestCore(Request paramRequest, ExecutionContext paramExecutionContext)
    throws Exception;
  
  private Request buildRequest(RequestMessage requestMessage, ExecutionContext context)
    throws ClientException
  {
    Request request = new Request();
    request.setMethod(requestMessage.getMethod());
    request.setHeaders(requestMessage.getHeaders());
    
    if (request.getHeaders() != null) {
      HttpUtil.convertHeaderCharsetToIso88591(request.getHeaders());
    }
    
    String delimiter = "/";
    String uri = requestMessage.getEndpoint().toString();
    if ((!uri.endsWith("/")) && ((requestMessage.getResourcePath() == null) || (!requestMessage.getResourcePath().startsWith("/"))))
    {
      uri = uri + "/";
    }
    
    if (requestMessage.getResourcePath() != null) {
      uri = uri + requestMessage.getResourcePath();
    }
    String paramString;
    try
    {
      paramString = HttpUtil.paramToQueryString(requestMessage.getParameters(), context.getCharset());
    }
    catch (UnsupportedEncodingException e) {
      throw new AssertionError(rm.getFormattedString("EncodingFailed", new Object[] { e.getMessage() }));
    }
    
    boolean requestHasNoPayload = requestMessage.getContent() != null;
    boolean requestIsPost = requestMessage.getMethod() == HttpMethod.POST;
    boolean putParamsInUri = (!requestIsPost) || (requestHasNoPayload);
    if ((paramString != null) && (putParamsInUri)) {
      uri = uri + "?" + paramString;
    }
    
    request.setUrl(uri);
    
    if ((requestIsPost) && (requestMessage.getContent() == null) && (paramString != null))
    {
      try
      {
        byte[] buf = paramString.getBytes(context.getCharset());
        ByteArrayInputStream content = new ByteArrayInputStream(buf);
        request.setContent(content);
        request.setContentLength(buf.length);
      } catch (UnsupportedEncodingException e) {
        throw new AssertionError(rm.getFormattedString("EncodingFailed", new Object[] { e.getMessage() }));
      }
    } else {
      request.setContent(requestMessage.getContent());
      request.setContentLength(requestMessage.getContentLength());
    }
    
    return request;
  }
  
  private void handleResponse(ResponseMessage response, List<ResponseHandler> responseHandlers) throws ServiceException, ClientException
  {
    for (ResponseHandler h : responseHandlers) {
      h.handle(response);
    }
  }
  
  private void pause(int retries)
    throws ClientException
  {
    int scale = 300;
    long delay = (long) (Math.pow(2.0D, retries) * scale);
    
    log.debug("Retriable error detected, will retry in " + delay + "ms, attempt number: " + retries);
    try
    {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      throw new ClientException(e.getMessage(), e);
    }
  }
  
  private boolean shouldRetry(RequestMessage request, Exception exception, int statusCode, int retries)
  {
    if (retries >= this.config.getMaxErrorRetry()) {
      return false;
    }
    
    if (!request.isRepeatable()) {
      return false;
    }
    
    if (((exception instanceof SocketException)) || ((exception instanceof SocketTimeoutException)) || ((exception instanceof NoHttpResponseException)))
    {
      log.debug("Retrying on " + exception.getClass().getName() + ": " + exception.getMessage());
      
      return true;
    }
    
    if ((statusCode == 500) || (statusCode == 503))
    {
      log.debug("Retrying on " + exception.getClass().getName() + ": " + exception.getMessage());
      
      return true;
    }
    
    return false;
  }
  
  private void closeResponseSilently(ResponseMessage response)
  {
    if (response != null) {
      try {
        response.close();
      }
      catch (IOException ioe) {}
    }
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.comm.ServiceClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */