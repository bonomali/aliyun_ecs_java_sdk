package com.aliyun.common.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager
{
  private ResourceBundle bundle;
  
  ResourceManager(String baseName, Locale locale)
  {
    this.bundle = ResourceBundle.getBundle(baseName, locale);
  }
  
  public static ResourceManager getInstance(String baseName) {
    return new ResourceManager(baseName, Locale.getDefault());
  }
  
  public static ResourceManager getInstance(String baseName, Locale locale) {
    return new ResourceManager(baseName, locale);
  }
  
  public String getString(String key) {
    return this.bundle.getString(key);
  }
  
  public String getFormattedString(String key, Object... args) {
    return MessageFormat.format(getString(key), args);
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.utils.ResourceManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */