package com.aliyun.openservices.ecs;

import java.io.Serializable;

import com.aliyun.openservices.ServiceException;

public class ECSException extends ServiceException implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -981480886499069623L;
  private String header;
  private String resourceType;

  public ECSException() {}

  public ECSException(String message) {
    super(message);
  }

  public ECSException(String message, Throwable cause) {
    super(message, cause);
  }

  public ECSException(String message, Throwable cause, String errorCode, String requestId,
      String hostId) {
    super(message, cause, errorCode, requestId, hostId);
  }

  public ECSException(String message, String errorCode, String requestId, String hostId,
      String header, String resourceType, String method) {
    super(message, null, errorCode, requestId, hostId);

    this.header = header;
    this.resourceType = resourceType;
  }

  public String getHeader() {
    return this.header;
  }

  public String getResourceType() {
    return this.resourceType;
  }
}
