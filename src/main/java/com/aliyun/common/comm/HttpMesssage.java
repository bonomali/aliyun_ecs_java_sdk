package com.aliyun.common.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.aliyun.common.utils.CaseInsensitiveMap;

public abstract class HttpMesssage {
  private Map<String, String> headers = new CaseInsensitiveMap();

  private InputStream content;

  private long contentLength;

  public Map<String, String> getHeaders() {
    return this.headers;
  }

  public void setHeaders(Map<String, String> headers) {
    assert (headers != null);
    this.headers = headers;
  }

  public void addHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public InputStream getContent() {
    return this.content;
  }

  public void setContent(InputStream content) {
    this.content = content;
  }

  public long getContentLength() {
    return this.contentLength;
  }

  public void setContentLength(long contentLength) {
    this.contentLength = contentLength;
  }

  public void close() throws IOException {
    if (this.content != null) {
      this.content.close();
      this.content = null;
    }
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.comm.HttpMesssage JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
