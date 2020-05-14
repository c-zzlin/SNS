# SNS
社交网站技术方案

前端项目(含项目效果图)传送门https://github.com/c-zzlin/SNS_Front



环境部署：

1.需在本地部署elk(三个版本要一致)环境， 其中Logstash日志输入端口为5044

2.下载zipkin-server-2.10.4-exec，  在本地运行

3.数据库运行sns.sql文件

4.在本地部署redis

5.在本地部署rabiitmq

6.启动eureka、sns-geteway、以及sns-provider下所有子项目


部署成功后，使用user表的账号进行登陆，注册功能尚未实现




