package com.aliyun.common.utils;

import org.apache.commons.codec.binary.Base64;

public class BinaryUtil {
  public static String toBase64String(byte[] binaryData) {
    return new String(Base64.encodeBase64(binaryData));
  }

  public static byte[] fromBase64String(String base64String) {
    return Base64.decodeBase64(base64String);
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.utils.BinaryUtil JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
