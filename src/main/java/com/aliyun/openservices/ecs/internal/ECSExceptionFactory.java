package com.aliyun.openservices.ecs.internal;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ecs.internal.model.ECSErrorResult;

public class ECSExceptionFactory {
  public static ECSException create(ECSErrorResult err) {
    return new ECSException(err.Message != null ? err.Message.trim() : null, err.Code != null
        ? err.Code.trim()
        : null, err.RequestId != null ? err.RequestId.trim() : null, err.HostId != null
        ? err.HostId.trim()
        : null, err.Header != null ? err.Header.trim() : null, err.ResourceType != null
        ? err.ResourceType.trim()
        : null, err.Method != null ? err.Method.trim() : null);
  }

  public static ECSException create(String requestId, String errorCode, String message) {
    return new ECSException(message, errorCode, requestId, null, null, null, null);
  }

  public static ECSException create(String message, Throwable cause) {
    return new ECSException(message, cause);
  }

  public static ECSException create(String message, Throwable cause, String errorCode,
      String requestId, String hostId) {
    return new ECSException(message, cause, errorCode, requestId, hostId);
  }

  public static ClientException createResponseException(String message, Throwable cause) {
    return new ClientException(
        ECSUtils.ECS_RESOURCE_MANAGER.getString("ResponseInvalid") + message, cause);
  }
}
