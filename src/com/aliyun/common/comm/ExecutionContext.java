package com.aliyun.common.comm;

import java.util.LinkedList;
import java.util.List;

import com.aliyun.common.auth.RequestSigner;

public class ExecutionContext {
  private String charset = "utf-8";

  private RequestSigner signer;
  private List<ResponseHandler> responseHandlers = new LinkedList();

  public String getCharset() {
    return this.charset;
  }

  public void setCharset(String defaultEncoding) {
    this.charset = defaultEncoding;
  }

  public RequestSigner getSigner() {
    return this.signer;
  }

  public void setSigner(RequestSigner signer) {
    this.signer = signer;
  }

  public List<ResponseHandler> getResponseHandlers() {
    return this.responseHandlers;
  }

  public void addResponseHandler(ResponseHandler handler) {
    this.responseHandlers.add(handler);
  }

  public void insertResponseHandler(int position, ResponseHandler handler) {
    this.responseHandlers.add(position, handler);
  }

  public void removeResponseHandler(ResponseHandler handler) {
    this.responseHandlers.remove(handler);
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.comm.ExecutionContext JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
