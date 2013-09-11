package com.aliyun.openservices.ecs.internal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="DescribeInstanceTypesResponse")
public class DescribeInstanceTypesResult extends ECSResult {
  @XmlElement
  public InstanceTypesListResult InstanceTypes;
}
