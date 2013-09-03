package com.aliyun.common.comm;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.HttpMethod;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

class HttpFactory
{
  public HttpClient createHttpClient(ClientConfiguration config)
  {
    HttpParams httpParams = new BasicHttpParams();
    HttpProtocolParams.setUserAgent(httpParams, config.getUserAgent());
    HttpConnectionParams.setConnectionTimeout(httpParams, config.getConnectionTimeout());
    HttpConnectionParams.setSoTimeout(httpParams, config.getSocketTimeout());
    HttpConnectionParams.setStaleCheckingEnabled(httpParams, true);
    HttpConnectionParams.setTcpNoDelay(httpParams, true);
    
    ThreadSafeClientConnManager connMgr = createThreadSafeClientConnManager(config);
    DefaultHttpClient httpClient = new DefaultHttpClient(connMgr, httpParams);
    
    if (System.getProperty("com.aliyun.openservices.disableCertChecking") != null) {
      Scheme sch = new Scheme("https", 443, getSSLSocketFactory());
      httpClient.getConnectionManager().getSchemeRegistry().register(sch);
    }
    
    String proxyHost = config.getProxyHost();
    int proxyPort = config.getProxyPort();
    
    if ((proxyHost != null) && (proxyPort > 0)) {
      HttpHost proxy = new HttpHost(proxyHost, proxyPort);
      httpClient.getParams().setParameter("http.route.default-proxy", proxy);
      
      String proxyUsername = config.getProxyUsername();
      String proxyPassword = config.getProxyPassword();
      
      if ((proxyUsername != null) && (proxyPassword != null)) {
        String proxyDomain = config.getProxyDomain();
        String proxyWorkstation = config.getProxyWorkstation();
        
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new NTCredentials(proxyUsername, proxyPassword, proxyWorkstation, proxyDomain));
      }
    }
    
    return httpClient;
  }
  
  private ThreadSafeClientConnManager createThreadSafeClientConnManager(ClientConfiguration clientConfig)
  {
    ThreadSafeClientConnManager connMgr = new ThreadSafeClientConnManager();
    connMgr.setDefaultMaxPerRoute(clientConfig.getMaxConnections());
    connMgr.setMaxTotal(clientConfig.getMaxConnections());
    
    return connMgr;
  }
  
  public HttpRequestBase createHttpRequest(ServiceClient.Request request, ExecutionContext context)
  {
    String uri = request.getUri();
    HttpMethod method = request.getMethod();
    HttpRequestBase httpRequest;
    if (method == HttpMethod.POST)
    {
      HttpPost postMethod = new HttpPost(uri);
      
      if (request.getContent() != null) {
        postMethod.setEntity(new RepeatableInputStreamEntity(request));
      }
      
      httpRequest = postMethod; } else { HttpRequestBase httpRequest;
      if (method == HttpMethod.PUT)
      {
        HttpPut putMethod = new HttpPut(uri);
        
        if (request.getContent() != null) {
          putMethod.setEntity(new RepeatableInputStreamEntity(request));
        }
        
        httpRequest = putMethod; } else { HttpRequestBase httpRequest;
        if (method == HttpMethod.GET)
        {
          httpRequest = new HttpGet(uri); } else { HttpRequestBase httpRequest;
          if (method == HttpMethod.DELETE)
          {
            httpRequest = new HttpDelete(uri); } else { HttpRequestBase httpRequest;
            if (method == HttpMethod.HEAD) {
              httpRequest = new HttpHead(uri);
            } else
              throw new IllegalArgumentException(String.format("Unsupported HTTP method：%s.", new Object[] { request.getMethod().toString() }));
          }
        } } }
    HttpRequestBase httpRequest;
    configureRequestHeaders(request, context, httpRequest);
    
    return httpRequest;
  }
  
  private void configureRequestHeaders(ServiceClient.Request request, ExecutionContext context, HttpRequestBase httpRequest)
  {
    for (Map.Entry<String, String> entry : request.getHeaders().entrySet())
    {
      if ((!((String)entry.getKey()).equalsIgnoreCase("Content-Length")) && (!((String)entry.getKey()).equalsIgnoreCase("Host")))
      {
        httpRequest.addHeader((String)entry.getKey(), (String)entry.getValue());
      }
    }
    
    if ((httpRequest.getHeaders("Content-Type") == null) || (httpRequest.getHeaders("Content-Type").length == 0))
    {
      httpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + context.getCharset().toLowerCase());
    }
  }
  
  private static SSLSocketFactory getSSLSocketFactory()
  {
    TrustManager[] trustAllCerts = { new X509TrustManager() {
      public X509Certificate[] getAcceptedIssuers() {
        return null;
      }
      
      public void checkClientTrusted(X509Certificate[] certs, String authType) {}
      
      public void checkServerTrusted(X509Certificate[] certs, String authType) {}
    } };
    
    try
    {
      SSLContext sslcontext = SSLContext.getInstance("SSL");
      sslcontext.init(null, trustAllCerts, null);
      return new SSLSocketFactory(sslcontext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.comm.HttpFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */