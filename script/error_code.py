# -*- coding: utf8 -*-
"""
ECS-API-Reference.pdf
    阿里云弹性计算服务
    Aliyun Elastic Compute Service
    API参考手册
    （API版本2013-01-10）
        5.2 错误代码表
            5.2.1 客户端错误
            5.2.2 服务器端错误
"""
import sys

def camel_2_upper_case(line):
    length = len(line)
    result = ''
    if length > 0:
        result = line[0]
    for i in range(1, length):
        if line[i].isupper():
            result = '%s_%s' % (result, line[i])
        elif line[i] != '.':
            result = '%s%s' % (result, line[i])
    return result.upper()

def error_code_2_http_status_code(error_code):
    default_http_status_code = 400
    ec2hsc = {'FORBIDDEN':403, 'SIGNATURE_DOES_NOT_MATCH':403,
              'INSUFFICIENT_INSTANCE_CAPACITY':500, 'INTERNAL_ERROR':500,
              'SERVICE_UNAVAILABLE':503}
    if error_code in ec2hsc:
        return ec2hsc[error_code]
    return default_http_status_code

def error_code_2_side(error_code):
    server_side_error_codes = {'INSUFFICIENT_INSTANCE_CAPACITY':500,
                               'INTERNAL_ERROR':500,
                               'SERVICE_UNAVAILABLE':503}
    if error_code in server_side_error_codes:
        return 'Server'
    return 'Client'
    
if __name__ == '__main__':
    error_code_file = 'ErrorCode.txt'
    ecs_error_code_file = 'ECSErrorCode.txt'
    ecs_http_status_code_file = 'ECSHttpStatusCode.txt'
    with open(error_code_file) as inf:
        with open(ecs_error_code_file, 'w') as ecf:
            with open(ecs_http_status_code_file, 'w') as hscf:
                for line in inf:
                    line = line.strip()
                    if line:
                        error_code = camel_2_upper_case(line)
                        ecf.write('public static final String %s = "%s";' % (
                           error_code, line))
                        hscf.write('ErrorCode2HttpStatusCode.put(%s.%s, %d);' %
                               (error_code_2_side(error_code), error_code,
                                error_code_2_http_status_code(error_code)))
                        
                    ecf.write('\n')
                    hscf.write('\n')
