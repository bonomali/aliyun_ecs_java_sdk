package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InstanceType")
public class InstanceType {
  @XmlElement(name = "InstanceType")
  private String instanceTypeId;

  @XmlElement(name = "CpuCoreCount")
  private int cpuCoreCount;

  @XmlElement(name = "MemorySize")
  private float memorySize;

  public String getInstanceTypeId() {
    return instanceTypeId;
  }

  public void setInstanceTypeId(String instanceTypeId) {
    this.instanceTypeId = instanceTypeId;
  }

  public int getCpuCoreCount() {
    return cpuCoreCount;
  }

  public void setCpuCoreCount(int cpuCoreCount) {
    this.cpuCoreCount = cpuCoreCount;
  }

  public float getMemorySize() {
    return memorySize;
  }

  public void setMemorySize(float memorySize) {
    this.memorySize = memorySize;
  }
  
  
}
