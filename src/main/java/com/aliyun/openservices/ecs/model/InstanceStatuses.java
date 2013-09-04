package com.aliyun.openservices.ecs.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class InstanceStatuses {
  @XmlElement(name = "TotalCount")
  private int totalCount;

  @XmlElement(name = "PageNumber")
  private int pageNumber;

  @XmlElement(name = "PageSize")
  private int pageSize;

  @XmlElementWrapper(name = "InstanceStatuses")
  @XmlElement(name = "InstanceStatuse")
  private List<InstanceStatus> InstanceStatuse;

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

  public List<InstanceStatus> getInstanceStatuse() {
    return InstanceStatuse;
  }

  public void setInstanceStatuse(List<InstanceStatus> instanceStatuse) {
    InstanceStatuse = instanceStatuse;
  }
}
