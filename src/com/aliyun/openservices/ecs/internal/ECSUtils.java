package com.aliyun.openservices.ecs.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.aliyun.common.comm.ResponseMessage;
import com.aliyun.common.utils.ResourceManager;

public class ECSUtils {
  public static final ResourceManager ECS_RESOURCE_MANAGER = ResourceManager.getInstance("ecs");

  public static boolean nameValid(String name) {
    if (name == null) {
      return false;
    }
    String pattern = "^[a-zA-Z_][\\w]{0,99}$";
    return name.matches(pattern);
  }

  public static void ensureNameValid(String name) {
    if (!nameValid(name)) {
      throw new IllegalArgumentException(ECS_RESOURCE_MANAGER.getFormattedString("NameIsInvalid",
          new Object[] {name}));
    }
  }

  public static byte[] dataEncode(String data) {
    try {
      return data.getBytes("utf-8");
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError("Unsupported encoding: utf-8");
    }
  }

  public static void safeCloseResponse(ResponseMessage response) {
    assert (response != null);
    try {
      response.close();
    } catch (IOException e) {}
  }
}
