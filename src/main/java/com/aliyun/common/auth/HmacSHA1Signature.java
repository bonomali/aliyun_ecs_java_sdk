package com.aliyun.common.auth;

import com.aliyun.common.utils.BinaryUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA1Signature
  extends ServiceSignature
{
  private static final String DEFAULT_ENCODING = "UTF-8";
  private static final String ALGORITHM = "HmacSHA1";
  private static final String VERSION = "1";
  private static final Object LOCK = new Object();
  private static Mac macInstance;
  
  public String getAlgorithm() {
    return "HmacSHA1";
  }
  
  public String getVersion() {
    return "1";
  }
  
  public String computeSignature(String key, String data)
  {
    try
    {
      byte[] signData = sign(key.getBytes("UTF-8"), data.getBytes("UTF-8"));
      
      return BinaryUtil.toBase64String(signData);
    }
    catch (UnsupportedEncodingException ex) {
      throw new RuntimeException("Unsupported algorithm: UTF-8");
    }
  }
  
  private byte[] sign(byte[] key, byte[] data)
  {
    try
    {
      if (macInstance == null) {
        synchronized (LOCK) {
          if (macInstance == null) {
            macInstance = Mac.getInstance("HmacSHA1");
          }
        }
      }
      
      Mac mac = null;
      try {
        mac = (Mac)macInstance.clone();
      }
      catch (CloneNotSupportedException e) {
        mac = Mac.getInstance("HmacSHA1");
      }
      mac.init(new SecretKeySpec(key, "HmacSHA1"));
      return mac.doFinal(data);
    }
    catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException("Unsupported algorithm: HmacSHA1");
    }
    catch (InvalidKeyException ex) {
      throw new RuntimeException();
    }
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.auth.HmacSHA1Signature
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */