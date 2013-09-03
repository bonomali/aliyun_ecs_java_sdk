package com.aliyun.common.comm;

import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.HttpMethod;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RequestMessage
  extends HttpMesssage
{
  private HttpMethod method = HttpMethod.GET;
  private URI endpoint;
  private String resourcePath;
  private Map<String, String> parameters = new HashMap();
  
  public HttpMethod getMethod()
  {
    return this.method;
  }
  
  public void setMethod(HttpMethod method)
  {
    this.method = method;
  }
  
  public URI getEndpoint()
  {
    return this.endpoint;
  }
  
  public void setEndpoint(URI endpoint)
  {
    this.endpoint = endpoint;
  }
  
  public String getResourcePath()
  {
    return this.resourcePath;
  }
  
  public void setResourcePath(String resourcePath)
  {
    this.resourcePath = resourcePath;
  }
  
  public Map<String, String> getParameters()
  {
    return this.parameters;
  }
  
  public void setParameters(Map<String, String> parameters)
  {
    CodingUtils.assertParameterNotNull(parameters, "parameters");
    
    this.parameters = parameters;
  }
  
  public void addParameter(String key, String value) {
    CodingUtils.assertStringNotNullOrEmpty(key, "key");
    
    this.parameters.put(key, value);
  }
  
  public void removeParameter(String key) {
    CodingUtils.assertStringNotNullOrEmpty(key, "key");
    
    this.parameters.remove(key);
  }
  
  public boolean isRepeatable()
  {
    return (getContent() == null) || (getContent().markSupported());
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.comm.RequestMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */