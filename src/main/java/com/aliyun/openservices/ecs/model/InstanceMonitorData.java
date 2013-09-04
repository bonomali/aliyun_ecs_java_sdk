package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "InstanceMonitorData")
public class InstanceMonitorData {
  @XmlElement(name = "InstanceId")
  private String instanceId;

  @XmlElement(name = "CPU")
  private int cpu; // useage percent, in %

  @XmlElement(name = "Memory")
  private int memory; // in MB

  @XmlElement(name = "IntranetRX")
  private int intranetRX; // in kbytes

  @XmlElement(name = "IntranetTX")
  private int intranetTX; // in kbytes

  @XmlElement(name = "IntranetFlow")
  private int intranetFlow; // in kbytes

  @XmlElement(name = "IntranetBandwidth")
  private int intranetBandwidth; // in kbytes/s

  @XmlElement(name = "InternetRX")
  private int internetRX; // in kbytes

  @XmlElement(name = "InternetTX")
  private int internetTX; // in kbytes

  @XmlElement(name = "InternetFlow")
  private int internetFlow; // in kbytes

  @XmlElement(name = "InternetBandwidth")
  private int internetBandwidth;// in kbytes/s

  @XmlElement(name = "IOPSRead")
  private int iopsRead; // in /s

  @XmlElement(name = "IOPSWrite")
  private int iopsWrite; // in /s

  @XmlElement(name = "BPSRead")
  private int bpsRead; // in byte/s

  @XmlElement(name = "BPSWrite")
  private int bpsWrite; // in byte/s

  @XmlElement(name = "TimeStamp")
  private String timeStamp; // in ISO8601 format

  public String getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  public int getCpu() {
    return cpu;
  }

  public void setCpu(int cpu) {
    this.cpu = cpu;
  }

  public int getMemory() {
    return memory;
  }

  public void setMemory(int memory) {
    this.memory = memory;
  }

  public int getIntranetRX() {
    return intranetRX;
  }

  public void setIntranetRX(int intranetRX) {
    this.intranetRX = intranetRX;
  }

  public int getIntranetTX() {
    return intranetTX;
  }

  public void setIntranetTX(int intranetTX) {
    this.intranetTX = intranetTX;
  }

  public int getIntranetFlow() {
    return intranetFlow;
  }

  public void setIntranetFlow(int intranetFlow) {
    this.intranetFlow = intranetFlow;
  }

  public int getIntranetBandwidth() {
    return intranetBandwidth;
  }

  public void setIntranetBandwidth(int intranetBandwidth) {
    this.intranetBandwidth = intranetBandwidth;
  }

  public int getInternetRX() {
    return internetRX;
  }

  public void setInternetRX(int internetRX) {
    this.internetRX = internetRX;
  }

  public int getInternetTX() {
    return internetTX;
  }

  public void setInternetTX(int internetTX) {
    this.internetTX = internetTX;
  }

  public int getInternetFlow() {
    return internetFlow;
  }

  public void setInternetFlow(int internetFlow) {
    this.internetFlow = internetFlow;
  }

  public int getInternetBandwidth() {
    return internetBandwidth;
  }

  public void setInternetBandwidth(int internetBandwidth) {
    this.internetBandwidth = internetBandwidth;
  }

  public int getIopsRead() {
    return iopsRead;
  }

  public void setIopsRead(int iopsRead) {
    this.iopsRead = iopsRead;
  }

  public int getIopsWrite() {
    return iopsWrite;
  }

  public void setIopsWrite(int iopsWrite) {
    this.iopsWrite = iopsWrite;
  }

  public int getBpsRead() {
    return bpsRead;
  }

  public void setBpsRead(int bpsRead) {
    this.bpsRead = bpsRead;
  }

  public int getBpsWrite() {
    return bpsWrite;
  }

  public void setBpsWrite(int bpsWrite) {
    this.bpsWrite = bpsWrite;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
}
