package com.aliyun.common.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity;

class RepeatableInputStreamEntity extends BasicHttpEntity {
  private boolean firstAttempt = true;

  private InputStreamEntity innerEntity;
  private InputStream content;

  public RepeatableInputStreamEntity(ServiceClient.Request request) {
    setChunked(false);

    String contentType = (String) request.getHeaders().get("Content-Type");
    this.content = request.getContent();
    long contentLength = request.getContentLength();

    this.innerEntity = new InputStreamEntity(this.content, contentLength);
    this.innerEntity.setContentType(contentType);

    setContent(this.content);
    setContentType(contentType);
    setContentLength(request.getContentLength());
  }

  public boolean isChunked() {
    return false;
  }

  public boolean isRepeatable() {
    return (this.content.markSupported()) || (this.innerEntity.isRepeatable());
  }

  public void writeTo(OutputStream output) throws IOException {
    if ((!this.firstAttempt) && (isRepeatable())) {
      this.content.reset();
    }

    this.firstAttempt = false;
    this.innerEntity.writeTo(output);
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.comm.RepeatableInputStreamEntity JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
