package com.aliyun.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CaseInsensitiveMap<V> implements Map<String, V> {
  private Map<String, V> wrappedMap;

  public CaseInsensitiveMap() {
    this(new HashMap());
  }

  public CaseInsensitiveMap(Map<String, V> wrappedMap) {
    CodingUtils.assertParameterNotNull(wrappedMap, "wrappedMap");

    this.wrappedMap = wrappedMap;
  }

  public int size() {
    return this.wrappedMap.size();
  }

  public boolean isEmpty() {
    return this.wrappedMap.isEmpty();
  }

  public boolean containsKey(Object key) {
    return this.wrappedMap.containsKey(key.toString().toLowerCase());
  }

  public boolean containsValue(Object value) {
    return this.wrappedMap.containsValue(value);
  }

  public V get(Object key) {
    return this.wrappedMap.get(key.toString().toLowerCase());
  }

  public V put(String key, V value) {
    return this.wrappedMap.put(key.toLowerCase(), value);
  }

  public V remove(Object key) {
    return this.wrappedMap.remove(key.toString().toLowerCase());
  }

  public void putAll(Map<? extends String, ? extends V> m) {
    for (Map.Entry<? extends String, ? extends V> entry : m.entrySet()) {
      put((String) entry.getKey(), entry.getValue());
    }
  }

  public void clear() {
    this.wrappedMap.clear();
  }

  public Set<String> keySet() {
    return this.wrappedMap.keySet();
  }

  public Collection<V> values() {
    return this.wrappedMap.values();
  }

  public Set<Map.Entry<String, V>> entrySet() {
    return this.wrappedMap.entrySet();
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.utils.CaseInsensitiveMap JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
