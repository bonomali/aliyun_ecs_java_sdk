package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.SecurityGroups;

@XmlRootElement(name = "DescribeSecurityGroupsResponse")
public class DescribeSecurityGroupsResult extends ECSResult {
  private SecurityGroups securityGroups;

  public SecurityGroups getSecurityGroups() {
    return securityGroups;
  }

  public void setSecurityGroups(SecurityGroups securityGroups) {
    this.securityGroups = securityGroups;
  }
}
