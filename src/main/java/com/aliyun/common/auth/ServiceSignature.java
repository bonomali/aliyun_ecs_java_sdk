package com.aliyun.common.auth;

public abstract class ServiceSignature {
  public abstract String getAlgorithm();

  public abstract String getVersion();

  public abstract String computeSignature(String paramString1, String paramString2);

  public static ServiceSignature create() {
    return new HmacSHA1Signature();
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.auth.ServiceSignature JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
