package com.aliyun.common.parser;

import com.aliyun.common.utils.DateUtil;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxbDateSerializer
  extends XmlAdapter<String, Date>
{
  public String marshal(Date date)
    throws Exception
  {
    return DateUtil.formatRfc822Date(date);
  }
  
  public Date unmarshal(String date) throws Exception
  {
    return DateUtil.parseRfc822Date(date);
  }
}


/* Location:           E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name:     com.aliyun.common.parser.JaxbDateSerializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */