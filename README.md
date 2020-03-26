# SNS
社交网站技术方案

接口

1.登录      /user/login

请求参数:Post

| 参数名 | 类型 |
| ------ | ------ |
| user_logon | varchar(30) |
| user_password | varchar(50) |


返回参数:

| 参数名 | 类型 |
| ------ | ------ |
| user_id | char(11) |
| user_aiais | varchar | 
| user_image | varchar |


2.注册      /user/register

请求参数post

| 参数名 | 类型 |
| ------ | ------ |
| user_aiais | varchar |
| user_image | varchar | 
| user_logon | varchar |
| user_passwd |varchar |
| user_sex    | char    |
| user_work   | varchar |
| user_tel    | varchar |
| user_address | varchar |


返回参数:
    {
        "status":"success"
    }


3.修改个人资料  /user/update

同上



4.查询动态   /blog/search
    
    请求参数：
    
    {
    user_id:"123456"
    }

    返回参数：
    
    {
        [
            {
              user_id:"9999",
              user_image:"url",
              user_aiais:"clim",
              msg_content:"xczxcasfasf",
              "msg_id":"123",
              create_time:12-12 17:17,
              "is_own":1,
              img_group:[
                img_url:"1",
                img_url:"2",
                img_url:"3"
              ],
              is_like:0       //是否点赞
              like:"111"                //后台计算
              comment:"123"             //后台计算  ，共同好友总数,考虑建张快照表，每添加一个评论&回复就加一条数据
            },
        ]
    }

5.删除动态   /blog/delete

请求参数:
    {
        msg_id:"111",
    }
    
返回：
    {
        success
    }
    

6.点赞      /blog/like

    {
        msg_id:"11",
        user_id:"22"
    }
    
    返回:
    {
    success
    }

7.评论      /blog/reply

    {
        "msg_id":"111",
        "user_id":"123",
        "content":"123asdasd",
        
    }
    
    返回
    
    {
        time:"2019/21/32"
    }


8.查询好友列表   /friend/list
    
    {
        "user_id":"1608030"
    }
    
    返回
    {
    "code":200,
    "msg":"success",
    "data":{
      "friend":[
        {
        		    "user":
        			{"user_aiais": "ggg",
        			"user_image": "/static/img/2.jpg",
        			"user_id": 1608030150,
              "user_sex":1,
              "user_info": "fuck"
              }
        },
        {
        		  "user":
        			{"user_aiais": "ccc",
        			"user_image": "/static/img/3.jpg",
        			"user_id": 1608030151,
              "user_sex":2,
              "user_info": "fuck2"
              }
        }
        ]
  }}

9.删除好友      /friend/delete
    
    {
        "user_id":"zxc",
        "friend_id":"12312"
    }

    {
        success
    }
    
    
10.搜索查找好友  /user/find

    {
        "user_aiais"                //根据昵称查询
    }
    
    返回
    同8 /friend/list
    
11.申请添加好友  /friend/add
    1
    {
        "user_id":"自己的ID"
        "friend_id":"添加人的ID"
    }
    
    push信息过去，不在线考虑新建张表存储

    2. 查看好友申请 /friend/commit
    {
        "user_id":
    }
    
     返回同8 /friend/list

    
    
12.确认通过添加好友  /friend/confirm
    
    {
          "user_id":"自己的ID"
        "friend_id":"添加人的ID"    这里应该建多张表，申请时生成一个ID，接收PUSH时存放ID，确认时根据ID确认
    }                   //后台应该：1.添加好友，关注表互相增加
                                    2.更新A与B的  时间表
    
13.聊天室发送 /im/send
    
14.聊天室查找聊天记录/im/search

15.发布动态  /blog/send

16.上传图片 /img/upload

17.长连接 /socket/connnect

18.查询评论 /comment/search

    请求参数
    {
    "user_id":123,
    "msg_id":456
    }
    
    返回数据
    {
        
    }

20.添加评论 /comment/insert
    
    请求参数
    {
        content:123,
         user_id:123,
        msg_id:123,
    }
    
    返回success
    
统一下返回数据格式
    {
        code:200,
        msg:"success",
        data:{
            
        }
    }

