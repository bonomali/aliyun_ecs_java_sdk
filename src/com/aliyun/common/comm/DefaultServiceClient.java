package com.aliyun.common.comm;

import com.aliyun.common.utils.HttpUtil;
import com.aliyun.openservices.ClientConfiguration;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;

public class DefaultServiceClient
  extends ServiceClient
{
  private HttpClient httpClient;
  
  public DefaultServiceClient(ClientConfiguration config)
  {
    super(config);
    this.httpClient = new HttpFactory().createHttpClient(config);
  }
  
  public ResponseMessage sendRequestCore(ServiceClient.Request request, ExecutionContext context)
    throws IOException
  {
    assert ((request != null) && (context != null));
    
    this.httpClient.getConnectionManager().closeIdleConnections(30L, TimeUnit.SECONDS);
    
    HttpRequestBase httpRequest = new HttpFactory().createHttpRequest(request, context);
    
    HttpResponse response = this.httpClient.execute(httpRequest);
    
    ResponseMessage result = new ResponseMessage();
    result.setUrl(request.getUri());
    if (response.getStatusLine() != null) {
      result.setStatusCode(response.getStatusLine().getStatusCode());
    }
    if (response.getEntity() != null) {
      result.setContent(response.getEntity().getContent());
    }
    
    Header[] headers = response.getAllHeaders();
    Map<String, String> resultHeaders = new HashMap();
    for (int i = 0; i < headers.length; i++) {
      Header h = headers[i];
      resultHeaders.put(h.getName(), h.getValue());
    }
    HttpUtil.convertHeaderCharsetFromIso88591(resultHeaders);
    result.setHeaders(resultHeaders);
    
    return result;
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.comm.DefaultServiceClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */