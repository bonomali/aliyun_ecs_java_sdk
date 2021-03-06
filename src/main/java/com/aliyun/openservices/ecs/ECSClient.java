package com.aliyun.openservices.ecs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.DefaultServiceClient;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ecs.internal.ECSConstants;
import com.aliyun.openservices.ecs.internal.ECSDatacenterOperation;
import com.aliyun.openservices.ecs.internal.ECSDiskOperation;
import com.aliyun.openservices.ecs.internal.ECSImageOperation;
import com.aliyun.openservices.ecs.internal.ECSInstanceOperation;
import com.aliyun.openservices.ecs.internal.ECSMonitorOperation;
import com.aliyun.openservices.ecs.internal.ECSNetworkOperation;
import com.aliyun.openservices.ecs.internal.ECSOtherOperation;
import com.aliyun.openservices.ecs.internal.ECSSecurityGroupOperation;
import com.aliyun.openservices.ecs.internal.ECSUtils;
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

public class ECSClient implements ECS {
  private ServiceCredentials credentials = new ServiceCredentials();
  private URI endpoint;
  private ServiceClient serviceClient;
  private static final Object LOCK = new Object();
  private ECSInstanceOperation instanceOperation;
  private ECSDiskOperation diskOperation;
  private ECSImageOperation imageOperation;
  private ECSNetworkOperation networkOperation;
  private ECSSecurityGroupOperation securityGroupOperation;
  private ECSDatacenterOperation datacenterOperation;
  private ECSMonitorOperation monitorOperation;
  private ECSOtherOperation otherOperation;

