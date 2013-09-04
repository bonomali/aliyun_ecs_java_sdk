package com.aliyun.openservices.ecs.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.common.comm.ResponseMessage;
import com.aliyun.common.utils.DateUtil;
import com.aliyun.common.utils.HttpUtil;
import com.aliyun.common.utils.ResourceManager;
import com.aliyun.openservices.ecs.model.ObjectMetadata;
import com.aliyun.openservices.ecs.model.ResponseHeaderOverrides;

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

  public static String makeResourcePath(String key) {
    return key != null ? urlEncodeKey(key) : null;
  }

  public static String makeResourcePath(String bucket, String key) {
    if (bucket != null) {
      return new StringBuilder()
          .append(bucket)
          .append(
              key != null
                  ? new StringBuilder().append("/").append(urlEncodeKey(key)).toString()
                  : "").toString();
    }
    return null;
  }

  private static String urlEncodeKey(String key) {
    String[] keys = key.split("/");
    StringBuffer uri = new StringBuffer();
    try {
      uri.append(HttpUtil.urlEncode(keys[0], "utf-8"));

      for (int i = 1; i < keys.length; i++) {
        uri.append("/").append(HttpUtil.urlEncode(keys[i], "utf-8"));
      }

      if (key.endsWith("/")) {
        for (int i = key.length() - 1; i >= 0; i--) {
          if (key.charAt(i) != '/') break;
          uri.append("/");
        }
      }

      return uri.toString();
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException(ECS_RESOURCE_MANAGER.getFormattedString(
          "FailedToEncodeObjectKey", new Object[] {key, "utf-8"}), e);
    }
  }

  public static void populateRequestMetadata(Map<String, String> headers, ObjectMetadata metadata) {
    Map<String, Object> rawMetadata = metadata.getRawMetadata();
    if (rawMetadata != null) {
      for (Map.Entry<String, Object> entry : rawMetadata.entrySet()) {
        headers.put(entry.getKey(), entry.getValue().toString());
      }
      if (!rawMetadata.keySet().contains("Content-Type")) {
        headers.put("Content-Type", "application/octet-stream");
      }
    }

    Map<String, String> userMetadata = metadata.getUserMetadata();
    if (userMetadata != null) {
      for (Map.Entry<String, String> entry : userMetadata.entrySet()) {
        String key = (String) entry.getKey();
        String value = (String) entry.getValue();
        if (key != null) key = key.trim();
        if (value != null) value = value.trim();
        headers.put(new StringBuilder().append("x-oss-meta-").append(key).toString(), value);
      }
    }
  }

  public static void addDateHeader(Map<String, String> headers, String header, Date value) {
    if (value != null) {
      headers.put(header, DateUtil.formatRfc822Date(value));
    }
  }

  public static void addListHeader(Map<String, String> headers, String header, List<String> values) {
    if (values != null) {
      StringBuilder sb = new StringBuilder();
      boolean first = true;
      for (String value : values) {
        if (!first) {
          sb.append(", ");
        }

        sb.append(value);
        first = false;
      }

      headers.put(header, sb.toString());
    }
  }

  public static String trimQuotes(String s) {
    if (s == null) {
      return null;
    }
    s = s.trim();
    if (s.startsWith("\"")) s = s.substring(1);
    if (s.endsWith("\"")) {
      s = s.substring(0, s.length() - 1);
    }
    return s;
  }

  public static Map<String, String> getResponseHeaderParameters(
      ResponseHeaderOverrides responseHeader) {
    HashMap<String, String> params = new HashMap();

    if (responseHeader != null) {
      if (responseHeader.getCacheControl() != null) {
        params.put("response-cache-control", responseHeader.getCacheControl());
      }
      if (responseHeader.getContentDisposition() != null) {
        params.put("response-content-disposition", responseHeader.getContentDisposition());
      }
      if (responseHeader.getContentEncoding() != null) {
        params.put("response-content-encoding", responseHeader.getContentEncoding());
      }
      if (responseHeader.getContentLangauge() != null) {
        params.put("response-content-language", responseHeader.getContentLangauge());
      }
      if (responseHeader.getContentType() != null) {
        params.put("response-content-type", responseHeader.getContentType());
      }
      if (responseHeader.getExpires() != null) {
        params.put("response-expires", responseHeader.getExpires());
      }
    }

    return params;
  }

  public static void safeCloseResponse(ResponseMessage response) {
    assert (response != null);
    try {
      response.close();
    } catch (IOException e) {}
  }

}
