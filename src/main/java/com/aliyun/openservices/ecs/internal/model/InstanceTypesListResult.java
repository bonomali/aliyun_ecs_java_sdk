package com.aliyun.openservices.ecs.internal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.InstanceType;

@XmlRootElement(name="InstanceTypes")
public class InstanceTypesListResult {
  @XmlElement(name="InstanceType")
  public List<InstanceType> InstanceTypes;
}

