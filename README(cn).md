# 商品信息管理系统

## 一个简易的能执行基本操作的管理系统（首个web项目）

#### [商品信息系统介绍（个人博客）](http://hxblog.site/2018/04/16/%E7%AE%80%E6%98%93%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F-%E9%A6%96%E4%B8%AAWeb%E9%A1%B9%E7%9B%AE/)

由于是初学时首个 Web 项目，所以在项目开始之前曾经学习过[@codingXiaxw/CustomerManagement](https://github.com/codingXiaxw/CustomerManagement)这个项目，编程风格可能有一点类似，但是注释全都是在个人理解中总结而来

源代码中有极其详细的注释

## 版本 0.1

### 项目结构：
> Servlet + JSP + MySQL<br>
> MVC模式

### 开发流程：

<br/>1. 建包  
> dao  
> demo  
> page  
> product  
> service  
> servlet  

<br/>2. 导入 JAR 文件
> c3p0-0.9.5.2  
>commons-beanutils-1.9.3  
>commons-collections-3.2.2  
>commons-dbutils-1.7  
>commons-logging-1.2  
>itcast-tools-1.4  
>jsp-api  
>jstl-1.2  
>mchange-commons-java-0.2.11  
>mysql-connector-java-5.0.8-bin  
>servlet-api  

<br/>3. 建表  
>USE product;  
>CREATE TABLE product (  
>   id VARCHAR(50) NOT NULL PRIMARY KEY,<br>
    barcode VARCHAR(13) NOT NULL,<br>
    name VARCHAR(40) NOT NULL,<br>
    units VARCHAR(5) NOT NULL,<br>
    purchasePrice VARCHAR(10) NOT NULL,<br>
    salePrice VARCHAR(10) NOT NULL,<br>
    inventory VARCHAR(20) NOT NULL<br>
>);

<br/>4. 编程环境
>IntellijIDEA Ultimate + MySQL

<br/>5. 如何使用
> 1.Clone or download project [https://github.com/lihanxiang/ProductManagement.git](https://github.com/lihanxiang/ProductManagement.git)
><br/>2.在IDEA中打开项目,配置web项目信息[Issues#1](https://github.com/lihanxiang/p-m/issues/1)
><br/>3.部署到Tomcat（科学上网）[Issues#1](https://github.com/lihanxiang/p-m/issues/1)
><br/>4.Run, 打开浏览器并输入地址： **localhost:8080**

<br/>6. 功能介绍
>添加商品信息  
><br/>编辑商品信息  
><br/>删除商品信息  
><br/>显示所有商品信息  
><br/>查找商品信息  

### 项目的截图在上方的**Project screenshot**中  

## 版本0.2

### 改进用户界面

![](http://upload-images.jianshu.io/upload_images/3426615-57ab75c1c170884b.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 项目已完结
