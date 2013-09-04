package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceTypesResult;
import com.aliyun.openservices.ecs.internal.model.InstanceTypesListResult;
import com.aliyun.openservices.ecs.model.InstanceType;
import com.aliyun.openservices.ots.internal.model.ListTableGroupResult;

public class ECSOtherOperation extends ECSOperation {
  private static final String ACTION_DESCRIBE_INSTANCE_TYPES = "DescribeInstanceTypes";

  public ECSOtherOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

  public List<InstanceType> describeInstanceTypes() {
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
