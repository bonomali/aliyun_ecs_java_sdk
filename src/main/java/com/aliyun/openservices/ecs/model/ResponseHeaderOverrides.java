package com.aliyun.openservices.ecs.model;

public class ResponseHeaderOverrides {
  private String contentType;

  private String contentLangauge;

  private String expires;

  private String cacheControl;

  private String contentDisposition;

  private String contentEncoding;

  public static final String RESPONSE_HEADER_CONTENT_TYPE = "response-content-type";

  public static final String RESPONSE_HEADER_CONTENT_LANGUAGE = "response-content-language";

  public static final String RESPONSE_HEADER_EXPIRES = "response-expires";

  public static final String RESPONSE_HEADER_CACHE_CONTROL = "response-cache-control";

  public static final String RESPONSE_HEADER_CONTENT_DISPOSITION = "response-content-disposition";

  public static final String RESPONSE_HEADER_CONTENT_ENCODING = "response-content-encoding";

  public String getContentType() {
    return this.contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getContentLangauge() {
    return this.contentLangauge;
  }

  public void setContentLangauge(String contentLangauge) {
    this.contentLangauge = contentLangauge;
  }

  public String getExpires() {
    return this.expires;
  }

  public void setExpires(String expires) {
    this.expires = expires;
  }

  public String getCacheControl() {
    return this.cacheControl;
  }

  public void setCacheControl(String cacheControl) {
    this.cacheControl = cacheControl;
  }

  public String getContentDisposition() {
    return this.contentDisposition;
  }

  public void setContentDisposition(String contentDisposition) {
    this.contentDisposition = contentDisposition;
  }

  public String getContentEncoding() {
    return this.contentEncoding;
  }

  public void setContentEncoding(String contentEncoding) {
    this.contentEncoding = contentEncoding;
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.openservices.oss.model.ResponseHeaderOverrides JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
