package com.aliyun.openservices.ecs.internal;

import java.net.URI;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;

public class ECSDatacenterOperation extends ECSOperation {
  private static final String ACTION_DESCRIBE_REGIONS = "DescribeRegions";
  private static final String ACTION_DESCRIBE_ZONES = "DescribeZones";

  public ECSDatacenterOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

}
