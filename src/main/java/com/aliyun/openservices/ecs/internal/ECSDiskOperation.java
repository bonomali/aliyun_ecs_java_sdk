package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceDisksResult;
import com.aliyun.openservices.ecs.model.Disk;

public class ECSDiskOperation extends ECSOperation {
  public static final String ACTION_DESCRIBE_INSTANCE_DISKS = "DescribeInstanceDisks";

  public ECSDiskOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
  }

  public List<Disk> describeInstanceDisks(String instanceId) {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);

    DescribeInstanceDisksResult result =
        (DescribeInstanceDisksResult) invoke(ACTION_DESCRIBE_INSTANCE_DISKS, HttpMethod.GET,
            params, DescribeInstanceDisksResult.class);

    return result.getDisks();
  }
}
