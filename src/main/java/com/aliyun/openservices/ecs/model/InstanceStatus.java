package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InstanceStatus")
public class InstanceStatus {
  @XmlElement(name = "InstanceId")
  private String instanceId;

  @XmlElement(name = "Status")
  private String status;

  public String getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }  
}
