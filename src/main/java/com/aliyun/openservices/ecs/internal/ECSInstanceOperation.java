package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ecs.internal.model.CreateSecurityGroupResult;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceAttributeResult;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceStatusResult;
import com.aliyun.openservices.ecs.model.InstanceAttribute;
import com.aliyun.openservices.ecs.model.InstanceStatus;
import com.aliyun.openservices.ots.internal.OTSUtil;

public class ECSInstanceOperation extends ECSOperation {
  private static final String ACTION_START_INSTANCE = "StartInstance";
  private static final String ACTION_STOP_INSTANCE = "StopInstance";
  private static final String ACTION_REBOOT_INSTANCE = "RebootInstance";
  private static final String ACTION_RESET_INSTANCE = "ResetInstance";
  private static final String ACTION_MODIFY_INSTANCE_ATTRIBUTE = "ModifyInstanceAttribute";
  private static final String ACTION_DESCRIBE_INSTANCE_STATUS = "DescribeInstanceStatus";
  private static final String ACTION_DESCRIBE_INSTANCE_ATTRIBUTE = "DescribeInstanceAttribute";

  public ECSInstanceOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
  }

  public void startInstance(String instanceId) throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);

    invokeNoResult(ACTION_START_INSTANCE, HttpMethod.GET, params);
  }

  public void stopInstance(String instanceId) throws ECSException, ClientException {
    String forceStop = "false"; // or "true"
    stopInstance(instanceId, forceStop);
  }

  public void stopInstance(String instanceId, String forceStop) throws ECSException,
      ClientException {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");
    CodingUtils.assertStringNotNullOrEmpty(forceStop, "forceStop");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);
    params.put("ForceStop", forceStop);

    invokeNoResult(ACTION_STOP_INSTANCE, HttpMethod.GET, params);
  }

  public void rebootInstance(String instanceId) throws ECSException, ClientException {
    String forceStop = "false"; // or "true"
    rebootInstance(instanceId, forceStop);
  }

  public void rebootInstance(String instanceId, String forceStop) throws ECSException,
      ClientException {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");
    CodingUtils.assertStringNotNullOrEmpty(forceStop, "forceStop");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);
    params.put("ForceStop", forceStop);

    invokeNoResult(ACTION_REBOOT_INSTANCE, HttpMethod.GET, params);
  }

  public void resetInstance(String instanceId) throws ECSException, ClientException {
    // TODO: 将实例重置为当前实例使用的Image系统配置
    String imageId = null;
    String diskType = "system"; // or "data"
    resetInstance(instanceId, imageId, diskType);
  }

  public void resetInstance(String instanceId, String imageId, String diskType)
      throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");
    CodingUtils.assertStringNotNullOrEmpty(imageId, "imageId");
    CodingUtils.assertStringNotNullOrEmpty(diskType, "diskType");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);
    params.put("ImageId", imageId);
    params.put("DiskType", diskType);

    invokeNoResult(ACTION_REBOOT_INSTANCE, HttpMethod.GET, params);
  }

  public void modifyInstanceAttribute(String instanceId, String password, String hostName,
      String securityGroupId) throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");
    CodingUtils.assertStringNotNullOrEmpty(password, "password");
    CodingUtils.assertStringNotNullOrEmpty(hostName, "hostName");
    CodingUtils.assertStringNotNullOrEmpty(securityGroupId, "securityGroupId");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);
    params.put("Password", password);
    params.put("HostName", hostName);
    params.put("SecurityGroupId", securityGroupId);

    invokeNoResult(ACTION_MODIFY_INSTANCE_ATTRIBUTE, HttpMethod.GET, params);
  }

  public InstanceStatus describeInstanceStatus(String regionId, String zoneId) throws ECSException,
      ClientException {
    Integer pageNumber = 1;
    Integer pageSize = 10;
    return describeInstanceStatus(regionId, zoneId, pageNumber, pageSize);
  }

  public InstanceStatus describeInstanceStatus(String regionId, String zoneId, Integer pageNumber,
      Integer pageSize) throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(regionId, "regionId");
    CodingUtils.assertStringNotNullOrEmpty(zoneId, "zoneId");
    CodingUtils.assertParameterNotNull(pageNumber, "pageNumber");
    CodingUtils.assertParameterNotNull(pageSize, "pageSize");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("RegionId", regionId);
    params.put("ZoneId", zoneId);
    params.put("PageNumber", pageNumber.toString());
    params.put("PageSize", pageSize.toString());

    DescribeInstanceStatusResult result =
        (DescribeInstanceStatusResult) invoke(ACTION_DESCRIBE_INSTANCE_STATUS, HttpMethod.GET,
            params, DescribeInstanceStatusResult.class);

    return result.getInstanceStatus();
  }

  public InstanceAttribute describeInstanceAttribute(String instanceId) throws ECSException,
      ClientException {
    CodingUtils.assertStringNotNullOrEmpty(instanceId, "instanceId");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("InstanceId", instanceId);

    DescribeInstanceAttributeResult result =
        (DescribeInstanceAttributeResult) invoke(ACTION_DESCRIBE_INSTANCE_ATTRIBUTE,
            HttpMethod.GET, params, DescribeInstanceAttributeResult.class);

    return result.getInstanceAttribute();
  }
}
