package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.InstanceMonitorData;

@XmlRootElement(name = "MonitorData")
public class MonitorData {
  @XmlElement(name = "InstanceMonitorData")
  private InstanceMonitorData instanceMonitorData;

  public InstanceMonitorData getInstanceMonitorData() {
    return instanceMonitorData;
  }

  public void setInstanceMonitorData(InstanceMonitorData instanceMonitorData) {
    this.instanceMonitorData = instanceMonitorData;
  }
}
