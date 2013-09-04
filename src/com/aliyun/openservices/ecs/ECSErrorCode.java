package com.aliyun.openservices.ecs;

import java.util.HashMap;
import java.util.Map;

public interface ECSErrorCode {
  public static interface Client {
    public static final String UNSUPPORTED_OPERATION = "UnsupportedOperation";
    public static final String NO_SUCH_VERSION = "NoSuchVersion";
    public static final String MISSING_PARAMETER = "MissingParameter";
    public static final String INVALID_PARAMETER = "InvalidParameter";
    public static final String THROTTLING = "Throttling";
    public static final String INVALID_ACCESS_KEY_ID_NOT_FOUND = "InvalidAccessKeyId.NotFound";
    public static final String FORBIDDEN = "Forbidden";
    public static final String SIGNATURE_DOES_NOT_MATCH = "SignatureDoesNotMatch";
    public static final String SIGNATURE_NONCE_USED = "SignatureNonceUsed";
    public static final String IDEMPOTENT_PARAMETER_MISMATCH = "IdempotentParameterMismatch";
    public static final String INCORRECT_INSTANCE_STATUS = "IncorrectInstanceStatus";
    public static final String INSTANCE_MOUNTED_SNAPSHOT = "InstanceMountedSnapshot";
    public static final String INVALID_SECURITY_GROUP_STATUS = "InvalidSecurityGroupStatus";
    public static final String INVALID_SECURITY_GROUP_IN_USE = "InvalidSecurityGroup.InUse";
    public static final String SECURITY_GROUP_LIMIT_EXCEEDED = "SecurityGroupLimitExceeded";
    public static final String SECURITY_GROUP_RULE_LIMIT_EXCEEDED =
        "SecurityGroupRuleLimitExceeded";
    public static final String SECURITY_GROUP_INSTANCE_LIMIT_EXCEED =
        "SecurityGroupInstanceLimitExceed";
    public static final String INVALID_SNAPSHOT_IN_USE = "InvalidSnapshot.InUse";
    public static final String INVALID_INSTANCE_ID_NOT_FOUND = "InvalidInstanceId.NotFound";
    public static final String INVALID_INSTANCE_ID_MALFORMED = "InvalidInstanceId.Malformed";
    public static final String INVALID_INSTANCE_TYPE_NOT_FOUND = "InvalidInstanceType.NotFound";
    public static final String INVALID_REGION_ID_NOT_FOUND = "InvalidRegionId.NotFound";
    public static final String INVALID_ZONE_ID_NOT_FOUND = "InvalidZoneId.NotFound";
    public static final String INVALID_DISK_ID_NOT_FOUND = "InvalidDiskId.NotFound";
    public static final String INVALID_DISK_ID_MALFORMED = "InvalidDiskId.Malformed";
    public static final String INVALID_DISK_NOT_READY = "InvalidDisk.NotReady";
    public static final String INVALID_DISK_TYPE_NOT_FOUND = "InvalidDiskType.NotFound";
    public static final String INVALID_SNAPSHOT_ID_NOT_FOUND = "InvalidSnapshotId.NotFound";
    public static final String INVALID_SNAPSHOT_ID_MALFORMED = "InvalidSnapshotId.Malformed";
    public static final String INVALID_SNAPSHOT_UNBOOTABLE = "InvalidSnapshot.Unbootable";
    public static final String INVALID_SNAPSHOT_NOT_READY = "InvalidSnapshot.NotReady";
    public static final String INVALID_PASSWORD_MALFORMED = "InvalidPassword.Malformed";
    public static final String INVALID_PUBLIC_IP_ADDRESS_NOT_FOUND =
        "InvalidPublicIpAddress.NotFound";
    public static final String INVALID_PUBLIC_IP_ADDRESS_MALFORMED =
        "InvalidPublicIpAddress.Malformed";
    public static final String INVALID_HOST_NAME_MALFORMED = "InvalidHostName.Malformed";
    public static final String INVALID_IMAGE_ID_NOT_FOUND = "InvalidImageId.NotFound";
    public static final String INVALID_IMAGE_ID_MALFORMED = "InvalidImageId.Malformed";
    public static final String INVALID_SECURITY_GROUP_ID_MALFORMED =
        "InvalidSecurityGroupId.Malformed";
    public static final String INVALID_SECURITY_GROUP_ID_NOT_FOUND =
        "InvalidSecurityGroupId.NotFound";
    public static final String INVALID_SOURCE_GROUP_ID_NOT_FOUND = "InvalidSourceGroupId.NotFound";
    public static final String INVALID_SOURCE_GROUP_ID_MALFORMED = "InvalidSourceGroupId.Malformed";
    public static final String INVALID_SECURITY_GROUP_DESCRIPTION =
        "InvalidSecurityGroupDescription";
    public static final String INVALID_IP_PROTOCOL = "InvalidIpProtocol";
    public static final String INVALID_DISK_SIZE_MALFORMED = "InvalidDiskSize.Malformed";
    public static final String INVALID_DISK_SIZE_EXCEEDED = "InvalidDiskSize.Exceeded";
    public static final String INVALID_INTERNET_MAX_BANDWIDTH_MALFORMED =
        "InvalidInternetMaxBandwidth.Malformed";
    public static final String INVALID_SOURCE_CIDR_IP_MALFORMED = "InvalidSourceCidrIp.Malformed";
    public static final String INVALID_PORT_RANGE_MALFORMED = "InvalidPortRange.Malformed";
    public static final String INVALID_POLICY_MALFORMED = "InvalidPolicy.Malformed";
    public static final String INVALID_NIC_TYPE_MALFORMED = "InvalidNicType.Malformed";
  }