  public ECSClient(String accessKeyId, String accessKeySecret) {
    this(ECSConstants.DEFAULT_ECS_ENDPOINT, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret) {
    this(endpoint, accessKeyId, accessKeySecret, null);
  }

  public ECSClient(String endpoint, String accessKeyId, String accessKeySecret,
      ClientConfiguration config) {
    CodingUtils.assertStringNotNullOrEmpty(endpoint, "endpoint");
    CodingUtils.assertStringNotNullOrEmpty(accessKeyId, "accessKeyId");
    CodingUtils.assertStringNotNullOrEmpty(accessKeySecret, "accessKeySecret");
    try {
      if (!endpoint.startsWith("http://") || !endpoint.startsWith("https://")) {
        throw new IllegalArgumentException(
            ECSUtils.ECS_RESOURCE_MANAGER.getString("EndpointProtocolInvalid"));
      }
      this.endpoint = new URI(endpoint);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
    this.credentials = new ServiceCredentials(accessKeyId, accessKeySecret);
    this.serviceClient =
        new DefaultServiceClient(config != null ? config : new ClientConfiguration());
  }

  @Override
  public void startInstance(String instanceId) throws ECSException, ClientException {
    getInstanceOperation().startInstance(instanceId);
  }

  @Override
  public void stopInstance(String instanceId) throws ECSException, ClientException {
    getInstanceOperation().stopInstance(instanceId);
  }

  @Override
  public void stopInstance(String instanceId, String forceStop) throws ECSException,
      ClientException {
    getInstanceOperation().stopInstance(instanceId, forceStop);
  }

  @Override
  public void rebootInstance(String instanceId) throws ECSException, ClientException {
    getInstanceOperation().rebootInstance(instanceId);
  }

  @Override
  public void rebootInstance(String instanceId, String forceStop) throws ECSException,
      ClientException {
    getInstanceOperation().rebootInstance(instanceId, forceStop);
  }

  @Override
  public void resetInstance(String instanceId) throws ECSException, ClientException {
    getInstanceOperation().resetInstance(instanceId);
  }

  @Override
  public void resetInstance(String instanceId, String imageId, String diskType)
      throws ECSException, ClientException {
    getInstanceOperation().resetInstance(instanceId, imageId, diskType);
  }

  @Override
  public void modifyInstanceAttribute(String instanceId, String password, String hostName,
      String securityGroupId) throws ECSException, ClientException {
    getInstanceOperation().modifyInstanceAttribute(instanceId, password, hostName, securityGroupId);
  }

  @Override
  public InstanceStatus describeInstanceStatus(String regionId, String zoneId) throws ECSException,
      ClientException {
    return getInstanceOperation().describeInstanceStatus(regionId, zoneId);
  }

  @Override
  public InstanceStatus describeInstanceStatus(String regionId, String zoneId, Integer pageNumber,
      Integer pageSize) throws ECSException, ClientException {
    return getInstanceOperation().describeInstanceStatus(regionId, zoneId, pageNumber, pageSize);
  }

  @Override
  public InstanceAttribute describeInstanceAttribute(String instanceId) throws ECSException,
      ClientException {
    return getInstanceOperation().describeInstanceAttribute(instanceId);
  }

  @Override
  public List<Disk> describeInstanceDisks(String instanceId) throws ECSException, ClientException {
    return getDiskOperation().describeInstanceDisks(instanceId);
  }

  @Override
  public List<Image> describeImages(String regionId, Integer pageNumber, Integer pageSize)
      throws ECSException, ClientException {
    return getImageOperation().describeImages(regionId, pageNumber, pageSize);
  }

  @Override
  public String allocatePublicIpAddress(String instanceId) throws ECSException, ClientException {
    return getNetworkOperation().allocatePublicIpAddress(instanceId);
  }

  @Override
  public void releasePublicIpAddress(String publicIpAddress) throws ECSException, ClientException {
    getNetworkOperation().releasePublicIpAddress(publicIpAddress);
  }

  @Override
  public String createSecurityGroup(String regionId, String description) throws ECSException,
      ClientException {
    return getSecurityGroupOperation().createSecurityGroup(regionId, description);
  }

  @Override
  public void authorizeSecurityGroup(String securityGroupId, String regionId, String ipProtocol,
      String portRange, String sourceGroupId, String sourceCidrId, String policy, String nicType)
      throws ECSException, ClientException {
    getSecurityGroupOperation().authorizeSecurityGroup(securityGroupId, regionId, ipProtocol,
        portRange, sourceGroupId, sourceCidrId, policy, nicType);
  }

  @Override
  public SecurityGroup describeSecurityGroupAttribute(String securityGroupId, String regionId)
      throws ECSException, ClientException {
    return getSecurityGroupOperation().describeSecurityGroupAttribute(securityGroupId, regionId);
  }

  @Override
  public SecurityGroup describeSecurityGroupAttribute(String securityGroupId, String regionId,
      String nicType) throws ECSException, ClientException {
    return getSecurityGroupOperation().describeSecurityGroupAttribute(securityGroupId, regionId,
        nicType);
  }

  @Override
  public SecurityGroups describeSecurityGroups(String regionId) throws ECSException,
      ClientException {
    return getSecurityGroupOperation().describeSecurityGroups(regionId);
  }

  @Override
  public SecurityGroups describeSecurityGroups(String regionId, Integer pageNumber, Integer pageSize)
      throws ECSException, ClientException {
    return getSecurityGroupOperation().describeSecurityGroups(regionId, pageNumber, pageSize);
  }

  @Override
  public void revokeSecurityGroup(String securityGroupId, String regionId, String ipProtocol,
      String portRange, String sourceGroupId, String sourceCidrId, String policy, String nicType)
      throws ECSException, ClientException {
    getSecurityGroupOperation().revokeSecurityGroup(securityGroupId, regionId, ipProtocol,
        portRange, sourceGroupId, sourceCidrId, policy, nicType);
  }

  @Override
  public void deleteSecurityGroup(String securityGroupId, String regionId) throws ECSException,
      ClientException {
    getSecurityGroupOperation().deleteSecurityGroup(securityGroupId, regionId);
  }

  @Override
  public List<Region> describeRegions() throws ECSException, ClientException {
    return getDatacenterOperation().describeRegions();
  }

  @Override
  public List<Zone> describeZones() throws ECSException, ClientException {
    return getDatacenterOperation().describeZones();
  }

  @Override
  public InstanceMonitorData getMonitorData(String regionId, String instanceId)
      throws ECSException, ClientException {
    return getMonitorOperation().getMonitorData(regionId, instanceId);
  }

  @Override
  public List<InstanceType> describeInstanceTypes() throws ECSException, ClientException {
    return getOtherOperation().describeInstanceTypes();
  }



  public URI getEndpoint() {
    return this.endpoint;
  }

  public String getAccessKeyId() {
    return this.credentials.getAccessKeyId();
  }

  public String getAccessKeySecret() {
    return this.credentials.getAccessKeySecret();
  }

  private ServiceCredentials getCredentials() {
    return credentials;
  }

  private ServiceClient getServiceClient() {
    return serviceClient;
  }

  private ECSInstanceOperation getInstanceOperation() {
    if (this.instanceOperation == null) {
      synchronized (LOCK) {
        if (this.instanceOperation == null) {
          this.instanceOperation =
              new ECSInstanceOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return instanceOperation;
  }

  private ECSDiskOperation getDiskOperation() {
    if (this.diskOperation == null) {
      synchronized (LOCK) {
        if (this.diskOperation == null) {
          this.diskOperation =
              new ECSDiskOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return diskOperation;
  }

  private ECSImageOperation getImageOperation() {
    if (this.imageOperation == null) {
      synchronized (LOCK) {
        if (this.imageOperation == null) {
          this.imageOperation =
              new ECSImageOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return imageOperation;
  }

  private ECSNetworkOperation getNetworkOperation() {
    if (this.networkOperation == null) {
      synchronized (LOCK) {
        if (this.networkOperation == null) {
          this.networkOperation =
              new ECSNetworkOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return networkOperation;
  }

  private ECSSecurityGroupOperation getSecurityGroupOperation() {
    if (this.securityGroupOperation == null) {
      synchronized (LOCK) {
        if (this.securityGroupOperation == null) {
          this.securityGroupOperation =
              new ECSSecurityGroupOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return securityGroupOperation;
  }

  private ECSDatacenterOperation getDatacenterOperation() {
    if (this.datacenterOperation == null) {
      synchronized (LOCK) {
        if (this.datacenterOperation == null) {
          this.datacenterOperation =
              new ECSDatacenterOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return datacenterOperation;
  }

  private ECSMonitorOperation getMonitorOperation() {
    if (this.monitorOperation == null) {
      synchronized (LOCK) {
        if (this.monitorOperation == null) {
          this.monitorOperation =
              new ECSMonitorOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return monitorOperation;
  }

  private ECSOtherOperation getOtherOperation() {
    if (this.otherOperation == null) {
      synchronized (LOCK) {
        if (this.otherOperation == null) {
          this.otherOperation =
              new ECSOtherOperation(this.endpoint, this.serviceClient, this.credentials);
        }
      }
    }
    return otherOperation;
  }
}
