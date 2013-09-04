package com.aliyun.openservices.ecs.internal;

import java.net.URI;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;

public class ECSDiskOperation extends ECSOperation {
  public static final String ACTION_DESCRIBE_INSTANCE_DISKS = "DescribeInstanceDisks";

  public ECSDiskOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

}
