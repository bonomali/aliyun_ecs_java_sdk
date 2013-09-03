package com.aliyun.common.comm;

public class ResponseMessage
  extends HttpMesssage
{
  private String uri;
  
  private int statusCode;
  
  private static final int HTTP_SUCCESS_STATUS_CODE = 200;
  
  public String getUri()
  {
    return this.uri;
  }
  
  public void setUrl(String uri) {
    this.uri = uri;
  }
  
  public int getStatusCode() {
    return this.statusCode;
  }
  
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }
  
  public boolean isSuccessful() {
    return this.statusCode / 100 == 2;
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.comm.ResponseMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */