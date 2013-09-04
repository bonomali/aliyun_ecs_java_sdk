package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "InstanceStatus")
public class InstanceAttribute {
  @XmlElement(name = "InstanceId")
  private String instanceId;

  @XmlElement(name = "ImageId")
  private String imageId;

  @XmlElement(name = "RegionId")
  private String regionId;

  @XmlElement(name = "ZoneId")
  private String zoneId;

  @XmlElement(name = "InstanceType")
  private String instanceType;

  @XmlElement(name = "hostName")
  private String hostName;

  @XmlElement(name = "Status")
  private String status;

  @XmlElementWrapper(name = "PublicIpAddress")
  @XmlElement(name = "IpAddress")
  private String publicIpAddress;

  @XmlElementWrapper(name = "InnerIpAddress")
  @XmlElement(name = "IpAddress")
  private String innerIpAddress;

  @XmlElement(name = "InternetMaxBandwidthOut")
  private int internetMaxBandwidthOut;

  @XmlElement(name = "InternetMaxBandwidthIn")
  private int internetMaxBandwidthIn;

  @XmlElementWrapper(name = "SecurityGroupIds")
  @XmlElement(name = "SecurityGroupId")
  private String securityGroupId;

  public String getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }

  public String getInstanceType() {
    return instanceType;
  }

  public void setInstanceType(String instanceType) {
    this.instanceType = instanceType;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPublicIpAddress() {
    return publicIpAddress;
  }

  public void setPublicIpAddress(String publicIpAddress) {
    this.publicIpAddress = publicIpAddress;
  }

  public String getInnerIpAddress() {
    return innerIpAddress;
  }

  public void setInnerIpAddress(String innerIpAddress) {
    this.innerIpAddress = innerIpAddress;
  }

  public int getInternetMaxBandwidthOut() {
    return internetMaxBandwidthOut;
  }

  public void setInternetMaxBandwidthOut(int internetMaxBandwidthOut) {
    this.internetMaxBandwidthOut = internetMaxBandwidthOut;
  }

  public int getInternetMaxBandwidthIn() {
    return internetMaxBandwidthIn;
  }

  public void setInternetMaxBandwidthIn(int internetMaxBandwidthIn) {
    this.internetMaxBandwidthIn = internetMaxBandwidthIn;
  }

  public String getSecurityGroupId() {
    return securityGroupId;
  }

  public void setSecurityGroupId(String securityGroupId) {
    this.securityGroupId = securityGroupId;
  }
}
