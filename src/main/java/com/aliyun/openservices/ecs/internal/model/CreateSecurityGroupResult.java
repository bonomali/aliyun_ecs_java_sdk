package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "CreateSecurityGroupResponse")
public class CreateSecurityGroupResult extends ECSResult {
  @XmlElement(name = "SecurityGroupId")
  private String securityGroupId;

  public String getSecurityGroupId() {
    return securityGroupId;
  }

  public void setSecurityGroupId(String securityGroupId) {
    this.securityGroupId = securityGroupId;
  }
}
