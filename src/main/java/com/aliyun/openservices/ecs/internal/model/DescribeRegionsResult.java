package com.aliyun.openservices.ecs.internal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.Region;

@XmlRootElement(name = "DescribeRegionsResponse")
public class DescribeRegionsResult extends ECSResult {
  @XmlElementWrapper(name = "Regions")
  @XmlElement(name = "Region")
  private List<Region> regions;

  public List<Region> getRegions() {
    return this.regions;
  }

  public void setRegions(List<Region> regions) {
    this.regions = regions;
  }
}
