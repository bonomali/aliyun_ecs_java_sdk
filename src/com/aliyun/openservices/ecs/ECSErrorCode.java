package com.aliyun.openservices.ecs;

public interface ECSErrorCode {
  public static final String ACCESS_DENIED = "AccessDenied";
  public static final String BUCKES_ALREADY_EXISTS = "BucketAlreadyExists";
  public static final String BUCKETS_NOT_EMPTY = "BucketNotEmpty";
  public static final String FILE_GROUP_TOO_LARGE = "FileGroupTooLarge";
  public static final String FILE_PART_STALE = "FilePartStale";
  public static final String INVALID_ARGUMENT = "InvalidArgument";
  public static final String INVALID_ACCESS_KEY_ID = "InvalidAccessKeyId";
  public static final String INVALID_BUCKET_NAME = "InvalidBucketName";
  public static final String INVALID_OBJECT_NAME = "InvalidObjectName";
  public static final String INVALID_PART = "InvalidPart";
  public static final String INVALID_PART_ORDER = "InvalidPartOrder";
  public static final String INTERNAL_ERROR = "InternalError";
  public static final String MISSING_CONTENT_LENGTH = "MissingContentLength";
  public static final String NO_SUCH_BUCKET = "NoSuchBucket";
  public static final String NO_SUCH_KEY = "NoSuchKey";
  public static final String NOT_IMPLEMENTED = "NotImplemented";
  public static final String PRECONDITION_FAILED = "PreconditionFailed";
  public static final String REQUEST_TIME_TOO_SKEWED = "RequestTimeTooSkewed";
  public static final String REQUEST_TIMEOUT = "RequestTimeout";
  public static final String SIGNATURE_DOES_NOT_MATCH = "SignatureDoesNotMatch";
  public static final String TOO_MANY_BUCKETS = "TooManyBuckets";
}
