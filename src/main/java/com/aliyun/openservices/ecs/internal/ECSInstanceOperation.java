package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.HashMap;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ots.internal.OTSUtil;

public class ECSInstanceOperation extends ECSOperation {
  private static final String ACTION_START_INSTANCE = "StartInstance";
  private static final String ACTION_STOP_INSTANCE = "StopInstance";
  private static final String ACTION_REBOOT_INSTANCE = "RebootInstance";
  private static final String ACTION_RESET_INSTANCE = "ResetInstance";
  private static final String ACTION_MODIFY_INSTANCE_ATTRIBUTE = "ModifyInstanceAttribute";
  private static final String ACTION_DESCRIBE_INSTANCE_STATUS = "DescribeInstanceStatus";
  private static final String ACTION_DESCRIBE_INSTANCE_ATTRIBUTE = "DescribeInstanceAttribute";

  public ECSInstanceOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
    // TODO Auto-generated constructor stub
  }

  public void startInstance() throws ECSException, ClientException {

    CodingUtils.assertParameterNotNull(tableGroupName, "tableGroupName");
    OTSUtil.ensureNameValid(tableGroupName);
    
    java.util.Map<String, String> params = new HashMap();
    params.put("TableGroupName", tableGroupName);
    params.put("PartitionKeyType", pkType.toString());
    invokeNoResult("CreateTableGroup", HttpMethod.GET, params);
  }
  
  public void stopInstance() throws ECSException, ClientException {

  }
  
  public void rebootInstance() throws ECSException, ClientException {

  }
  
  public void resetInstance() throws ECSException, ClientException {

  }
}
