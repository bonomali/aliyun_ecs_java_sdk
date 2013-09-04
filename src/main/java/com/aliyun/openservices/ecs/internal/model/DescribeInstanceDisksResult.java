package com.aliyun.openservices.ecs.internal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.Disk;

@XmlRootElement(name="DescribeInstanceDisksResponse")
public class DescribeInstanceDisksResult extends ECSResult {
  @XmlElementWrapper(name = "Disks")
  @XmlElement(name = "Disk")
  private List<Disk> disks;

  public List<Disk> getDisks() {
    return disks;
  }

  public void setDisks(List<Disk> disks) {
    this.disks = disks;
  } 
}
