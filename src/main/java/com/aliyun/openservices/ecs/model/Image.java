package com.aliyun.openservices.ecs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Image")
public class Image {
  @XmlElement(name = "ImageId")
  private String imageId;

  @XmlElement(name = "ImageVersion")
  private String imageVersion;

  @XmlElement(name = "Platform")
  private String platform;

  @XmlElement(name = "Description")
  private String description;

  @XmlElement(name = "Size")
  private int size;

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getImageVersion() {
    return imageVersion;
  }

  public void setImageVersion(String imageVersion) {
    this.imageVersion = imageVersion;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
