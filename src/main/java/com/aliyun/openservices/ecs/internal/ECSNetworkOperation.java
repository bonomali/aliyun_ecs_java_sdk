package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.internal.model.AllocatePublicIpAddressResult;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceTypesResult;
import com.aliyun.openservices.ecs.model.InstanceType;

public class ECSNetworkOperation extends ECSOperation {
  private static final String ACTION_ALLOCATE_PUBLIC_IP_ADDRESS = "AllocatePublicIpAddress";
  private static final String ACTION_RELEASE_PUBLIC_IP_ADDRESS = "ReleasePublicIpAddress";

  public ECSNetworkOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

  public String allocatePublicIpAddress(String instanceId) {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);

    AllocatePublicIpAddressResult result =
        (AllocatePublicIpAddressResult) invoke(ACTION_ALLOCATE_PUBLIC_IP_ADDRESS, HttpMethod.GET,
            params, AllocatePublicIpAddressResult.class);

    String ipAddress = null;
    if (result.IpAddress != null) {
      ipAddress = result.IpAddress;
    }
    return ipAddress;
  }

  public void releasePublicIpAddress(String publicIpAddress) {
    CodingUtils.assertStringNotNullOrEmpty(publicIpAddress, "publicIpAddress");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("PublicIpAddress", publicIpAddress);

    invokeNoResult(ACTION_RELEASE_PUBLIC_IP_ADDRESS, HttpMethod.GET, params);
  }
}
