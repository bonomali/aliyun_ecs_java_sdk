package com.aliyun.openservices.ecs.model;

import java.util.List;

public class Permission {
  private String ipProtocol;
  private List<Integer> portRange;
  private List<String> sourceCidrIp;
  private SecurityGroupIds securityGroupIds;
  private String policy;
  private String nicType;
}
