package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;

public class ECSResult {
  private String requestId;
//  private String hostId;

  public String getRequestId() {
    return this.requestId;
  }

  @XmlElement(name = "RequestId")
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

//  public String getHostId() {
//    return this.hostId;
//  }
//
//  @XmlElement(name = "HostId")
//  public void setHostId(String hostId) {
//    this.hostId = hostId;
//  }
}
