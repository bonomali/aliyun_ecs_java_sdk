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
import com.aliyun.openservices.ecs.internal.model.CreateSecurityGroupResult;
import com.aliyun.openservices.ecs.internal.model.DescribeInstanceDisksResult;
import com.aliyun.openservices.ecs.internal.model.DescribeSecurityGroupAttributeResult;
import com.aliyun.openservices.ecs.internal.model.DescribeSecurityGroupsResult;
import com.aliyun.openservices.ecs.model.Disk;
import com.aliyun.openservices.ecs.model.SecurityGroup;
import com.aliyun.openservices.ecs.model.SecurityGroups;

public class ECSSecurityGroupOperation extends ECSOperation {
  private static final String ACTION_CREATE_SECURITY_GROUP = "CreateSecurityGroup";
  private static final String ACTION_AUTHORIZE_SECURITY_GROUP = "AuthorizeSecurityGroup";
  private static final String ACTION_DESCRIBE_SECURITY_GROUP_ATTRIBUTE =
      "DescribeSecurityGroupAttribute";
  private static final String ACTION_DESCRIBE_SECURITY_GROUPS = "DescribeSecurityGroups";
  private static final String ACTION_REVOKE_SECURITY_GROUP = "RevokeSecurityGroup";
  private static final String ACTION_DELETE_SECURITY_GROUP = "DeleteSecurityGroup";

  public ECSSecurityGroupOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
  }

  public String createSecurityGroup(String regionId, String description) throws ECSException,
      ClientException {
    CodingUtils.assertStringNotNullOrEmpty(regionId, "regionId");
    CodingUtils.assertStringNotNullOrEmpty(description, "description");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("RegionId", regionId);
    params.put("Description", description);

    CreateSecurityGroupResult result =
        (CreateSecurityGroupResult) invoke(ACTION_CREATE_SECURITY_GROUP, HttpMethod.GET, params,
            CreateSecurityGroupResult.class);

    return result.getSecurityGroupId();
  }

  public void authorizeSecurityGroup(String securityGroupId, String regionId, String ipProtocol,
      String portRange, String sourceGroupId, String sourceCidrId, String policy, String nicType)
      throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty("securityGroupId", securityGroupId);
    CodingUtils.assertStringNotNullOrEmpty("regionId", regionId);
    CodingUtils.assertStringNotNullOrEmpty("ipProtocol", ipProtocol);
    CodingUtils.assertStringNotNullOrEmpty("portRange", portRange);
    CodingUtils.assertStringNotNullOrEmpty("sourceGroupId", sourceGroupId);
    CodingUtils.assertStringNotNullOrEmpty("sourceCidrId", sourceCidrId);
    CodingUtils.assertStringNotNullOrEmpty("policy", policy);
    CodingUtils.assertStringNotNullOrEmpty("nicType", nicType);

    Map<String, String> params = new LinkedHashMap<>();
    params.put("SecurityGroupId", securityGroupId);
    params.put("RegionId", regionId);
    params.put("IpProtocol", ipProtocol);
    params.put("PortRange", portRange);
    params.put("SourceGroupId", sourceGroupId);
    params.put("SourceCidrId", sourceCidrId);
    params.put("Policy", policy);
    params.put("NicType", nicType);

    invokeNoResult(ACTION_AUTHORIZE_SECURITY_GROUP, HttpMethod.GET, params);
  }

  public SecurityGroup describeSecurityGroupAttribute(String securityGroupId, String regionId)
      throws ECSException, ClientException {
    String nicType = "internet"; // or "intranet"
    return describeSecurityGroupAttribute(securityGroupId, regionId, nicType);
  }

  public SecurityGroup describeSecurityGroupAttribute(String securityGroupId, String regionId,
      String nicType) throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty("securityGroupId", securityGroupId);
    CodingUtils.assertStringNotNullOrEmpty("regionId", regionId);
    CodingUtils.assertStringNotNullOrEmpty("nicType", nicType);

    Map<String, String> params = new LinkedHashMap<>();
    params.put("SecurityGroupId", securityGroupId);
    params.put("RegionId", regionId);
    params.put("NicType", nicType);

    DescribeSecurityGroupAttributeResult result =
        (DescribeSecurityGroupAttributeResult) invoke(ACTION_DESCRIBE_SECURITY_GROUP_ATTRIBUTE,
            HttpMethod.GET, params, DescribeSecurityGroupAttributeResult.class);

    return result.getSecurityGroup();
  }


  public SecurityGroups describeSecurityGroups(String regionId) {
    return describeSecurityGroups(regionId, 1, 10);
  }

  public SecurityGroups describeSecurityGroups(String regionId, Integer pageNumber, Integer pageSize)
      throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(regionId, "regionId");
    CodingUtils.assertParameterNotNull(pageNumber, "pageNumber");
    CodingUtils.assertParameterNotNull(pageSize, "pageSize");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("RegionId", regionId);
    params.put("PageNumber", pageNumber.toString());
    params.put("PageSize", pageSize.toString());

    DescribeSecurityGroupsResult result =
        (DescribeSecurityGroupsResult) invoke(ACTION_DESCRIBE_SECURITY_GROUPS, HttpMethod.GET,
            params, DescribeSecurityGroupsResult.class);

    return result.getSecurityGroups();
  }

  public void revokeSecurityGroup(String securityGroupId, String regionId, String ipProtocol,
      String portRange, String sourceGroupId, String sourceCidrId, String policy, String nicType)
      throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty("securityGroupId", securityGroupId);
    CodingUtils.assertStringNotNullOrEmpty("regionId", regionId);
    CodingUtils.assertStringNotNullOrEmpty("ipProtocol", ipProtocol);
    CodingUtils.assertStringNotNullOrEmpty("portRange", portRange);
    CodingUtils.assertStringNotNullOrEmpty("sourceGroupId", sourceGroupId);
    CodingUtils.assertStringNotNullOrEmpty("sourceCidrId", sourceCidrId);
    CodingUtils.assertStringNotNullOrEmpty("policy", policy);
    CodingUtils.assertStringNotNullOrEmpty("nicType", nicType);

    Map<String, String> params = new LinkedHashMap<>();
    params.put("SecurityGroupId", securityGroupId);
    params.put("RegionId", regionId);
    params.put("IpProtocol", ipProtocol);
    params.put("PortRange", portRange);
    params.put("SourceGroupId", sourceGroupId);
    params.put("SourceCidrId", sourceCidrId);
    params.put("Policy", policy);
    params.put("NicType", nicType);

    invokeNoResult(ACTION_REVOKE_SECURITY_GROUP, HttpMethod.GET, params);
  }

  public void deleteSecurityGroup(String securityGroupId, String regionId) throws ECSException,
      ClientException {
    CodingUtils.assertStringNotNullOrEmpty("securityGroupId", securityGroupId);
    CodingUtils.assertStringNotNullOrEmpty("regionId", regionId);

    Map<String, String> params = new LinkedHashMap<>();
    params.put("SecurityGroupId", securityGroupId);
    params.put("RegionId", regionId);

    invokeNoResult(ACTION_DELETE_SECURITY_GROUP, HttpMethod.GET, params);
  }
}
