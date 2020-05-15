# SNS为社交网站后端项目，前端项目（效果展示）传送门https://github.com/c-zzlin/SNS_Front

Ps:该项目为本人毕设项目



## 环境：

1. jdk 1.8
2. mysql 8.0
3. redis
4. rabbitmq
5. elk(版本需一致，logstash 开放5044端口作为日志输入)
6. 本地运行zipkin-server-2.10.4-exec(自行百度下载该jar包)



## 如何部署：

1.启动eureka项目

2.启动sns-getway项目

3.启动sns-provider下所有子项目

启动成功后，可通过localhost:8761 eureka服务中心 查看各服务端口信息



## 已完成模块:

1.登陆  （没有实现注册功能，登陆账号可从数据库添加用户来实现，登陆接口可查wiki:[https://github.com/c-zzlin/SNS/wiki/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3](https://github.com/c-zzlin/SNS/wiki/接口文档)

2.发布动态，动态展示

3.评论，回复评论，展示评论

4.点赞

5.聊天室私信功能

6.消息推送功能







如有问题可联系vx:605739593

or Email:605739593@qq.com

