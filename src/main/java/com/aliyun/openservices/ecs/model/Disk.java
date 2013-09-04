package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Disk")
public class Disk {
  @XmlElement(name = "DiskId")
  private String diskId;

  @XmlElement(name = "Size")
  private int size; // in GB

  @XmlElement(name = "Type")
  private String type;

  public String getDiskId() {
    return this.diskId;
  }

  public void setDiskId(String diskId) {
    this.diskId = diskId;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
