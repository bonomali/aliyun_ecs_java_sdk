package com.aliyun.openservices;

public class ClientException extends RuntimeException {
  private static final long serialVersionUID = 1870835486798448798L;

  public ClientException() {}

  public ClientException(String message) {
    super(message);
  }

  public ClientException(Throwable cause) {
    super(cause);
  }

  public ClientException(String message, Throwable cause) {
    super(message, cause);
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.openservices.ClientException JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
