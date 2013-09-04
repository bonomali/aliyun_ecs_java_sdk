package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AllocatePublicIpAddressResponse")
public class AllocatePublicIpAddressResult extends ECSResult {
  @XmlElement(name = "IpAddress")
  public String IpAddress;
}
