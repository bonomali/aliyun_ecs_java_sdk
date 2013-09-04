package com.aliyun.openservices.ecs.model;

public class InstanceMonitorData {
  private int instanceId;
  private int cpu; // useage percent, in %
  private int memory; // in MB
  private int intranetRX; // in kbytes
  private int intranetTX; // in kbytes
  private int intranetFlow; // in kbytes
  private int intranetBandwidth; // in kbytes/s
  private int internetRX; // in kbytes
  private int internetTX; // in kbytes
  private int internetFlow; // in kbytes
  private int internetBandwidth;// in kbytes/s
  private int iopsRead; // in /s
  private int iopsWrite; // in /s
  private int bpsRead; // in byte/s
  private int bpsWrite; // in byte/s
  private long timeStamp; // in ISO8601 format
}
