package com.aliyun.openservices;

public class ClientConfiguration
{
  private static final String DEFAULT_USER_AGENT = "aliyun-sdk-java";
  
  private static final int DEFAULT_MAX_RETRIES = 3;
  
  private String userAgent = "aliyun-sdk-java";
  private String proxyHost;
  private int proxyPort;
  private String proxyUsername;
  private String proxyPassword;
  private String proxyDomain;
  private String proxyWorkstation;
  private int maxConnections = 50;
  private int socketTimeout = 50000;
  private int connectionTimeout = 50000;
  private int maxErrorRetry = 3;
  
  public String getUserAgent()
  {
    return this.userAgent;
  }
  
  public void setUserAgent(String userAgent)
  {
    this.userAgent = userAgent;
  }
  
  public String getProxyHost()
  {
    return this.proxyHost;
  }
  
  public void setProxyHost(String proxyHost)
  {
    this.proxyHost = proxyHost;
  }
  
  public int getProxyPort()
  {
    return this.proxyPort;
  }
  
  public void setProxyPort(int proxyPort)
  {
    this.proxyPort = proxyPort;
  }
  
  public String getProxyUsername()
  {
    return this.proxyUsername;
  }
  
  public void setProxyUsername(String proxyUsername)
  {
    this.proxyUsername = proxyUsername;
  }
  
  public String getProxyPassword()
  {
    return this.proxyPassword;
  }
  
  public void setProxyPassword(String proxyPassword)
  {
    this.proxyPassword = proxyPassword;
  }
  
  public String getProxyDomain()
  {
    return this.proxyDomain;
  }
  
  public void setProxyDomain(String proxyDomain)
  {
    this.proxyDomain = proxyDomain;
  }
  
  public String getProxyWorkstation()
  {
    return this.proxyWorkstation;
  }
  
  public void setProxyWorkstation(String proxyWorkstation)
  {
    this.proxyWorkstation = proxyWorkstation;
  }
  
  public int getMaxConnections()
  {
    return this.maxConnections;
  }
  
  public void setMaxConnections(int maxConnections)
  {
    this.maxConnections = maxConnections;
  }
  
  public int getSocketTimeout()
  {
    return this.socketTimeout;
  }
  
  public void setSocketTimeout(int socketTimeout)
  {
    this.socketTimeout = socketTimeout;
  }
  
  public int getConnectionTimeout()
  {
    return this.connectionTimeout;
  }
  
  public void setConnectionTimeout(int connectionTimeout)
  {
    this.connectionTimeout = connectionTimeout;
  }
  
  public int getMaxErrorRetry()
  {
    return this.maxErrorRetry;
  }
  
  public void setMaxErrorRetry(int maxErrorRetry)
  {
    this.maxErrorRetry = maxErrorRetry;
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.openservices.ClientConfiguration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */