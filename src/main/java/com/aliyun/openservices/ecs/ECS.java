package com.aliyun.openservices.ecs;

import java.util.List;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ecs.model.Disk;
import com.aliyun.openservices.ecs.model.Image;
import com.aliyun.openservices.ecs.model.InstanceAttribute;
import com.aliyun.openservices.ecs.model.InstanceMonitorData;
import com.aliyun.openservices.ecs.model.InstanceStatus;
import com.aliyun.openservices.ecs.model.InstanceType;
import com.aliyun.openservices.ecs.model.Region;
import com.aliyun.openservices.ecs.model.SecurityGroup;
import com.aliyun.openservices.ecs.model.SecurityGroups;
import com.aliyun.openservices.ecs.model.Zone;

public interface ECS {
  public abstract void startInstance(String instanceId) throws ECSException, ClientException;

  public abstract void stopInstance(String instanceId) throws ECSException, ClientException;

  public abstract void stopInstance(String instanceId, String forceStop) throws ECSException,
      ClientException;

  public abstract void rebootInstance(String instanceId) throws ECSException, ClientException;

  public abstract void rebootInstance(String instanceId, String forceStop) throws ECSException,
      ClientException;

  public abstract void resetInstance(String instanceId) throws ECSException, ClientException;

  public abstract void resetInstance(String instanceId, String imageId, String diskType)
      throws ECSException, ClientException;

  public abstract void modifyInstanceAttribute(String instanceId, String password, String hostName,
      String securityGroupId) throws ECSException, ClientException;

  public abstract InstanceStatus describeInstanceStatus(String regionId, String zoneId)
      throws ECSException, ClientException;

  public abstract InstanceStatus describeInstanceStatus(String regionId, String zoneId,
      Integer pageNumber, Integer pageSize) throws ECSException, ClientException;

  public InstanceAttribute describeInstanceAttribute(String instanceId) throws ECSException,
      ClientException;

  public abstract List<Disk> describeInstanceDisks(String instanceId) throws ECSException,
      ClientException;

  public abstract List<Image> describeImages(String regionId, Integer pageNumber, Integer pageSize)
      throws ECSException, ClientException;

  public abstract String allocatePublicIpAddress(String instanceId) throws ECSException,
      ClientException;

  public abstract void releasePublicIpAddress(String publicIpAddress) throws ECSException,
      ClientException;

  public abstract String createSecurityGroup(String regionId, String description)
      throws ECSException, ClientException;

  public abstract void authorizeSecurityGroup(String securityGroupId, String regionId,
      String ipProtocol, String portRange, String sourceGroupId, String sourceCidrId,
      String policy, String nicType) throws ECSException, ClientException;

  public abstract SecurityGroup describeSecurityGroupAttribute(String securityGroupId,
      String regionId) throws ECSException, ClientException;

  public abstract SecurityGroup describeSecurityGroupAttribute(String securityGroupId,
      String regionId, String nicType) throws ECSException, ClientException;

  public abstract SecurityGroups describeSecurityGroups(String regionId) throws ECSException,
      ClientException;

  public abstract SecurityGroups describeSecurityGroups(String regionId, Integer pageNumber,
      Integer pageSize) throws ECSException, ClientException;

  public abstract void revokeSecurityGroup(String securityGroupId, String regionId,
      String ipProtocol, String portRange, String sourceGroupId, String sourceCidrId,
      String policy, String nicType) throws ECSException, ClientException;

  public abstract void deleteSecurityGroup(String securityGroupId, String regionId)
      throws ECSException, ClientException;

  public abstract List<Region> describeRegions() throws ECSException, ClientException;

  public abstract List<Zone> describeZones() throws ECSException, ClientException;

  public abstract InstanceMonitorData getMonitorData(String regionId, String instanceId)
      throws ECSException, ClientException;

  public abstract List<InstanceType> describeInstanceTypes() throws ECSException, ClientException;
}
