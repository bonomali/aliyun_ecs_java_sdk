package com.aliyun.common.auth;

import com.aliyun.common.utils.CodingUtils;

public class ServiceCredentials
{
  private String accessKeyId;
  private String accessKeySecret;
  
  public String getAccessKeyId()
  {
    return this.accessKeyId;
  }
  
  public void setAccessKeyId(String accessKeyId)
  {
    CodingUtils.assertParameterNotNull(accessKeyId, "accessKeyId");
    this.accessKeyId = accessKeyId;
  }
  
  public String getAccessKeySecret()
  {
    return this.accessKeySecret;
  }
  
  public void setAccessKeySecret(String accessKeySecret)
  {
    CodingUtils.assertParameterNotNull(accessKeySecret, "accessKeySecret");
    
    this.accessKeySecret = accessKeySecret;
  }
  
  public ServiceCredentials() {}
  
  public ServiceCredentials(String accessKeyId, String accessKeySecret)
  {
    setAccessKeyId(accessKeyId);
    setAccessKeySecret(accessKeySecret);
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.auth.ServiceCredentials
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */