package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class SecurityGroups {
  @XmlElement(name = "TotalCount")
  private int totalCount;

  @XmlElement(name = "PageNumber")
  private int pageNumber;

  @XmlElement(name = "PageSize")
  private int pageSize;

  @XmlElement(name = "RegionId")
  private String pegionId;
  
  @XmlElementWrapper(name = "SecurityGroups")
  @XmlElement(name = "SecurityGroup")
  private SecurityGroupItem securityGroupItem;

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getPegionId() {
    return pegionId;
  }

  public void setPegionId(String pegionId) {
    this.pegionId = pegionId;
  }

  public SecurityGroupItem getSecurityGroupItem() {
    return securityGroupItem;
  }

  public void setSecurityGroupItem(SecurityGroupItem securityGroupItem) {
    this.securityGroupItem = securityGroupItem;
  }  
}
