package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Permission")
public class Permission {
  @XmlElement(name = "IpProtocol")
  private String ipProtocol;

  @XmlElement(name = "PortRange")
  private String portRange;

  @XmlElement(name = "SourceGroupId")
  private String sourceGroupId;

  @XmlElement(name = "Policy")
  private String policy;

  @XmlElement(name = "NicType")
  private String nicType;

  public String getIpProtocol() {
    return ipProtocol;
  }

  public void setIpProtocol(String ipProtocol) {
    this.ipProtocol = ipProtocol;
  }

  public String getPortRange() {
    return portRange;
  }

  public void setPortRange(String portRange) {
    this.portRange = portRange;
  }

  public String getSourceGroupId() {
    return sourceGroupId;
  }

  public void setSourceGroupId(String sourceGroupId) {
    this.sourceGroupId = sourceGroupId;
  }

  public String getPolicy() {
    return policy;
  }

  public void setPolicy(String policy) {
    this.policy = policy;
  }

  public String getNicType() {
    return nicType;
  }

  public void setNicType(String nicType) {
    this.nicType = nicType;
  }
}
