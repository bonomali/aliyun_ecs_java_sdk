package com.aliyun.openservices.ecs.internal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.aliyun.openservices.ecs.model.Image;

@XmlRootElement(name = "DescribeImagesResponse")
public class DescribeImagesResult extends ECSResult {
  @XmlElement(name = "RegionId")
  private String regionId;

  @XmlElement(name = "TotalCount")
  private int TotalCount;

  @XmlElement(name = "PageNumber")
  private int PageNumber;

  @XmlElement(name = "PageSize")
  private int PageSize;

  @XmlElementWrapper(name = "Images")
  @XmlElement(name = "Image")
  private List<Image> images;

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public int getTotalCount() {
    return TotalCount;
  }

  public void setTotalCount(int totalCount) {
    TotalCount = totalCount;
  }

  public int getPageNumber() {
    return PageNumber;
  }

  public void setPageNumber(int pageNumber) {
    PageNumber = pageNumber;
  }

  public int getPageSize() {
    return PageSize;
  }

  public void setPageSize(int pageSize) {
    PageSize = pageSize;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}
