# online_education

#### 介绍
(1)
在线教育系统，分为前台网站系统和后台运营平台，B2C模式。
前台用户系统包括课程、讲师、问答、文章几大大部分，使用了微服务技术架构，前后端分离开发。
后端的主要技术架构是：SpringBoot + SpringCloud + MyBatis-Plus + HttpClient + MySQL +
Maven+EasyExcel+ nginx
前端的架构是：Node.js + Vue.js +element-ui+NUXT+ECharts
其他涉及到的中间件包括Redis、阿里云OSS、阿里云视频点播
业务中使用了ECharts做图表展示，使用EasyExcel完成分类批量添加、注册分布式单点登录使用了JWT
（2）
项目前后端分离开发，后端采用SpringCloud微服务架构，持久层用的是MyBatis-Plus，微服务分库设
计，使用Swagger生成接口文档
接入了阿里云视频点播、阿里云OSS。
系统分为前台用户系统和后台管理系统两部分。
前台用户系统包括：首页、课程、名师、问答、文章。
后台管理系统包括：讲师管理、课程分类管理、课程管理、统计分析、Banner管理、订单管理、权限管
理等功能
![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/102957_72192424_5740554.png "68747470733a2f2f696d672d626c6f672e6373646e696d672e636e2f32303230303331343137343735353830352e706e673f782d6f73732d70726f636573733d696d6167652f77617465726d61726b2c747970655f5a6d46755a33706f5a57356e6147567064476b2c736.png")

#### 软件架构
软件架构说明
![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/103730_944e3204_5740554.png "68747470733a2f2f696d672d626c6f672e6373646e696d672e636e2f32303230303331343137343831333539392e706e673f782d6f73732d70726f636573733d696d6167652f77617465726d61726b2c747970655f5a6d46755a33706f5a57356e6147567064476b2c73686.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/105655_29ba6eb4_5740554.png "d2923fc7b11661b5f91a5a479fb1252.png")

#### 安装教程

  提示：必须安装redis、mysql8.0以上、nacos

1.  后端代码：https://gitee.com/weiqingbin666/online_education
    前台系统： https://gitee.com/weiqingbin666/online_education_front_system
    后台管理系统：https://gitee.com/weiqingbin666/online_education_front_admin
2.  将上面代码下载或克隆到iedea,IDEA会自动加载Maven 依赖包，初次加载会比较慢（根据自身网络情况而定）
3.  创建数据库guli并导入数据脚本sql/guli.sql
4.  修改各个模块的application.properties文件的MySQL配置信息
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/guli?characterEncoding=utf-8&serverTimezone=UTC
    spring.datasource.username=
    spring.datasource.password=
5.  修改redis配置信息、nacos配置信息

    

#### 效果图


![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114318_ea3c48ec_5740554.png "微信图片_20210627111828.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114333_b03ba2b8_5740554.png "微信图片_20210627111836.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114353_55140506_5740554.png "微信图片_20210627111841.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114404_4c551c5b_5740554.png "微信图片_20210627111846.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114414_ffd90c6f_5740554.png "微信图片_20210627111852.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114434_1542e46b_5740554.png "微信图片_20210627111901.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114447_e7d6a490_5740554.png "微信图片_20210627111906.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114504_5a193332_5740554.png "微信图片_20210627111912.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114607_6ad7b033_5740554.png "微信图片_20210627111917.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114514_50f95334_5740554.png "微信图片_20210627111922.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0627/114626_6ee90918_5740554.png "68747470733a2f2f696d672d626c6f672e6373646e696d672e636e2f32303230303331343139323134363139362e706e673f782d6f73732d70726f636573733d696d6167652f77617465726d61726b2c747970655f5a6d46755a33706f5a57356e6147567064476b2c736861.png")
#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
