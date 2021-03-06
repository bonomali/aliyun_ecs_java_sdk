package com.aliyun.openservices.ecs.internal;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.aliyun.common.comm.RequestMessage;
import com.aliyun.common.utils.HttpUtil;

public class SignUtils {
  private static final String NEW_LINE = "\n";
  private static final List<String> SIGNED_PARAMTERS = Arrays.asList(new String[] {"acl",
      "uploadId", "partNumber", "uploads", "response-cache-control",
      "response-content-disposition", "response-content-encoding", "response-content-language",
      "response-content-type", "response-expires"});

  public static String buildCanonicalString(Map<String, String> parameters)
      throws UnsupportedEncodingException {
    if (null == parameters || parameters.isEmpty()) {
      return null;
    }
    // 将参数Key按字典顺序排序
    String[] sortedKeys = parameters.keySet().toArray(new String[] {});
    Arrays.sort(sortedKeys);

    final String SEPARATOR = "&";

    // 生成规范化请求字符串
    StringBuilder canonicalizedQueryString = new StringBuilder();
    canonicalizedQueryString.append(ECSConstants.HTTP_METHOD).append(SEPARATOR)
        .append(HttpUtil.urlEncode("/")).append(SEPARATOR);
    boolean isNotTheFirst = false;
    for (String key : sortedKeys) {
      if (isNotTheFirst) {
        canonicalizedQueryString.append(SEPARATOR);
      } else {
        isNotTheFirst = true;
      }
      appendToCanonicalString(canonicalizedQueryString, key, parameters.get(key));
    }
    return canonicalizedQueryString.toString();
  }

  public static void appendToCanonicalString(StringBuilder canonicalizedQueryString, String key,
      String value) throws UnsupportedEncodingException {
    if (null != key) {
      canonicalizedQueryString.append(HttpUtil.urlEncode(key));
      if (null != value) {
        canonicalizedQueryString.append("=").append(HttpUtil.urlEncode(value));
      }
    }
  }

  public static String appendToCanonicalString(String queryString, String key, String value)
      throws UnsupportedEncodingException {
    final String SEPARATOR = "&";
    StringBuilder canonicalizedQueryString = new StringBuilder(queryString);
    if (null != queryString && queryString.length() > 0) {
      canonicalizedQueryString.append(SEPARATOR);
    }
    appendToCanonicalString(canonicalizedQueryString, key, value);
    return canonicalizedQueryString.toString();
  }

  public static String buildCanonicalString(String method, String resourcePath,
      RequestMessage request, String expires) {
    StringBuilder builder = new StringBuilder();
    builder.append(new StringBuilder().append(method).append("\n").toString());

    Map<String, String> headers = request.getHeaders();
    TreeMap<String, String> headersToSign = new TreeMap<>();

    if (headers != null) {
      for (Map.Entry<String, String> header : headers.entrySet()) {
        if (header.getKey() != null) {
          String lowerKey = ((String) header.getKey()).toLowerCase();

          if ((lowerKey.equals("Content-Type".toLowerCase()))
              || (lowerKey.equals("Content-MD5".toLowerCase()))
              || (lowerKey.equals("Date".toLowerCase())) || (lowerKey.startsWith("x-oss-"))) {
            headersToSign.put(lowerKey, header.getValue());
          }
        }
      }
    }
    if (!headersToSign.containsKey("Content-Type".toLowerCase())) {
      headersToSign.put("Content-Type".toLowerCase(), "");
    }
    if (!headersToSign.containsKey("Content-MD5".toLowerCase())) {
      headersToSign.put("Content-MD5".toLowerCase(), "");
    }

    if (request.getParameters() != null) {
      for (Map.Entry<String, String> p : request.getParameters().entrySet()) {
        if (((String) p.getKey()).startsWith("x-oss-")) {
          headersToSign.put(p.getKey(), p.getValue());
        }
      }
    }

    for (Map.Entry<String, String> entry : headersToSign.entrySet()) {
      String key = (String) entry.getKey();
      Object value = entry.getValue();

      if (key.startsWith("x-oss-")) {
        builder.append(key).append(':').append(value);
      } else {
        builder.append(value);
      }

      builder.append("\n");
    }

    builder.append(buildCanonicalizedResource(resourcePath, request.getParameters()));

    return builder.toString();
  }

  private static String buildCanonicalizedResource(String resourcePath,
      Map<String, String> parameters) {
    assert (resourcePath.startsWith("/"));

    StringBuilder builder = new StringBuilder();
    builder.append(resourcePath);

    if (parameters != null) {
      String[] parameterNames =
          (String[]) parameters.keySet().toArray(new String[parameters.size()]);

      Arrays.sort(parameterNames);
      char separater = '?';
      for (String paramName : parameterNames) {
        if (SIGNED_PARAMTERS.contains(paramName)) {
          builder.append(separater);
          builder.append(paramName);
          String paramValue = (String) parameters.get(paramName);
          if (paramValue != null) {
            builder.append("=").append(paramValue);
          }

          separater = '&';
        }
      }
    }
    return builder.toString();
  }
}
