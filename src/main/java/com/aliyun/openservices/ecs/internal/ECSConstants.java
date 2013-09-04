package com.aliyun.openservices.ecs.internal;

import java.net.URI;

public interface ECSConstants {
  public static final String API_VERSION = "1";
  public static final int BUFFER_SIZE = 8192;
  public static final String CHARSET = "utf-8";
  public static final String DEFAULT_ECS_ENDPOINT = "http://ecs.aliyuncs.com/";
  public static final String DEFAULT_ECS_SECURE_ENDPOINT = "https://ecs.aliyuncs.com/";
  public static final String DEFAULT_ENCODING = "utf-8";
  public static final String DEFAULT_XML_CHARSET = "utf-8";
  public static final String ENCODING = "UTF-8";
  public static final URI ENDPOINT = URI.create(DEFAULT_ECS_ENDPOINT);

  public static final String HTTP_METHOD = "GET";
  public static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public static final long MAX_FILESIZE = 5368709120L;
  public static final int RESPONSE_EXPIRE_MINUTES = 15;
  public static final String RESPONSE_FORMAT = "JSON";
}
