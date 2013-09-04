package com.aliyun.openservices.ecs.model;

public class SnapshotType {
  private int snapshotId;
  private String snapshotName;
  private int progress; // in precent, not including "%"
  private String creationTime; // in ISO8601 format, UTC time, YYYY-MM-DDThh:mmZ 
}
