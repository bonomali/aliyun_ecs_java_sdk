package com.aliyun.openservices.ecs.internal;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.aliyun.common.auth.RequestSigner;
import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.auth.ServiceSignature;
import com.aliyun.common.comm.RequestMessage;
import com.aliyun.common.utils.DateUtil;
import com.aliyun.common.utils.HttpUtil;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ecs.internal.SignUtils;

public class ECSRequestSigner implements RequestSigner {
  private String httpMethod;
  private String resourcePath;
  private ServiceCredentials credentials;
  private String ecsAction;

  public ECSRequestSigner(String httpMethod, String resourcePath, ServiceCredentials credentials) {
    assert (credentials != null);
    this.httpMethod = httpMethod;
    this.resourcePath = resourcePath;
    this.credentials = credentials;
  }

  public ECSRequestSigner(String ecsAction, ServiceCredentials credentials) {
    assert ((ecsAction != null) && (credentials != null));    
    this.ecsAction = ecsAction;
    this.credentials = credentials;
  }


  public void sign(RequestMessage request) throws ClientException {
    String secretAccessKey = this.credentials.getAccessKeySecret();
    String accessId = this.credentials.getAccessKeyId();

    if ((accessId.length() > 0) && (secretAccessKey.length() > 0)) {
      String canonicalString =
          SignUtils.buildCanonicalString(this.httpMethod, this.resourcePath, request, null);
      String signature =
          ServiceSignature.create().computeSignature(secretAccessKey, canonicalString);
      request.addHeader("Authorization", "OSS " + accessId + ":" + signature);
    } else if (accessId.length() > 0) {
      request.addHeader("Authorization", accessId);
    }
  }

  private static void addRequiredParameters(String action, Map<String, String> parameters,
      ServiceCredentials credentials) {
    ServiceSignature signer = ServiceSignature.create();
    parameters.put("Date", DateUtil.formatRfc822Date(new Date()));
    parameters.put("OTSAccessKeyId", credentials.getAccessKeyId());
    parameters.put("APIVersion", "1");
    parameters.put("SignatureMethod", signer.getAlgorithm());
    parameters.put("SignatureVersion", signer.getVersion());
  }

  private static void addSignature(String ecsAction, Map<String, String> parameters,
      ServiceCredentials credentials) throws UnsupportedEncodingException {
    parameters.put("Signature", getSignature(ecsAction, parameters, credentials));
  }

  private static String getSignature(String action, Map<String, String> parameters,
      ServiceCredentials credentials) throws UnsupportedEncodingException {
    String data =
        String.format("/%s\n%s",
            new Object[] {action, HttpUtil.paramToQueryString(sortMap(parameters), "utf-8")});

    return ServiceSignature.create().computeSignature(credentials.getAccessKeySecret(), data);
  }

  private static Map<String, String> sortMap(Map<String, String> unsortMap) {
    SortedSet<String> sortedKeys = new TreeSet(unsortMap.keySet());

    Map<String, String> sortedMap = new LinkedHashMap();
    for (String key : sortedKeys) {
      sortedMap.put(key, unsortMap.get(key));
    }
    return sortedMap;
  }
}
