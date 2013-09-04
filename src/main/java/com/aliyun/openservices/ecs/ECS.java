package com.aliyun.openservices.ecs;

import java.util.List;

import com.aliyun.openservices.ecs.model.Image;
import com.aliyun.openservices.ecs.model.InstanceMonitorData;
import com.aliyun.openservices.ecs.model.InstanceType;
import com.aliyun.openservices.ecs.model.Region;
import com.aliyun.openservices.ecs.model.SecurityGroup;
import com.aliyun.openservices.ecs.model.SecurityGroups;
import com.aliyun.openservices.ecs.model.Zone;

public interface ECS {  
  public abstract List<Image> describeImages(String regionId, Integer pageNumber, Integer pageSize);
  
  public abstract String allocatePublicIpAddress(String instanceId);

  public abstract void releasePublicIpAddress(String publicIpAddress);
  
  public abstract String createSecurityGroup(String regionId, String description);
  
  public abstract void authorizeSecurityGroup(String securityGroupId, String regionId, String ipProtocol,
                                     String portRange, String sourceGroupId, String sourceCidrId, String policy, String nicType);
  
  public abstract SecurityGroup describeSecurityGroupAttribute(String securityGroupId, String regionId);
  
  public abstract SecurityGroup describeSecurityGroupAttribute(String securityGroupId, String regionId,
                                                      String nicType);
  
  public abstract SecurityGroups describeSecurityGroups(String regionId);
  
  public abstract SecurityGroups describeSecurityGroups(String regionId, Integer pageNumber, Integer pageSize);
  
  public abstract void revokeSecurityGroup(String securityGroupId, String regionId, String ipProtocol,
                                  String portRange, String sourceGroupId, String sourceCidrId, String policy, String nicType);
  
  public abstract void deleteSecurityGroup(String securityGroupId, String regionId);
  
  public abstract List<Region> describeRegions();
  
  public abstract List<Zone> describeZones();
  
  public abstract InstanceMonitorData getMonitorData(String regionId, String instanceId);
  
  public abstract List<InstanceType> describeInstanceTypes();
}