  public static interface Server {
    public static final String INSUFFICIENT_INSTANCE_CAPACITY = "InsufficientInstanceCapacity";
    public static final String INTERNAL_ERROR = "InternalError";
    public static final String SERVICE_UNAVAILABLE = "ServiceUnavailable";
  }


  public static Map<String, Integer> ErrorCode2HttpStatusCode = new HashMap<String, Integer>() {
    /**
     * 
     */
    private static final long serialVersionUID = -698481294690974176L;
    {
      ErrorCode2HttpStatusCode.put(Client.UNSUPPORTED_OPERATION, 400);
      ErrorCode2HttpStatusCode.put(Client.NO_SUCH_VERSION, 400);
      ErrorCode2HttpStatusCode.put(Client.MISSING_PARAMETER, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_PARAMETER, 400);
      ErrorCode2HttpStatusCode.put(Client.THROTTLING, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_ACCESS_KEY_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.FORBIDDEN, 403);
      ErrorCode2HttpStatusCode.put(Client.SIGNATURE_DOES_NOT_MATCH, 403);
      ErrorCode2HttpStatusCode.put(Client.SIGNATURE_NONCE_USED, 400);
      ErrorCode2HttpStatusCode.put(Client.IDEMPOTENT_PARAMETER_MISMATCH, 400);
      ErrorCode2HttpStatusCode.put(Client.INCORRECT_INSTANCE_STATUS, 400);
      ErrorCode2HttpStatusCode.put(Client.INSTANCE_MOUNTED_SNAPSHOT, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SECURITY_GROUP_STATUS, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SECURITY_GROUP_IN_USE, 400);
      ErrorCode2HttpStatusCode.put(Client.SECURITY_GROUP_LIMIT_EXCEEDED, 400);
      ErrorCode2HttpStatusCode.put(Client.SECURITY_GROUP_RULE_LIMIT_EXCEEDED, 400);
      ErrorCode2HttpStatusCode.put(Client.SECURITY_GROUP_INSTANCE_LIMIT_EXCEED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SNAPSHOT_IN_USE, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_INSTANCE_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_INSTANCE_ID_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_INSTANCE_TYPE_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_REGION_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_ZONE_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_DISK_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_DISK_ID_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_DISK_NOT_READY, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_DISK_TYPE_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SNAPSHOT_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SNAPSHOT_ID_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SNAPSHOT_UNBOOTABLE, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SNAPSHOT_NOT_READY, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_PASSWORD_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_PUBLIC_IP_ADDRESS_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_PUBLIC_IP_ADDRESS_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_HOST_NAME_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_IMAGE_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_IMAGE_ID_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SECURITY_GROUP_ID_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SECURITY_GROUP_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SOURCE_GROUP_ID_NOT_FOUND, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SOURCE_GROUP_ID_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SECURITY_GROUP_DESCRIPTION, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_IP_PROTOCOL, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_DISK_SIZE_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_DISK_SIZE_EXCEEDED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_INTERNET_MAX_BANDWIDTH_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_SOURCE_CIDR_IP_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_PORT_RANGE_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_POLICY_MALFORMED, 400);
      ErrorCode2HttpStatusCode.put(Client.INVALID_NIC_TYPE_MALFORMED, 400);

      ErrorCode2HttpStatusCode.put(Server.INSUFFICIENT_INSTANCE_CAPACITY, 500);
      ErrorCode2HttpStatusCode.put(Server.INTERNAL_ERROR, 500);
      ErrorCode2HttpStatusCode.put(Server.SERVICE_UNAVAILABLE, 503);
    }
  };
}
