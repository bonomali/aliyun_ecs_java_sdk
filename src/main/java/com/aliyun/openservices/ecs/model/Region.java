package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Region")
public class Region {
  @XmlElement(name = "RegionId")
  private String regionId;

  public String getRegionId() {
    return this.regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }
}
