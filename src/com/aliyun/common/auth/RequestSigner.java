package com.aliyun.common.auth;

import com.aliyun.common.comm.RequestMessage;
import com.aliyun.openservices.ClientException;

public abstract interface RequestSigner
{
  public abstract void sign(RequestMessage paramRequestMessage)
    throws ClientException;
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.auth.RequestSigner
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */