package com.aliyun.openservices.ecs;

import java.net.URI;
import java.net.URISyntaxException;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.DefaultServiceClient;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ecs.internal.ECSUtils;

public class ECSClient implements ECS {
  private ServiceCredentials credentials = new ServiceCredentials();

  private URI endpoint;

  private ServiceClient serviceClient;


  public ECSClient(String accessKeyId, String accessKeySecret) {
    this("http://oss.aliyuncs.com", accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret) {
    this(endpoint, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret,
      ClientConfiguration config) {
    CodingUtils.assertStringNotNullOrEmpty(endpoint, "endpoint");
    try {
      if (!endpoint.startsWith("http://")) {
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

}
