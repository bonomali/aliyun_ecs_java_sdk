package com.aliyun.common.utils;

import java.util.LinkedList;

public class Lists {
  public static <T> java.util.List<T> newLinkedList(Iterable<T> it) {
    if (it == null) {
      return null;
    }
    java.util.List<T> list = new LinkedList();
    for (java.util.Iterator i$ = it.iterator(); i$.hasNext();) {
      T i = (T) i$.next();
      list.add(i);
    }
    return list;
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.utils.Lists JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
