package com.aliyun.openservices.ecs.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.openservices.HttpMethod;

public class GeneratePresignedUrlRequest {
  private HttpMethod method;
  private String bucketName;
  private String key;
  private Date expiration;
  private ResponseHeaderOverrides responseHeaders;
  private Map<String, String> userMetadata = new HashMap();

  public GeneratePresignedUrlRequest(String bucketName, String key) {
    this(bucketName, key, HttpMethod.GET);
  }

  public GeneratePresignedUrlRequest(String bucketName, String key, HttpMethod method) {
    this.bucketName = bucketName;
    this.key = key;
    this.method = method;
  }

  public HttpMethod getMethod() {
    return this.method;
  }

  public void setMethod(HttpMethod method) {
    if ((method != HttpMethod.GET) && (method != HttpMethod.PUT)) {
      throw new IllegalArgumentException("仅支持GET和PUT方法。");
    }
    this.method = method;
  }

  public String getBucketName() {
    return this.bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Date getExpiration() {
    return this.expiration;
  }

  public void setExpiration(Date expiration) {
    this.expiration = expiration;
  }

  public ResponseHeaderOverrides getResponseHeaders() {
    return this.responseHeaders;
  }

  public void setResponseHeaders(ResponseHeaderOverrides responseHeaders) {
    this.responseHeaders = responseHeaders;
  }

  public Map<String, String> getUserMetadata() {
    return this.userMetadata;
  }

  public void setUserMetadata(Map<String, String> userMetadata) {
    if (userMetadata == null) {
      throw new NullPointerException("参数'userMeta'为空指针。");
    }
    this.userMetadata = userMetadata;
  }

  public void addUserMetadata(String key, String value) {
    this.userMetadata.put(key, value);
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.openservices.oss.model.GeneratePresignedUrlRequest JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
