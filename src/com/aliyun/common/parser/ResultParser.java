package com.aliyun.common.parser;

import com.aliyun.common.comm.ResponseMessage;

public abstract interface ResultParser
{
  public abstract Object getObject(ResponseMessage paramResponseMessage)
    throws ResultParseException;
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.parser.ResultParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */