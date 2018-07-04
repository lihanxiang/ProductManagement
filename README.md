# What is the ProductManagement?

## A simple product management system which can do some basic things

#### 这里有[中文说明](https://github.com/lihanxiang/p-m/blob/master/README(cn).md)

This is my first Web project

### Before you do this project, there have something you need to learn first

> Basic part of `Servlet`、`JSP`<br>
> `MySql` ( CRUD )<br>
> `MVC` design pattern（Model-View-Controller）

### Mind Map

![](http://upload-images.jianshu.io/upload_images/3426615-cfa3d4b11fc240e8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### About this project

Honestly speaking, I think this is the the easiest project for the beginner of Java Web. It has only one data table, one Servlet, ugly user interface (I don't know how to do it better) and the basic CRUD. If you are a freshmen here, complete this project will greatly increase your confidence.

You can first take a look at the following development process, fork the project, set up the web environment and run this project. And then make your own project.

If you have something thar Google can not solve, like some problems when you set up the environment, you can email me, I will try my best to help every one who are enthusiastic about Web develop.

## Version 0.1

### Structure：
> Servlet + JSP + MySQL<br>
> MVC design pattern

### What can it do：

> Create<br>
> Edit<br>
> Delete<br>
> ShowAll<br>
> Query<br>

### Preparation：

#### 1. Package

> dao<br>
> demo<br>
> page<br>
> product<br>
> service<br>
> servlet<br>

#### 2. Import JAR

> c3p0-0.9.5.2<br>
> commons-beanutils-1.9.3<br>
> commons-collections-3.2.2<br>
> commons-dbutils-1.7<br>
> commons-logging-1.2<br>
> itcast-tools-1.4<br>
> jsp-api<br>
> jstl-1.2<br>
> mchange-commons-java-0.2.11<br>
> mysql-connector-java-5.0.8-bin<br>
> servlet-api

#### 3. SQL Table

```
USE product;  
CREATE TABLE product (  
    id              VARCHAR(50) NOT NULL PRIMARY KEY,
    barcode         VARCHAR(13) NOT NULL,
    name            VARCHAR(40) NOT NULL,
    units           VARCHAR(5)  NOT NULL,
    purchasePrice   VARCHAR(10) NOT NULL,
    salePrice       VARCHAR(10) NOT NULL,
    inventory       VARCHAR(20) NOT NULL
);
```

#### 4. Programming Environment
> Intellij IDEA Ultimate + MySQL

#### 5. How to run this project
> 1.Clone or download project [https://github.com/lihanxiang/ProductManagement.git](https://github.com/lihanxiang/ProductManagement.git)
><br/>2.configure the environment of web project (only have chinese tutorial [Issues#1](https://github.com/lihanxiang/p-m/issues/1))
><br/>3.Deploy it to tomcat [Issues#1](https://github.com/lihanxiang/p-m/issues/1)
><br/>4.Change the database information in c3p0-config.xml 
><br/>5.Run, open browser and enter： **localhost:8080**

### Development process

#### 1. Product class

According to the data in SQL table：

```
public class Product {
    private String id;              //use CommonUtils.uuid() to automatic generated ID
    private String barcode;         
    private String name;            
    private String units;           
    private String purchasePrice;   
    private String salePrice;       
    private String inventory;       
    
    //setter 和 getter
}
```

PageBean class, use it to show the data in different pages：

```
public class PageBean<Object> {
    private int pageCode;           
    private int pageRecord;        
    private int totalPage;          
    private int totalRecord;        
    private List<Object> beanList;  
    private String url;
    
    // setter 和 getter
    //getTotalPage() is a little different from others
    public int getTotalPage(){
        totalPage = totalRecord/pageRecord;
        return totalRecord % pageRecord == 0 ? totalPage : totalPage + 1;
    }
}
```

#### 2. c3p0

Use database connection pool to manage the operation of database, so that the system can be more efficient. About c3p0, I wrote a [chinese tutorial](http://hxblog.site/2018/05/01/c3p0%E8%BF%9E%E6%8E%A5%E6%B1%A0/).

After you import the c3p0-jar, you need a file named `c3p0-config.xml` to write down some configuration:

```
<?xml version="1.0" encoding="UTF-8" ?>
<c3p0-config>
    <default-config>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/the name of database schema</property>
        <property name="driverClass">com.mysql.jdbc.Driver</property>
        <property name="user">username</property>
        <property name="password">password</property>
        <property name="acquireIncrement">5</property>
        <property name="initialPoolSize">30</property>
        <property name="maxPoolSize">30</property>
        <property name="minPoolSize">1</property>
    </default-config>
</c3p0-config>
```

#### 3. DAO

We use a class named `ProductDao` to do the work with database:

Use the `TxQueryRunner` to commit the SQL to database

```
    private QueryRunner queryRunner = new TxQueryRunner();
```

Here I only give the code of `add()`

```
    public void add(Product product){
        try{
            String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?)";
            //use this array to fill the ? in sql
            Object[] parameter = {product.getId(),product.getBarcode(),product.getName(),
                        product.getUnits(), product.getPurchasePrice(), product.getSalePrice(),
                        product.getInventory()};
            
            queryRunner.update(sql,parameter);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
```

And then we take a look at `showAll()`

First, we need to find the number of products, and show the specific products according to the page code

```
    public PageBean<Product> showAll(int pageCode, int pageRecord){
        try{
            PageBean<Product> pageBean = new PageBean<>();
            pageBean.setPageCode(pageCode);
            pageBean.setPageRecord(pageRecord);

            //count the number of products
            String sql = "SELECT COUNT(*) FROM product";
            Number totalRecord = queryRunner.query(sql, new ScalarHandler<>());

            pageBean.setTotalRecord(totalRecord.intValue());

            //find the specific products
            sql = "SELECT * FROM product ORDER BY name LIMIT ?,?";
            //page code is start from 1
            Object[] parameter = {(pageCode - 1) * pageRecord, pageRecord};  

            List<Product> beanList = queryRunner.query(sql, new BeanListHandler<>(Product.class),
                                            parameter);

            //set the content of pageBean
            pageBean.setBeanList(beanList);
            return pageBean;

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
```

Finally, the `query()`

```
    public PageBean<Product> query(Product product, int pageCode, int pageRecord){
        try{
            PageBean<Product> pageBean = new PageBean<>();
            pageBean.setPageCode(pageCode);
            pageBean.setPageRecord(pageRecord);

            StringBuilder countSQL = new StringBuilder("SELECT COUNT(*) FROM product ");
            StringBuilder whereSQL = new StringBuilder("WHERE 1 = 1 ");   //1 = 1 is always true
            List<Object> parameter = new ArrayList<>();

            //only show the part of barcode
            String barcode = product.getBarcode();
            if(barcode != null && !barcode.trim().isEmpty()){
                whereSQL.append(" AND barcode LIKE ?");
                parameter.add("%" + barcode + "%");
            }
            //...
            
            Number totalRecord = queryRunner.query(countSQL.append(whereSQL).toString(), 
                                                new ScalarHandler<>(),parameter.toArray());
            
            pageBean.setTotalRecord(totalRecord.intValue());

            StringBuilder selectSQL = new StringBuilder("SELECT * FROM product ");
            //limit the output
            StringBuilder limitSQL = new StringBuilder(" LIMIT ?,?");        

            //pageRecord = 10,list = {10,20}, means the products 10 to 20。

            parameter.add((pageCode - 1) * pageRecord);
            parameter.add(pageRecord);

            List<Product> beanList = queryRunner.query(
                            selectSQL.append(whereSQL).append(limitSQL).toString(), 
                            new BeanListHandler<>(Product.class), parameter.toArray());
            //set the content of pageBean
            pageBean.setBeanList(beanList);
            return pageBean;

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
```

So, in the sql above, why do we have `1 = 1`, because if the user doesn't input anything, the sql will become like this：

```
    SELECT * FROM product WHERE
```

but if we use `1 = 1`, even if the user input nothing, it will show all products, there will be no sql syntax error

#### 4. Service

We need to use the methods in DAO:

```
    private ProductDao productDao = new ProductDao();
```

The code of Service is call the methods in DAO:

```
    public void add(Product product){
        productDao.add(product);
    }
    //other methods
```

Here I add one method `find(String id)`, use the id to find product

We first add the method `find(String id)` in DAO：

```
    public Product find(String id){
        try{
            String sql = "SELECT * FROM product WHERE id = ? ";
            return queryRunner.query(sql, new BeanHandler<>(Product.class),id);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
```

And call it in Service：

```
    public Product find(String id){
        return productDao.find(id);
    }
```

#### Servlet 

Finally, we get to the most important part: Servlet

The methods in Servlet are based on Service：

```
    private ProductService productService = new ProductService();
```

`add()`:

```
    public String add(HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException{

        //Encoding
        request.setCharacterEncoding("utf-8");
        //Mapping the request and product
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        //Random ID
        product.setId(CommonUtils.uuid());     
        productService.add(product);

        //After add product, you need to give a message on the page
        //set an attribute named message, pass it to the request
        request.setAttribute("message", "Add Product Successfully");
        //return jsp
        return "/message.jsp";
    }
```

`delete()`:

```
    public String delete(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException{

        //Get the id of the product you want to delete from request
        String id = request.getParameter("id");
        productService.delete(id);

        //set the attribute
        request.setAttribute("message", "Delete Product Successfully");
        return "/message.jsp";
    }
```

Edit the product, here are two steps:

First, according to the id, find the specific product

```
    public String preEdit(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException{
        //Get id
        String id = request.getParameter("id");
        //Find product
        Product product = productService.find(id);      

        //pass the product to the request
        request.setAttribute("product", product);
        return "/edit.jsp";
    }
```

Second, we make some change on the product：

```
    public String edit(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException{
        
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        productService.edit(product);

        //set the attribute
        request.setAttribute("message", "Edit Product Successfully");
        return "/message.jsp";
    }
```

Now, before we write the method to show all products, we need to find the page code and URL:

```
    private int getPageCode(HttpServletRequest request){
        //Get the page code, if it's null, set it to 1
        String value = request.getParameter("pageCode");
        if(value == null || value.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(value);
    }
    
    //URL Stitching
    private String getUrl(HttpServletRequest request){
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        if (queryString.contains("&pageCode=")) {
            int index = queryString.lastIndexOf("&pageCode=");
            queryString = queryString.substring(0, index);
        }
        return contextPath + servletPath + "?" + queryString;
    }
```

`showAll()`:

```
    public String showAll(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        request.setCharacterEncoding("utf-8");
        int pageCode = getPageCode(request);
        int pageRecord = 10;        //10 records per page

        PageBean<Product> pageBean = productService.showAll(pageCode, pageRecord);
        //set the URL
        pageBean.setUrl(getUrl(request));

        //set the attribute
        request.setAttribute("pageBean",pageBean);
        return "/content.jsp";
    }
```

`query` is misty serch:

```
    public String query(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        //Mapping
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);

        int pageCode = getPageCode(request);
        int pageRecord = 10;

        PageBean<Product> pageBean = productService.query(product, pageCode, pageRecord);

        pageBean.setUrl(getUrl(request));

        //set the attribute
        request.setAttribute("pageBean",pageBean);
        return "/content.jsp";
    }
```

#### Jsp

About jsp, you can do it with basic knowledge of HTML, no discussion here

### Screenshows：

![](http://upload-images.jianshu.io/upload_images/3426615-79022732f97f8e2a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-f2b03a3c915892c4.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-640e6c7db3c2d823.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-e696c7c46e039742.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-7a2da38659c2871d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-d401fbb6c61f85c1.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## Version 0.2

### Better UI (not that ugly)

![](http://upload-images.jianshu.io/upload_images/3426615-57ab75c1c170884b.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### Project is closed, happy coding
