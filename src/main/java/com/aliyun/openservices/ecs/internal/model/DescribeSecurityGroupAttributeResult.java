package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.SecurityGroup;

@XmlRootElement(name = "DescribeSecurityGroupAttributeResponse")
public class DescribeSecurityGroupAttributeResult extends ECSResult {
  private SecurityGroup securityGroup;

  public SecurityGroup getSecurityGroup() {
    return this.securityGroup;
  }

  public void setSecurityGroup(SecurityGroup securityGroup) {
    this.securityGroup = securityGroup;
  }
}
