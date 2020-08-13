## SpringBoot 全家桶

Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。
该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。
通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域成为领导者。

**加粗提醒：**

1. master分支基于最新Spring Boot 2.3.0构建！

## 子项目列表

希望做到每个子项目都配有一篇博客文章的详细讲解 :point_right:

项目名称                               | 详情
--------------------------------------|------------------------------------------------------------------------------------------
springboot-aop-log                    | 使用AOP记录日志
springboot-easyexcel                  | 使用更好用的阿里巴巴表格工具EasyExcel
springboot-hibernate-validator        | [集成Hibernate Validator](https://www.cnblogs.com/ruiyeclub/p/13141467.html)
springboot-i18n                       | 集成i18n实现后台国际化
springboot-jpa                        | 整合Jpa实现增删改查
springboot-jwt                        | [集成JWT实现接口权限认证](https://www.cnblogs.com/ruiyeclub/p/12951145.html)
springboot-mail                       | [整合mail，封装MailUtils发送邮件](https://www.cnblogs.com/ruiyeclub/p/13394493.html)
springboot-mongodb                    | 集成MongoDB数据库，使用mongoTemplate操作MongoDB
springboot-mybatis-plus               | 集成Mybatis-Plus
springboot-oss-qiniu                  | 集成七牛云存储，封装QiniuUtil实现文件上传
springboot-redis                      | Redis数据库，使用redisTemplate操作Redis
springboot-scheduled                  | 定时任务
springboot-shiro                      | [集成Shiro权限管理](https://www.cnblogs.com/ruiyeclub/p/12469920.html)
springboot-swagger3                   | [集成Swagger3自动生成API文档](https://www.cnblogs.com/ruiyeclub/p/13334826.html)
springboot-undertow
springboot-upload                     | [实现本地文件上传](https://www.cnblogs.com/ruiyeclub/p/12732154.html)
springboot-validation                 | 集成validation参数验证

## 环境

* JDK 1.8
* Maven latest
* Spring Boot 2.3.0
* Intellij IDEA
* mysql 5.7
* mongodb
* git 版本管理
* redis 缓存

## 后续计划

1. 添加七牛云对象存储 [√]
2. 整合EasyExcel [√]
3. 整合mail发送邮件 [√]
4. 整合Jpa操作数据库 [√]
5. 整合quartz定时任务
6. 添加RabbitMQ消息队列
7. 整合i18国际化 [√]
8. Mybatis多数据源操作

## 问题反馈

1. 欢迎提issue一起完善这个项目
2. Email: ruiyeclub@foxmail.com
3. 个人主站: http://www.ruiyeclub.cn/