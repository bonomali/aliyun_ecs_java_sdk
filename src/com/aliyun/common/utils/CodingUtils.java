package com.aliyun.common.utils;

public class CodingUtils
{
  private static ResourceManager rm = ResourceManager.getInstance("common");
  
  public static void assertParameterNotNull(Object param, String paramName)
  {
    if (param == null) {
      throw new NullPointerException(rm.getFormattedString("ParameterIsNull", new Object[] { paramName }));
    }
  }
  
  public static void assertStringNotNullOrEmpty(String param, String paramName) {
    assertParameterNotNull(param, paramName);
    assert (param != null);
    if (param.length() == 0) {
      throw new IllegalArgumentException(rm.getFormattedString("ParameterStringIsEmpty", new Object[] { paramName }));
    }
  }
  
  public static boolean isNullOrEmpty(String value) {
    return (value == null) || (value.length() == 0);
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.utils.CodingUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */