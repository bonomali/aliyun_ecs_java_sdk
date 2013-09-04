package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.List;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.internal.model.DescribeRegionsResult;
import com.aliyun.openservices.ecs.internal.model.DescribeZonesResult;
import com.aliyun.openservices.ecs.model.Region;
import com.aliyun.openservices.ecs.model.Zone;

public class ECSDatacenterOperation extends ECSOperation {
  private static final String ACTION_DESCRIBE_REGIONS = "DescribeRegions";
  private static final String ACTION_DESCRIBE_ZONES = "DescribeZones";

  public ECSDatacenterOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
  }

  public List<Region> describeRegions() {
    DescribeRegionsResult result =
        (DescribeRegionsResult) invoke(ACTION_DESCRIBE_REGIONS, HttpMethod.GET, null,
            DescribeRegionsResult.class);

    return result.getRegions();
  }

  public List<Zone> describeZones() {
    DescribeZonesResult result =
        (DescribeZonesResult) invoke(ACTION_DESCRIBE_ZONES, HttpMethod.GET, null,
            DescribeZonesResult.class);

    return result.getZones();
  }
}
