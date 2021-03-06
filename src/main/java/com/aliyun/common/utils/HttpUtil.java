package com.aliyun.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import com.aliyun.openservices.ecs.internal.ECSConstants;

public class HttpUtil {
  private static final String ISO_8859_1_CHARSET = "iso-8859-1";
  private static final String JAVA_CHARSET = "utf-8";

  public static String urlEncode(String value) throws UnsupportedEncodingException {
    return urlEncode(value, ECSConstants.DEFAULT_ENCODING);
  }

  public static String urlEncode(String value, String charset) throws UnsupportedEncodingException {
    return value != null ? URLEncoder.encode(value, charset).replace("+", "%20")
        .replace("*", "%2A").replace("%7E", "~") : null;
  }


  public static String paramsToQueryString(Map<String, String> params)
      throws UnsupportedEncodingException {
    return paramToQueryString(params, ECSConstants.DEFAULT_ENCODING);
  }

  public static String paramToQueryString(Map<String, String> params, String charset)
      throws UnsupportedEncodingException {
    if ((params == null) || (params.size() == 0)) {
      return null;
    }

    StringBuilder paramString = new StringBuilder();
    boolean first = true;
    for (Map.Entry<String, String> p : params.entrySet()) {
      String key = (String) p.getKey();
      String val = (String) p.getValue();

      if (!first) {
        paramString.append("&");
      }

      paramString.append(key);
      if (val != null) {
        paramString.append("=").append(urlEncode(val, charset));
      }

      first = false;
    }

    return paramString.toString();
  }


  public static void convertHeaderCharsetFromIso88591(Map<String, String> headers) {
    convertHeaderCharset(headers, "iso-8859-1", "utf-8");
  }

  public static void convertHeaderCharsetToIso88591(Map<String, String> headers) {
    convertHeaderCharset(headers, "utf-8", "iso-8859-1");
  }

  private static void convertHeaderCharset(Map<String, String> headers, String fromCharset,
      String toCharset) {
    assert (headers != null);

    for (Map.Entry<String, String> header : headers.entrySet()) {
      try {
        header.setValue(new String(((String) header.getValue()).getBytes(fromCharset), toCharset));
      } catch (UnsupportedEncodingException e) {
        throw new AssertionError("Invalid charset name.");
      }
    }
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.utils.HttpUtil JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
