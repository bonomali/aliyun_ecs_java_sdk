package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.InstanceStatus;

@XmlRootElement(name = "DescribeInstanceStatusResponse")
public class DescribeInstanceStatusResult extends ECSResult {
  private InstanceStatus instanceStatus;

  public InstanceStatus getInstanceStatus() {
    return instanceStatus;
  }

  public void setInstanceStatus(InstanceStatus instanceStatus) {
    this.instanceStatus = instanceStatus;
  }
}
