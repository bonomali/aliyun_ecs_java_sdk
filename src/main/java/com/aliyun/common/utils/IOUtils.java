package com.aliyun.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {
  public static String readStreamAsString(InputStream in, String charset) throws IOException {
    if (in == null) {
      return "";
    }
    Reader reader = null;
    Writer writer = new StringWriter();

    char[] buffer = new char[1024];
    String result;
    try {
      reader = new BufferedReader(new InputStreamReader(in, charset));

      int n;

      while ((n = reader.read(buffer)) > 0) {
        writer.write(buffer, 0, n);
      }

      result = writer.toString();
    } finally {
      in.close();
      if (reader != null) {
        reader.close();
      }
      if (writer != null) {
        writer.close();
      }
    }

    return result;
  }

  public static void safeClose(InputStream inputStream) {
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (IOException e) {}
    }
  }

  public static void safeClose(OutputStream outputStream) {
    if (outputStream != null) {
      try {
        outputStream.close();
      } catch (IOException e) {}
    }
  }
}


/*
 * Location:
 * E:\Projects\0.OS\AliyunSC\OSS_SDK\aliyun_java_sdk_20130604\aliyun-openservices-1.0.12.jar
 * Qualified Name: com.aliyun.common.utils.IOUtils JD-Core Version: 0.7.0-SNAPSHOT-20130630
 */
