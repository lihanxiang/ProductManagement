# What is the ProductManagement?
[![](https://travis-ci.org/Alamofire/Alamofire.svg?branch=master)](https://www.jianshu.com/p/737effb5ecc8)

## A simple product management system can do some basic things

#### 后有中文翻译
#### 详情请见：[商品信息系统介绍（简书）](https://www.jianshu.com/p/737effb5ecc8)
    
[商品信息系统介绍（博客园）](http://www.cnblogs.com/lihanxiang/p/8446820.html)

### Project structure：
> Servlet + JSP + MySQL<br>
> MVC(Model–View–Controller)

### Development process：

<br/>1. Build Packages:  
> dao  
> page  
> product  
> service  
> servlet

<br/>2. Import JAR File
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

<br/>3. Create Table    
>   USE product;   
>   CREATE TABLE product (  
>   id VARCHAR(50) NOT NULL PRIMARY KEY,<br>
    barcode VARCHAR(13) NOT NULL,<br>
    name VARCHAR(40) NOT NULL,<br>
    units VARCHAR(5) NOT NULL,<br>
    purchasePrice VARCHAR(10) NOT NULL,<br>
    salePrice VARCHAR(10) NOT NULL,<br>
    inventory VARCHAR(20) NOT NULL<br>
>);

<br/>4. Programming Environment
>IntellijIDEA Ultimate + MySQL

<br/>5. How to use
> 1.Clone or download project [https://github.com/lihanxiang/ProductManagement.git](https://github.com/lihanxiang/ProductManagement.git)
><br/>2.Open in IDEA, configure web information
><br/>3.Deploy it to the Tomcat
><br/>4.Run, open browser and input **localhost:8080**

<br/>6. What can it do
>Add product information  
><br/>Edit product information  
><br/>Delete product information  
><br/>Show all product information  
><br/>Search for product information  

### Project screenshot：
![](http://upload-images.jianshu.io/upload_images/3426615-79022732f97f8e2a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-f2b03a3c915892c4.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-640e6c7db3c2d823.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-e696c7c46e039742.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-7a2da38659c2871d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-d401fbb6c61f85c1.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### Run the demo:
![](http://upload-images.jianshu.io/upload_images/3426615-dc49b6932966c257.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

***

# 商品信息管理系统

## 一个简易的能执行基本操作的管理系统（首个web项目）
源代码中有极其详细的注解

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
><br/>2.在IDEA中打开项目,配置web项目信息（科学上网）
><br/>3.部署到Tomcat（科学上网）
><br/>4.Run, 打开浏览器并输入地址： **localhost:8080**

<br/>6. 功能介绍
>添加商品信息  
><br/>编辑商品信息  
><br/>删除商品信息  
><br/>显示所有商品信息  
><br/>查找商品信息  

### 项目的截图在上方的**Project screenshot**中  
