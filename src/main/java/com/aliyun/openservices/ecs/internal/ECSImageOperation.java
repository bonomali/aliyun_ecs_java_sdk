package com.aliyun.openservices.ecs.internal;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.comm.ServiceClient;
import com.aliyun.common.utils.CodingUtils;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.HttpMethod;
import com.aliyun.openservices.ecs.ECSException;
import com.aliyun.openservices.ecs.internal.model.DescribeImagesResult;
import com.aliyun.openservices.ecs.model.Image;

public class ECSImageOperation extends ECSOperation {
  private static final String ACTION_DESCRIBE_IMAGES = "DescribeImages";

  public ECSImageOperation(URI endpoint, ServiceClient client, ServiceCredentials cred) {
    super(endpoint, client, cred);
  }

  public List<Image> describeImages(String regionId, Integer pageNumber, Integer pageSize)
      throws ECSException, ClientException {
    CodingUtils.assertStringNotNullOrEmpty(regionId, "regionId");
    CodingUtils.assertParameterNotNull(pageNumber, "pageNumber");
    CodingUtils.assertParameterNotNull(pageSize, "pageSize");

    Map<String, String> params = new LinkedHashMap<>();
    params.put("RegionId", regionId);
    params.put("RageNumber", pageNumber.toString());
    params.put("RageSize", pageSize.toString());

    DescribeImagesResult result =
        (DescribeImagesResult) invoke(ACTION_DESCRIBE_IMAGES, HttpMethod.GET, params,
            DescribeImagesResult.class);

    return result.getImages();
  }
}
