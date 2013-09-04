package com.aliyun.openservices.ecs.internal;

import java.net.URI;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;

public class ECSSecurityGroupOperation extends ECSOperation {
  private static final String ACTION_CREATE_SECURITY_GROUP = "CreateSecurityGroup";
  private static final String ACTION_AUTHORIZE_SECURITY_GROUP = "AuthorizeSecurityGroup";
  private static final String ACTION_DESCRIBE_SECURITY_GROUP_ATTRIBUTE = "DescribeSecurityGroupAttribute";
  private static final String ACTION_DESCRIBE_SECURITY_GROUPS = "DescribeSecurityGroups";
  private static final String ACTION_REVOKE_SECURITY_GROUP = "RevokeSecurityGroup";
  private static final String ACTION_DELETE_SECURITY_GROUP = "DeleteSecurityGroup";

  public ECSSecurityGroupOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

}
