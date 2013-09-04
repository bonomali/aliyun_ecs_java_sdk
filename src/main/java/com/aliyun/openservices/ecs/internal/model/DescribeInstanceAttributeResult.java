package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.InstanceAttribute;

@XmlRootElement(name = "DescribeInstanceAttributeResponse")
public class DescribeInstanceAttributeResult extends ECSResult {
  private InstanceAttribute instanceAttribute;

  public InstanceAttribute getInstanceAttribute() {
    return instanceAttribute;
  }

  public void setInstanceAttribute(InstanceAttribute instanceAttribute) {
    this.instanceAttribute = instanceAttribute;
  }
}
