package com.aliyun.openservices;

public class ServiceException extends RuntimeException {
  private static final long serialVersionUID = 430933593095358673L;

  private String errorCode;

  private String requestId;

  private String hostId;

  public ServiceException() {}

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(String message, Throwable cause, String errorCode, String requestId,
      String hostId) {
    this(message, cause);

    this.errorCode = errorCode;
    this.requestId = requestId;
    this.hostId = hostId;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public String getRequestId() {
    return this.requestId;
  }

  public String getHostId() {
    return this.hostId;
  }

  public String toString() {
    return "[Error Code]:" + this.errorCode + ", " + "[Message]:" + getMessage();
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.openservices.ServiceException JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
