aliyun_ecs_java_sdk
===================

[Aliyun_ecs_java_sdk](https://github.com/kloudkl/aliyun_ecs_java_sdk) is the Java SDK for [Aliyun ECS (Elastic Compute Service)](http://www.aliyun.com/product/ecs/). Aliyun_ecs_java_sdk is simple, can be used with any JVM programming language, and is a lot of fun to use!

## Contributors

* Kai Li ([@kloudkl](https://github.com/kloudkl))

## Documentation

[Official API Documentation](http://dev.aliyun.com/read.php?tid=41) and [API specifications](http://oss.aliyuncs.com/aliyun_portal_storage/dasai/2013/ECS/ECS-API-Reference.pdf) can be found on the [Aliyun ECS API](http://dev.aliyun.com/read.php?tid=41) and the [Aliyun Elastic Compute Service API Reference](http://oss.aliyuncs.com/aliyun_portal_storage/dasai/2013/ECS/ECS-API-Reference.pdf).

## 作品特性说明
1、	作品名称

Aliyun ECS (Elastic Compute Service) Java SDK。

2、	关键特性

实现了阿里云弹性计算服务API参考手册（2013-01-10版本）全部接口。

3、	创新点

很大一部分阿里云开发者采用Java作为其应用开发语言，因此他们需要各项云服务的Java SDK。ECS作为整个阿里云平台非常核心的云服务器弹性计算服务，用户基础非常广泛。两方面因素决定ECS Java SDK的需求是非常现实而强烈的，而目前还没有提供ECS API全部功能的项目，特别是开源项目。

本项目的实现代码完全开源，采用Apache License Version 2.0,。代码遵照与Aliyun其它服务Java SDK相同的规范，例如OSS Java SDK和OTS Java SDK。公共功能基于官方标准代码，例如如下几个命名空间com.aliyun.com.{auth/comm/parser/utils}，而为AP开发的代码也类似地位于com.aliyun.openservices命名空间下，因此与其它API Java SDK的兼容性和可集成度很高。

实现的接口包括实例、磁盘、镜像、网络、安全组、数据中心、监控和其他相关接口。调用方式的请求、响应和签名严格遵照API参考手册规定，符合接口调用安全性要求。实现了所有数据类型，序列化和反序列化为XML。实现了错误代码。调用方式完全符合参考手册规定。
因此，本项目与官方API Java SDK高度一致，为应用开发者提供了调用Aliyun ECS所有各种服务功能所必需的API接口，简化了其项目代码，提高了开发效率。


## 作品与云计算关联性说明.
1、	作品使用到阿里云哪些产品或服务

本作品为阿里云弹性计算服务开发了Java SDK。

2、	阿里云API接口版本

本作品的Java SDK实现遵照的API 参考手册API版本为2013-01-10（为便于评审，本作品评审参考材料附录了该版本的《ECS-API-Reference.pdf》）。

3、	如何使用阿里云产品或服务实现作品功能

本作品开发的Java SDK遵照API 参考手册的规定封装公共参数与各项服务接口特定参数，生成请求签名，构造请求结构，调用阿里云弹性计算服务，并按照参考手册定义的数据类型和错误代码解析返回结果。

4、	使用阿里云产品后解决了什么问题

本项目与官方API Java SDK高度一致，为应用开发者提供了调用Aliyun ECS所有各种服务功能所必需的API接口，简化了其项目代码，提高了开发效率。开发者主要需要创建ECSClient实例，并调用其各接口即可简单方便地实现与ECS服务的交互。

本SDK提供的接口功能如下：

a)	实例相关接口

i.	启动实例startInstance

ii.	停止实例stopInstance

iii.	重启实例rebootInstance

iv.	重置实例resetInstance

v.	修改实例属性modifyInstanceAttribute

vi.	查询实例状态describeInstanceStatus

vii.	查询实例信息describeInstanceAttribute

b)	磁盘相关接口

i.	查询实例磁盘列表describeInstanceDisks

c)	镜像相关接口

i.	查询可用镜像describeImages

d)	网络相关接口

i.	分配公网IP地址allocatePublicIpAddress

ii.	释放公网IP地址releasePublicIpAddress

e)	安全组相关接口

i.	创建安全组createSecurityGroup

ii.	授权安全组权限authorizeSecurityGroup

iii.	查询安全组规则describeSecurityGroupAttribute

iv.	查询安全组列表describeSecurityGroups

v.	撤销安全组规则revokeSecurityGroup

vi.	删除安全组deleteSecurityGroup

f)	数据中心相关接口

i.	查询可用数据中心describeRegions

ii.	查询Zone信息describeZones

g)	监控相关接口

i.	查看云服务器监控信息getMonitorData

h)	其他接口

i.	查询实例资源规格列表describeInstanceTypes

本项目完全开源，后续将随着用户的反馈意见和ECS API参考手册的修订不断增强完善SDK实现。


## Getting help

Feel free to ask questions on [Aliyun ECS BBS](http://bbs.aliyun.com/thread.php?fid=127).

You can also come to Aliyun_ecs_java_sdk project website to [create a new issue](https://github.com/kloudkl/aliyun_ecs_java_sdk/issues/) and [send a pull request](https://github.com/kloudkl/aliyun_ecs_java_sdk/pulls/). 

## Acknowledgements

Aliyun 云服务器（Elastic Compute Service, ECS）是一种处理能力可弹性伸缩的计算服务，其管理方式比物理服务器更简单高效。云服务器帮助您快速构建更稳定、安全的应用，降低开发运维的难度和整体IT成本，使您能够更专注于核心业务创新。

Aliyun Elastic Compute Service (ECS) is a scalable computing service. It is simpler and more efficient to manage than the physical servers. The ECS enables you to focus on 
your core business innovation by helping you quickly build more stable and secure applications, easing development and operation, and reducing the overall IT cost. 

## License

The use and distribution terms for this software are covered by the
Apache License Version 2.0, January 2004 (http://www.apache.org/licenses/)
which can be found in the file LICENSE at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.
You must not remove this notice, or any other, from this software.

