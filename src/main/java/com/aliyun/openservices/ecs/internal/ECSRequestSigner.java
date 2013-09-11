package com.aliyun.openservices.ecs.internal;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import com.aliyun.common.auth.RequestSigner;
import com.aliyun.common.auth.ServiceCredentials;
import com.aliyun.common.auth.ServiceSignature;
import com.aliyun.common.comm.RequestMessage;
import com.aliyun.common.utils.DateUtil;
import com.aliyun.openservices.ClientException;

public class ECSRequestSigner implements RequestSigner {
  private String httpMethod = ECSConstants.HTTP_METHOD;
  private String resourcePath;
  private ServiceCredentials credentials;
  private String ecsAction;
  private static final ServiceSignature serviceSignature = ServiceSignature.create();
  private static final String signatureMethod = serviceSignature.getAlgorithm();
  private static final String signatureVersion = serviceSignature.getVersion();
  private static final String SIGNATURE = "Signature";

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

  public String sign(String action, Map<String, String> parameters) throws Exception {
    assert (action != null && action.length() > 0);
    if (parameters == null) {
      parameters = new HashMap<String, String>();
    }
    // 加入公共请求参数
    addRequiredParameters(action, parameters, this.credentials);
    String canonicalizedQueryString = SignUtils.buildCanonicalString(parameters);
    String signature =
        getSignature(canonicalizedQueryString, this.credentials.getAccessKeySecret());
    return SignUtils.appendToCanonicalString(canonicalizedQueryString, SIGNATURE, signature);
  }


  /*
   * (non-Javadoc) Based on com.aliyun.openservices.oss.OSSRequestSigner
   * 
   * @see com.aliyun.common.auth.RequestSigner#sign(com.aliyun.common.comm.RequestMessage)
   */
  // public void sign(RequestMessage request) throws ClientException {
  // String secretAccessKey = this.credentials.getAccessKeySecret();
  // String accessId = this.credentials.getAccessKeyId();
  //
  // if ((accessId.length() > 0) && (secretAccessKey.length() > 0)) {
  // String canonicalString =
  // SignUtils.buildCanonicalString(this.httpMethod, this.resourcePath, request, null);
  // String signature =
  // ServiceSignature.create().computeSignature(secretAccessKey, canonicalString);
  // request.addHeader("Authorization", "ECS " + accessId + ":" + signature);
  // } else if (accessId.length() > 0) {
  // request.addHeader("Authorization", accessId);
  // }
  // }

  /*
   * (non-Javadoc) Based on com.aliyun.openservices.ots.OTSRequestSigner
   * 
   * @see com.aliyun.common.auth.RequestSigner#sign(com.aliyun.common.comm.RequestMessage)
   */
  public void sign(RequestMessage request) throws ClientException {
    try {
      addRequiredParameters(this.ecsAction, request.getParameters(), this.credentials);
    } catch (UnsupportedEncodingException e1) {
      // TODO Auto-generated catch block
      // e1.printStackTrace();
      throw (new ClientException(e1.getMessage(), e1));
    }
    try {
      addSignature(request.getParameters(), this.credentials);
    } catch (UnsupportedEncodingException e) {
      throw new ClientException("无法计算签名：" + e.getMessage());
    }
  }

  private static void addRequiredParameters(String action, Map<String, String> parameters,
      ServiceCredentials credentials) throws UnsupportedEncodingException {
    ServiceSignature signer = ServiceSignature.create();
    if (null == parameters) {
      parameters = new HashMap<String, String>();
    }
    parameters.put("Action", action);
    parameters.put("Format", ECSConstants.RESPONSE_FORMAT_DEFAULT);
    parameters.put("Version", ECSConstants.API_VERSION);
    parameters.put("AccessKeyId", credentials.getAccessKeyId());
    parameters.put("Timestamp", DateUtil.formatAlternativeIso8601Date(new Date()));
    parameters.put("SignatureMethod", signatureMethod);
    parameters.put("SignatureVersion", signatureVersion);
    parameters.put("SignatureNonce", UUID.randomUUID().toString());
  }

  private static void addSignature(Map<String, String> parameters, ServiceCredentials credentials)
      throws UnsupportedEncodingException {
    parameters.put("Signature", getSignature(parameters, credentials));
  }

  private static String getSignature(Map<String, String> parameters, ServiceCredentials credentials)
      throws UnsupportedEncodingException {
    return getSignature(SignUtils.buildCanonicalString(parameters), credentials);
    /*
     * String data = String.format( "/%s\n%s", new Object[] {action,
     * HttpUtil.paramToQueryString(sortMap(parameters), ECSConstants.DEFAULT_ENCODING)});
     * 
     * return ServiceSignature.create().computeSignature(credentials.getAccessKeySecret(), data);
     */
  }

  private static String getSignature(String canonicalizedQueryString, ServiceCredentials credentials)
      throws UnsupportedEncodingException {
    return getSignature(canonicalizedQueryString, credentials.getAccessKeySecret());
  }

  private static String getSignature(String canonicalizedQueryString, String accessKeySecert)
      throws UnsupportedEncodingException {
    // 注意accessKeySecret后面要加入一个字符"&"
    String signature =
        ServiceSignature.create().computeSignature(accessKeySecert + "&", canonicalizedQueryString);
    return signature;
  }

  private static Map<String, String> sortMap(Map<String, String> unsortMap) {
    SortedSet<String> sortedKeys = new TreeSet<>(unsortMap.keySet());

    Map<String, String> sortedMap = new LinkedHashMap<>();
    for (String key : sortedKeys) {
      sortedMap.put(key, unsortMap.get(key));
    }
    return sortedMap;
  }
}
