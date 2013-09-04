package com.aliyun.openservices.ecs.internal;

import com.aliyun.common.parser.JAXBResultParser;
import com.aliyun.common.parser.ResultParser;

public class ECSResultParserFactory {
  private static ECSResultParserFactory instance;

  public static ECSResultParserFactory createFactory() {
    if (null == instance) {
      synchronized (ECSResultParserFactory.class) {
        if (null == instance) {
          instance = new ECSResultParserFactory();
        }
      }
    }
    return instance;
  }

  public ResultParser createResultParser(Class<?> c) {
    return new JAXBResultParser(c);
  }
}
