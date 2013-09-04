package com.aliyun.openservices.ecs.model;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;

public class IpAddress {
  private String ipAddress;
  SubnetUtils utils;
  SubnetInfo subnetInfo;

  IpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    this.utils = new SubnetUtils(ipAddress);
    this.utils.setInclusiveHostCount(true); // For use w/ /32 CIDR subnets
    this.subnetInfo = this.utils.getInfo();
  }

  public boolean isInRange(String address) {
    return this.subnetInfo.isInRange(address);
  }
}
