# What is the ProductManagement?

## A simple product management system can do some basic things

#### 后有中文翻译
#### 详情请见：

#### [商品信息系统介绍（个人博客）](http://hxblog.site/2018/04/16/%E7%AE%80%E6%98%93%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F-%E9%A6%96%E4%B8%AAWeb%E9%A1%B9%E7%9B%AE/)
#### [商品信息系统介绍（博客园）](http://www.cnblogs.com/lihanxiang/p/8446820.html)

## Version0.1

### Project structure：
> Servlet + JSP + MySQL<br>
> MVC(Model–View–Controller)

![](http://upload-images.jianshu.io/upload_images/3426615-cfa3d4b11fc240e8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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

## Version0.2

### New user interface

![](http://upload-images.jianshu.io/upload_images/3426615-57ab75c1c170884b.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### This project now is closed
