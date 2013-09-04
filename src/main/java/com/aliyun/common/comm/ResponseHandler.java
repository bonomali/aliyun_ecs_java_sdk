package com.aliyun.common.comm;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;

public abstract interface ResponseHandler {
  public abstract void handle(ResponseMessage paramResponseMessage) throws ServiceException,
      ClientException;
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.comm.ResponseHandler JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
