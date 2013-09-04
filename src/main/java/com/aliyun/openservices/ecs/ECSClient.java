package com.aliyun.openservices.ecs;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.auth.ServiceSignature;
import com.aliyun.common.comm.DefaultServiceClient;
import com.aliyun.common.comm.RequestMessage;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.common.utils.HttpUtil;
import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.internal.ECSConstants;
import com.aliyun.openservices.ecs.internal.ECSUtils;
import com.aliyun.openservices.ecs.internal.SignUtils;

public class ECSClient implements ECS {
  private ServiceCredentials credentials = new ServiceCredentials();
  private URI endpoint;
  private ServiceClient serviceClient;

  public ECSClient(String accessKeyId, String accessKeySecret) {
    this(ECSConstants.DEFAULT_ECS_ENDPOINT, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret) {
    this(endpoint, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret,
      ClientConfiguration config) {
    CodingUtils.assertStringNotNullOrEmpty(endpoint, "endpoint");
    try {
      if (!endpoint.startsWith("http://") || !endpoint.startsWith("https://")) {
        throw new IllegalArgumentException(
            ECSUtils.ECS_RESOURCE_MANAGER.getString("EndpointProtocolInvalid"));
      }
      this.endpoint = new URI(endpoint);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
    this.credentials = new ServiceCredentials(accessKeyId, accessKeySecret);
    this.serviceClient =
        new DefaultServiceClient(config != null ? config : new ClientConfiguration());
  }

  public URI getEndpoint() {
    return this.endpoint;
  }

  public String getAccessKeyId() {
    return this.credentials.getAccessKeyId();
  }

  public String getAccessKeySecret() {
    return this.credentials.getAccessKeySecret();
  }

  public URL generatePresignedUrl(String bucketName, String key, Date expiration)
      throws ClientException {
    return generatePresignedUrl(bucketName, key, expiration, HttpMethod.GET);
  }

  public URL generatePresignedUrl(String bucketName, String key, Date expiration, HttpMethod method)
      throws ClientException {
    GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key);
    request.setExpiration(expiration);
    request.setMethod(method);

    return generatePresignedUrl(request);
  }

  public URL generatePresignedUrl(GeneratePresignedUrlRequest request) throws ClientException {
    CodingUtils.assertParameterNotNull(request, "request");
    if (request.getBucketName() == null) {
      throw new IllegalArgumentException(
          ECSUtils.ECS_RESOURCE_MANAGER.getString("MustSetBucketName"));
    }
    if (request.getExpiration() == null) {
      throw new IllegalArgumentException(
          ECSUtils.ECS_RESOURCE_MANAGER.getString("MustSetExpiration"));
    }

    String bucketName = request.getBucketName();
    String key = request.getKey();

    String accessId = this.credentials.getAccessKeyId();
    String accessKey = this.credentials.getAccessKeySecret();
    HttpMethod method = request.getMethod() != null ? request.getMethod() : HttpMethod.GET;

    String expires = String.valueOf(request.getExpiration().getTime() / 1000L);
    String resourcePath = ECSUtils.makeResourcePath(key);

    RequestMessage requestMessage = new RequestMessage();
    requestMessage.setEndpoint(ECSUtils.makeBukcetEndpoint(this.endpoint, bucketName));
    requestMessage.setMethod(method);
    requestMessage.setResourcePath(resourcePath);
    requestMessage.addHeader("Date", expires);
    for (Map.Entry<String, String> h : request.getUserMetadata().entrySet()) {
      requestMessage.addHeader(new StringBuilder().append("x-oss-meta-")
          .append((String) h.getKey()).toString(), (String) h.getValue());
    }

    Map<String, String> responseHeadersParams =
        ECSUtils.getResponseHeaderParameters(request.getResponseHeaders());

    if (responseHeadersParams.size() > 0) {
      requestMessage.setParameters(responseHeadersParams);
    }

    String canonicalResource =
        new StringBuilder().append("/").append(bucketName != null ? bucketName : "")
            .append(key != null ? new StringBuilder().append("/").append(key).toString() : "")
            .toString();

    String canonicalString =
        SignUtils.buildCanonicalString(method.toString(), canonicalResource, requestMessage,
            expires);

    String signature = ServiceSignature.create().computeSignature(accessKey, canonicalString);

    Map<String, String> params = new HashMap();
    params.put("Expires", expires);
    params.put("ECSAccessKeyId", accessId);
    params.put("Signature", signature);
    params.putAll(responseHeadersParams);

    String queryString;
    try {
      queryString = HttpUtil.paramToQueryString(params, "utf-8");
    } catch (UnsupportedEncodingException e) {
      throw new ClientException(ECSUtils.ECS_RESOURCE_MANAGER.getString("FailedToEncodeUri"), e);
    }

    String url = requestMessage.getEndpoint().toString();
    if (!url.endsWith("/")) {
      url = new StringBuilder().append(url).append("/").toString();
    }
    url =
        new StringBuilder().append(url).append(resourcePath).append("?").append(queryString)
            .toString();
    try {
      return new URL(url);
    } catch (MalformedURLException e) {
      throw new ClientException(e);
    }
  }
}
