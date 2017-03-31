员工投票系统简介.使用
软件概述
用于公司评选优秀员工的无记名投票系统
软件信息: v 0.1
开发环境
语言版本：oracle java 1.8  64
数据库版本：oracle mysql  5.7 15-1
IDE ：   Eclipse Java EE IDE for Web Developers Version : Neon.2
容器：Tomcat   Version：8.5
MVC设计
持久层
myBatis 
70.	/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;  
71.	/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;  

	
业务层
Spring
展示层
Spring MVC
页面
Material Design设计规范
Materializecss框架 
栅格系统






软件功能点
员工界面
 为了符合手机访问的特点使用了H5设计规范，符合市面上绝大多数的移动设备访问（测试设备：Apple iphone 5S）
登陆
      
投票
显示已经投了票的员工
显示员工得票数
           

提交建议
 



管理后台界面
考虑到后台展示的信息比较多，所以使用了PC端显示，但是也做了一定的移动端优化
管理员登陆（默认账号  admin（不可修改）  密码：justice（系统内部可以修改）


员工管理模块
分页展示所有员工（得票情况一并查出）
    
按照名字搜索员工信息
 


修改员工信息（头像还有姓名）

 
添加员工            


删除员工
 

查看员工本月投票情况
 


投票统计模块
展示本月员工得票情况（每月第一天凌晨三点开始的情况
 
柱状图和南丁格尔图切换展示  
 



按年份展示查询年份的员工得票总计并排序
 


意见管理模块
分页展示员工建议  按照投递时间排序  保证最新的投递在最前面
 
展示建议详情

密码修改模块
 

