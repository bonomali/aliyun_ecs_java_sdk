package com.aliyun.common.utils;

import com.aliyun.openservices.ClientConfiguration;

public class ClientConfigurationUtil {
  public static void copyConfig(ClientConfiguration source, ClientConfiguration target) {
    target.setConnectionTimeout(source.getConnectionTimeout());
    target.setMaxConnections(source.getMaxConnections());
    target.setProxyDomain(source.getProxyDomain());
    target.setProxyHost(source.getProxyHost());
    target.setProxyPassword(source.getProxyPassword());
    target.setProxyPort(source.getProxyPort());
    target.setProxyUsername(source.getProxyUsername());
    target.setProxyWorkstation(source.getProxyWorkstation());
    target.setSocketTimeout(source.getSocketTimeout());
    target.setUserAgent(source.getUserAgent());
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.utils.ClientConfigurationUtil JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
