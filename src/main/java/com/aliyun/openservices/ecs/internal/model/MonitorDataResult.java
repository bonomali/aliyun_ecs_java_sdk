package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetMonitorDataResponse")
public class MonitorDataResult extends ECSResult {
  @XmlElement(name = "MonitorData")
  private MonitorData monitorData;

  public MonitorData getMonitorData() {
    return monitorData;
  }

  public void setMonitorData(MonitorData monitorData) {
    this.monitorData = monitorData;
  }
}
