package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ots.internal.model.OTSResult;
import com.aliyun.openservices.ots.internal.model.TableGroupNameList;


@XmlRootElement(name="DescribeInstanceTypesResponse")
public class DescribeInstanceTypesResult extends ECSResult {
  @XmlElement
  public InstanceTypesListResult InstanceTypes;
}
