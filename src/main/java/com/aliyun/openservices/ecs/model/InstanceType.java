package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InstanceType")
public class InstanceType {
  @XmlElement(name = "InstanceType")
  public String InstanceTypeId;

  @XmlElement(name = "CpuCoreCount")
  public int CpuCoreCount;

  @XmlElement(name = "MemorySize")
  public float MemorySize;
}
