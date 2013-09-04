package com.aliyun.openservices.ecs.internal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.Zone;

@XmlRootElement(name = "DescribeZonesResponse")
public class DescribeZonesResult extends ECSResult {
  @XmlElementWrapper(name = "Zones")
  @XmlElement(name = "Zone")
  private List<Zone> zones;

  public List<Zone> getZones() {
    return zones;
  }

  public void setZones(List<Zone> zones) {
    this.zones = zones;
  }  
}
