package com.aliyun.openservices.ecs.internal;

import java.net.URI;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;

public class ECSImageOperation extends ECSOperation {
  private static final String ACTION_DESCRIBE_IMAGES = "DescribeImages";

  public ECSImageOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

}
