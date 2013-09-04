package com.aliyun.openservices.ecs.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.common.utils.CodingUtils;

public class ObjectMetadata {
  private Map<String, String> userMetadata = new HashMap<>();

  private Map<String, Object> metadata = new HashMap<>();

  public Map<String, String> getUserMetadata() {
    return this.userMetadata;
  }

  public void setUserMetadata(Map<String, String> userMetadata) {
    CodingUtils.assertParameterNotNull(userMetadata, "userMetadata");
    this.userMetadata = userMetadata;
  }

  public void setHeader(String key, Object value) {
    this.metadata.put(key, value);
  }

  public void addUserMetadata(String key, String value) {
    this.userMetadata.put(key, value);
  }

  public Date getLastModified() {
    return (Date) this.metadata.get("Last-Modified");
  }

  public void setLastModified(Date lastModified) {
    this.metadata.put("Last-Modified", lastModified);
  }

  public Date getExpirationTime() {
    return (Date) this.metadata.get("Expires");
  }

  public void setExpirationTime(Date expirationTime) {
    this.metadata.put("Expires", expirationTime);
  }

  public long getContentLength() {
    Long contentLength = (Long) this.metadata.get("Content-Length");

    if (contentLength == null) return 0L;
    return contentLength.longValue();
  }

  public void setContentLength(long contentLength) {
    if (contentLength > 5368709120L) {
      throw new IllegalArgumentException("内容长度不能超过5G字节。");
    }

    this.metadata.put("Content-Length", Long.valueOf(contentLength));
  }

  public String getContentType() {
    return (String) this.metadata.get("Content-Type");
  }

  public void setContentType(String contentType) {
    this.metadata.put("Content-Type", contentType);
  }

  public String getContentEncoding() {
    return (String) this.metadata.get("Content-Encoding");
  }

  public void setContentEncoding(String encoding) {
    this.metadata.put("Content-Encoding", encoding);
  }

  public String getCacheControl() {
    return (String) this.metadata.get("Cache-Control");
  }

  public void setCacheControl(String cacheControl) {
    this.metadata.put("Cache-Control", cacheControl);
  }

  public String getContentDisposition() {
    return (String) this.metadata.get("Content-Disposition");
  }

  public void setContentDisposition(String disposition) {
    this.metadata.put("Content-Disposition", disposition);
  }

  public String getETag() {
    return (String) this.metadata.get("ETag");
  }

  public Map<String, Object> getRawMetadata() {
    return Collections.unmodifiableMap(this.metadata);
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.openservices.oss.model.ObjectMetadata JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
