package com.aliyun.openservices.ecs.internal;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aliyun.common.auth.RequestSigner;
import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ExecutionContext;
import com.aliyun.common.comm.RequestMessage;
import com.aliyun.common.comm.ResponseMessage;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ecs.internal.model.ECSResult;

public abstract class ECSOperation {
  private URI endpoint;
  private ServiceClient serviceClient;
  private ServiceCredentials credentials;

  protected ECSOperation(URI endpoint, ServiceClient serviceClient, ServiceCredentials credentials) {
    assert ((endpoint != null) && (serviceClient != null) && (credentials != null));
    this.endpoint = endpoint;
    this.serviceClient = serviceClient;
    this.credentials = credentials;
  }

  public URI getEndpoint() {
    return this.endpoint;
  }

  protected ResponseMessage send(RequestMessage request, ExecutionContext context)
      throws ECSException, ClientException {
    return send(request, context, false);
  }

  protected ResponseMessage send(RequestMessage request, ExecutionContext context,
      boolean keepResponseOpen) throws ECSException, ClientException {
    try {
      ResponseMessage response = this.serviceClient.sendRequest(request, context);
      if (!keepResponseOpen) {
        ECSUtils.safeCloseResponse(response);
      }
      return response;
    } catch (ServiceException e) {
      assert ((e instanceof ECSException));
      throw ((ECSException) e);
    }
  }

  private static RequestSigner createSigner(HttpMethod method, String bucket, String key,
      ServiceCredentials credentials) {
    String resourcePath =
        new StringBuilder().append("/").append(bucket != null ? bucket : "")
            .append(key != null ? new StringBuilder().append("/").append(key).toString() : "")
            .toString();

    if ((bucket != null) && (key == null)) {
      resourcePath = new StringBuilder().append(resourcePath).append("/").toString();
    }

    return new ECSRequestSigner(method.toString(), resourcePath, credentials);
  }

  protected ExecutionContext createDefaultContext(HttpMethod method, String bucket, String key) {
    ExecutionContext context = new ExecutionContext();
    context.setCharset("utf-8");
    context.setSigner(createSigner(method, bucket, key, this.credentials));
    context.getResponseHandlers().add(new ECSErrorResponseHandler());
    return context;
  }

  protected ExecutionContext createDefaultContext(HttpMethod method, String bucketName) {
    return createDefaultContext(method, bucketName, null);
  }

  protected ExecutionContext createDefaultContext(HttpMethod method) {
    return createDefaultContext(method, null, null);
  }

  @SuppressWarnings("unchecked")
  protected <T extends ECSResult> T invoke(String ecsAction, HttpMethod httpMethod,
      Map<String, String> parameters, Class<?> resultClass) throws ECSException, ClientException {
    RequestMessage request = buildRequest(ecsAction, httpMethod, parameters);
    try {
      return (T) this.serviceClient.sendRequest(request, createContext(ecsAction),
          ECSResultParserFactory.createFactory().createResultParser(resultClass));
    } catch (ServiceException e) {
      throw handleException(e);
    }
  }

  protected void invokeNoResult(String ecsAction, HttpMethod httpMethod,
      Map<String, String> parameters) throws ECSException, ClientException {
    RequestMessage request = buildRequest(ecsAction, httpMethod, parameters);
    ResponseMessage response = null;
    try {
      response = this.serviceClient.sendRequest(request, createContext(ecsAction));
      return;
    } catch (ServiceException e) {
      throw handleException(e);
    } finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {}
      }
    }
  }

  private ExecutionContext createContext(String ecsAction) {
    ExecutionContext ec = new ExecutionContext();
    ec.setCharset("utf-8");
    ec.setSigner(new ECSRequestSigner(ecsAction, this.credentials));

    ec.getResponseHandlers().add(new ECSErrorResponseHandler());
    ec.getResponseHandlers().add(new ECSValidationResponseHandler(this.credentials, ecsAction));

    return ec;
  }

  private RequestMessage buildRequest(String ecsAction, HttpMethod httpMethod,
      Map<String, String> parameters) {
    return buildRequest(this.endpoint, ecsAction, httpMethod, parameters, this.credentials);
  }

  private static RequestMessage buildRequest(URI endpoint, String action, HttpMethod httpMethod,
      Map<String, String> parameters, ServiceCredentials credentials) {
    assert ((endpoint != null) && (action != null) && (httpMethod != null) && (credentials != null));

    if (parameters == null) {
      parameters = new LinkedHashMap<>();
    }

    RequestMessage request = new RequestMessage();
    request.setMethod(httpMethod);
    request.setEndpoint(endpoint);
    request.setResourcePath(action);
    request.setParameters(parameters);
    request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

    return request;
  }

  private ECSException handleException(ServiceException e) {
    if ((e instanceof ECSException)) {
      return (ECSException) e;
    }
    return ECSExceptionFactory.create("请求失败：" + e.getMessage(), e);
  }
}
