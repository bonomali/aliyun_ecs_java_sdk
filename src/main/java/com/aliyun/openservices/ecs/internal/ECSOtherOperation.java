package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceTypesResult;
import com.aliyun.openservices.ecs.model.InstanceType;

public class ECSOtherOperation extends ECSOperation {
  private static final String ACTION_DESCRIBE_INSTANCE_TYPES = "DescribeInstanceTypes";

  public ECSOtherOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

  public List<InstanceType> describeInstanceTypes() throws ECSException, ClientException {
    DescribeInstanceTypesResult result =
        (DescribeInstanceTypesResult) invoke(ACTION_DESCRIBE_INSTANCE_TYPES, HttpMethod.GET, null,
            DescribeInstanceTypesResult.class);

    List<InstanceType> instanceTypes = new ArrayList<>();
    if ((result.InstanceTypes != null) && (result.InstanceTypes.InstanceTypes != null)) {
      instanceTypes.addAll(result.InstanceTypes.InstanceTypes);
    }
    return instanceTypes;
  }
}
