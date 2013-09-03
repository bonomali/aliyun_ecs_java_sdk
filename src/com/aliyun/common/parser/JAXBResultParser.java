package com.aliyun.common.parser;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.aliyun.common.comm.ResponseMessage;
import com.aliyun.common.utils.ResourceManager;

public class JAXBResultParser implements ResultParser {
  private static final SAXParserFactory saxParserFactory;
  private Class<?> modelClass;
  private static HashMap<Object, JAXBContext> cachedContexts;

  static {
    saxParserFactory = SAXParserFactory.newInstance();

    cachedContexts = new HashMap();

    saxParserFactory.setNamespaceAware(true);
    saxParserFactory.setValidating(false);
  }

  public JAXBResultParser(Class<?> modelClass) {
    assert (modelClass != null);
    this.modelClass = modelClass;
  }

  public Object getObject(ResponseMessage response) throws ResultParseException {
    assert ((response != null) && (response.getContent() != null));
    try {
      if (!cachedContexts.containsKey(this.modelClass)) {
        initJAXBContext(this.modelClass);
      }

      assert (cachedContexts.containsKey(this.modelClass));
      JAXBContext jc = (JAXBContext) cachedContexts.get(this.modelClass);
      Unmarshaller um = jc.createUnmarshaller();

      return um.unmarshal(getSAXSource(response.getContent()));
    } catch (JAXBException e) {
      throw new ResultParseException(ResourceManager.getInstance("common").getString(
          "FailedToParseResponse"), e);
    } catch (SAXException e) {
      throw new ResultParseException(ResourceManager.getInstance("common").getString(
          "FailedToParseResponse"), e);
    } catch (ParserConfigurationException e) {
      throw new ResultParseException(ResourceManager.getInstance("common").getString(
          "FailedToParseResponse"), e);
    }
  }

  private static synchronized void initJAXBContext(Class<?> c) throws JAXBException {
    if (!cachedContexts.containsKey(c)) {
      JAXBContext jc = JAXBContext.newInstance(new Class[] {c});
      cachedContexts.put(c, jc);
    }
  }

  private static SAXSource getSAXSource(InputStream content) throws SAXException,
      ParserConfigurationException {
    SAXParser saxParser = saxParserFactory.newSAXParser();
    return new SAXSource(saxParser.getXMLReader(), new InputSource(content));
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.parser.JAXBResultParser JD-Core Version:
 * 0.7.0-SNAPSHOT-20130630
 */
