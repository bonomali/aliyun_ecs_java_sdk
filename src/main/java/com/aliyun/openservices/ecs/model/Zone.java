package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Zone")
public class Zone {
  @XmlElement(name = "ZoneId")
  private String zoneId;

  public String getZoneId() {
    return this.zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }
}
