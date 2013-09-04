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
import com.aliyun.openservices.ecs.internal.ECSDatacenterOperation;
import com.aliyun.openservices.ecs.internal.ECSDiskOperation;
import com.aliyun.openservices.ecs.internal.ECSImageOperation;
import com.aliyun.openservices.ecs.internal.ECSInstanceOperation;
import com.aliyun.openservices.ecs.internal.ECSMonitorOperation;
import com.aliyun.openservices.ecs.internal.ECSNetworkOperation;
import com.aliyun.openservices.ecs.internal.ECSOtherOperation;
import com.aliyun.openservices.ecs.internal.ECSSecurityGroupOperation;
import com.aliyun.openservices.ecs.internal.ECSUtils;
import com.aliyun.openservices.ecs.internal.SignUtils;
import com.aliyun.openservices.ecs.model.GeneratePresignedUrlRequest;

public class ECSClient implements ECS {
  private ServiceCredentials credentials = new ServiceCredentials();
  private URI endpoint;
  private ServiceClient serviceClient;
  private static final Object LOCK = new Object();
  private ECSInstanceOperation instanceOperation;
  private ECSDiskOperation diskOperation;
  private ECSImageOperation imageOperation;
  private ECSNetworkOperation networkOperation;
  private ECSSecurityGroupOperation securityGroupOperation;
  private ECSDatacenterOperation datacenterOperation;
  private ECSMonitorOperation monitorOperation;
  private ECSOtherOperation otherOperation;

  public ECSClient(String accessKeyId, String accessKeySecret) {
    this(ECSConstants.DEFAULT_ECS_ENDPOINT, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret) {
    this(endpoint, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret,
      ClientConfiguration config) {
    CodingUtils.assertStringNotNullOrEmpty(endpoint, "endpoint");
    CodingUtils.assertStringNotNullOrEmpty(accessKeyId, "accessKeyId");
    CodingUtils.assertStringNotNullOrEmpty(accessKeySecret, "accessKeySecret");
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

  private ServiceCredentials getCredentials() {
    return credentials;
  }

  private ServiceClient getServiceClient() {
    return serviceClient;
  }

  private ECSInstanceOperation getInstanceOperation() {
    if (this.instanceOperation == null) {
      synchronized (LOCK) {
        if (this.instanceOperation == null) {
          this.instanceOperation =
              new ECSInstanceOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return instanceOperation;
  }

  private ECSDiskOperation getDiskOperation() {
    if (this.diskOperation == null) {
      synchronized (LOCK) {
        if (this.diskOperation == null) {
          this.diskOperation =
              new ECSDiskOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return diskOperation;
  }

  private ECSImageOperation getImageOperation() {
    if (this.imageOperation == null) {
      synchronized (LOCK) {
        if (this.imageOperation == null) {
          this.imageOperation =
              new ECSImageOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return imageOperation;
  }

  private ECSNetworkOperation getNetworkOperation() {
    if (this.networkOperation == null) {
      synchronized (LOCK) {
        if (this.networkOperation == null) {
          this.networkOperation =
              new ECSNetworkOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return networkOperation;
  }

  private ECSSecurityGroupOperation getSecurityGroupOperation() {
    if (this.securityGroupOperation == null) {
      synchronized (LOCK) {
        if (this.securityGroupOperation == null) {
          this.securityGroupOperation =
              new ECSSecurityGroupOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return securityGroupOperation;
  }

  private ECSDatacenterOperation getDatacenterOperation() {
    if (this.datacenterOperation == null) {
      synchronized (LOCK) {
        if (this.datacenterOperation == null) {
          this.datacenterOperation =
              new ECSDatacenterOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return datacenterOperation;
  }

  private ECSMonitorOperation getMonitorOperation() {
    if (this.monitorOperation == null) {
      synchronized (LOCK) {
        if (this.monitorOperation == null) {
          this.monitorOperation =
              new ECSMonitorOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return monitorOperation;
  }

  private ECSOtherOperation getOtherOperation() {
    if (this.otherOperation == null) {
      synchronized (LOCK) {
        if (this.otherOperation == null) {
          this.otherOperation =
              new ECSOtherOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return otherOperation;
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
