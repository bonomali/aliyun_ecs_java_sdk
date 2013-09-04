package com.aliyun.openservices.ecs.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SecurityGroup {
  @XmlElement(name = "SecurityGroupId")
  private int securityGroupId;

  @XmlElement(name = "RegionId")
  private String regionId;

  @XmlElement(name = "Description")
  private String description;

  @XmlElementWrapper(name = "Permissions")
  @XmlElement(name = "Permission")
  private List<Permission> permissions;

  public int getSecurityGroupId() {
    return securityGroupId;
  }

  public void setSecurityGroupId(int securityGroupId) {
    this.securityGroupId = securityGroupId;
  }

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }
}
