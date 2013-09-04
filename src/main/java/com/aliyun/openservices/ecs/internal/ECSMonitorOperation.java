package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceDisksResult;
import com.aliyun.openservices.ecs.internal.model.MonitorDataResult;
import com.aliyun.openservices.ecs.model.Disk;
import com.aliyun.openservices.ecs.model.InstanceMonitorData;

public class ECSMonitorOperation extends ECSOperation {
  private static final String ACTION_GET_MONITOR_DATA = "GetMonitorData";

  public ECSMonitorOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
  }

  public InstanceMonitorData getMonitorData(String regionId, String instanceId)
      throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(regionId, "regionId");
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("RegionId", regionId);
    params.put("InstanceId", instanceId);

    MonitorDataResult result =
        (MonitorDataResult) invoke(ACTION_GET_MONITOR_DATA, HttpMethod.GET, params,
            MonitorDataResult.class);

    return result.getMonitorData().getInstanceMonitorData();
  }
}
