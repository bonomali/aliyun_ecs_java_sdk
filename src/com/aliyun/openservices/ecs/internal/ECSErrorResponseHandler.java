package com.aliyun.openservices.ecs.internal;

import java.io.InputStream;

import com.aliyun.common.comm.ResponseHandler;
import com.aliyun.common.comm.ResponseMessage;
import com.aliyun.common.parser.JAXBResultParser;
import com.aliyun.common.parser.ResultParseException;
import com.aliyun.common.utils.ResourceManager;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.ecs.internal.ECSExceptionFactory;
import com.aliyun.openservices.ecs.internal.model.ECSErrorResult;

public class ECSErrorResponseHandler implements ResponseHandler {

  @Override
  public void handle(ResponseMessage responseData) throws ServiceException, ClientException {
    assert (responseData != null);
    if (responseData.isSuccessful()) {
      return;
    }

    InputStream errorStream = responseData.getContent();
    if (errorStream == null) {
      throw ECSExceptionFactory.createResponseException(ResourceManager.getInstance("common")
          .getString("ServerReturnsUnknownError"), null);
    }

    JAXBResultParser parser = new JAXBResultParser(ECSErrorResult.class);
    try {
      Object obj = parser.getObject(responseData);
      if ((obj instanceof ECSErrorResult)) {
        ECSErrorResult err = (ECSErrorResult) obj;
        throw ECSExceptionFactory.create(err);
      }
    } catch (ResultParseException e) {
      throw ECSExceptionFactory.createResponseException(ResourceManager.getInstance("common")
          .getString("ServerReturnsUnknownError"), null);
    }
  }

}
