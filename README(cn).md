# 商品信息管理系统

## 一个简易的能执行基本操作的管理系统（首个web项目）

#### [商品信息系统介绍（个人博客）](https://lihanxiang.github.io/2018/04/16/%E7%AE%80%E6%98%93%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F-%E9%A6%96%E4%B8%AAWeb%E9%A1%B9%E7%9B%AE/)

这是我初学 Java Web 时的首个 Web 项目

### 在此之前需要学习的内容，不必一次学习太多，结合项目一起学习才能高效：

> Servlet、JSP 基础知识<br>
> MySql 基本用法（增删改查）<br>
> MVC 基础概念（Model-View-Controller）

### 思维导图

![](http://upload-images.jianshu.io/upload_images/3426615-cfa3d4b11fc240e8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 自吹一波

这个项目应该是最符合新手的项目，它只有一张数据表，前端页面也不复杂，后台也只有一个控制器，业务逻辑也都是最常规的增删改查，如果在刚入门 Java Web 时能够完整做出这一个项目，相信你会极大地增加自信心

可以跟着下面的开发流程一步步了解这个项目，先将你 fork 的这个项目理解并运行起来，之后再自行构建一个项目

如果有什么是 Google 不能解决的，你也可以发邮件私信我，每一个认真学习的人都值得被鼓励，能帮忙的我一定尽力帮忙

## 版本 0.1

### 项目结构：
> Servlet + JSP + MySQL<br>
> MVC 模式

### 能做些什么：

> 添加商品信息<br>
> 编辑商品信息<br>
> 删除商品信息<br>
> 显示所有商品信息<br>
> 模糊查找商品信息<br>

### 准备工作：

#### 1. 建包

> dao<br>
> demo<br>
> page<br>
> product<br>
> service<br>
> servlet<br>

#### 2. 导入 JAR 文件

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

#### 3. 建表

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

#### 4. 编程环境
> IntellijIDEA Ultimate + MySQL

#### 5. 如何使用
> 1.Clone 或 download 项目 [https://github.com/lihanxiang/ProductManagement.git](https://github.com/lihanxiang/ProductManagement.git)
><br/>2.在IDEA中打开项目,配置web项目信息 [Issue #1](https://github.com/lihanxiang/p-m/issues/1)
><br/>3.部署到Tomcat [Issue #1](https://github.com/lihanxiang/p-m/issues/1)
><br/>4.修改 c3p0-config.xml 文件中的数据库信息，确保能连接数据库
><br/>5.Run, 打开浏览器并输入地址： **localhost:8080**

### 开发过程

#### 1. 创建实体类

对应数据表中的实体类：

```
public class Product {
    private String id;              //由CommonUtils.uuid()自动生成的唯一ID
    private String barcode;         //条形码
    private String name;            //名称
    private String units;           //单位
    private String purchasePrice;   //进价
    private String salePrice;       //售价
    private String inventory;       //库存
    
    //省略 setter 和 getter
}
```

数据页的实体类：

```
public class PageBean<Object> {
    private int pageCode;           //页码
    private int pageRecord;         //页面的记录数
    private int totalPage;          //总页数
    private int totalRecord;        //总记录
    private List<Object> beanList;  //当前页面内容
    private String url;
    
    //省略 setter 和 getter，唯独以下一点：
    //计算总共有几页
    public int getTotalPage(){
        totalPage = totalRecord/pageRecord;
        return totalRecord % pageRecord == 0 ? totalPage : totalPage + 1;
    }
}
```

#### 2. 配置连接池信息

使用连接池来对进行数据库操作，能够提高效率，在初始化时就获得一定连接数，需要用就从连接池取，用完归还，在项目初始时开销较大，但后续操作十分省时，关于 c3p0 写了一篇[博客](http://hxblog.site/2018/05/01/c3p0%E8%BF%9E%E6%8E%A5%E6%B1%A0/) 来介绍

使用 c3p0 连接池，导入 c3p0 包之后，需要一个 c3p0-config.xml 文件来进行配置：

```
<?xml version="1.0" encoding="UTF-8" ?>
<c3p0-config>
    <default-config>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/你的数据库的名字</property>
        <property name="driverClass">com.mysql.jdbc.Driver</property>
        <property name="user">用户名</property>
        <property name="password">密码</property>
        <property name="acquireIncrement">5</property>
        <property name="initialPoolSize">30</property>
        <property name="maxPoolSize">30</property>
        <property name="minPoolSize">1</property>
    </default-config>
</c3p0-config>
```

#### 3. 数据持久层工作

接下来我们在 dao 包内创建一个 ProductDao 类来编写持久层代码，用到的工具是 apache 的 `common-dbutils`：

首先选用 TxQueryRunner 来作为查询的工具，TxQueryRunner 是 QueryRunner 的子类，我们只有增删改查操作，所以选择越轻量的工具越好：

```
    private QueryRunner queryRunner = new TxQueryRunner();
```

增删改三步的操作是类似的，所以这里就给出添加商品的代码：

SQL 语句都留了占位符，这时候需要根据用户在页面上的表单输入的数据来进行数据库操作

```
    public void add(Product product){
        try{
            String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?)";
            //Object数组的内容是为了填充SQL语句中 "?" 的位置
            Object[] parameter = {product.getId(),product.getBarcode(),product.getName(),
                        product.getUnits(), product.getPurchasePrice(), product.getSalePrice(),
                        product.getInventory()};
            
            queryRunner.update(sql,parameter);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
```

然后是显示所有商品的操作：

首先查询一共有多少商品，确定一共需要几页，然后根据页码来选择查询的范围，使用 List 来存放数据，最后返回一个 pageBean 对象，在 Jsp 页面中对其进行读取：

```
    public PageBean<Product> showAll(int pageCode, int pageRecord){
        try{
            PageBean<Product> pageBean = new PageBean<>();
            pageBean.setPageCode(pageCode);
            pageBean.setPageRecord(pageRecord);

            //计算一共有多少记录
            String sql = "SELECT COUNT(*) FROM product";
            Number totalRecord = queryRunner.query(sql, new ScalarHandler<>());

            pageBean.setTotalRecord(totalRecord.intValue());

            //检索从第?行到第?行的记录
            sql = "SELECT * FROM product ORDER BY name LIMIT ?,?";
            //数组从0开始，而页码从1开始
            Object[] parameter = {(pageCode - 1) * pageRecord, pageRecord};  

            List<Product> beanList = queryRunner.query(sql, new BeanListHandler<>(Product.class),
                                            parameter);

            //为pageBean设定页面内容
            pageBean.setBeanList(beanList);
            return pageBean;

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
```

最后就是模糊查找的操作了：

针对多项数据的操作是类似的，所以这里给出了针对条形码进行的拼接：

```
    public PageBean<Product> query(Product product, int pageCode, int pageRecord){
        try{
            PageBean<Product> pageBean = new PageBean<>();
            pageBean.setPageCode(pageCode);
            pageBean.setPageRecord(pageRecord);

            StringBuilder countSQL = new StringBuilder("SELECT COUNT(*) FROM product ");
            StringBuilder whereSQL = new StringBuilder("WHERE 1 = 1 ");   //无论何时都成立
            List<Object> parameter = new ArrayList<>();

            //使用mysql的like来对条形码和名称进行模糊查询
            String barcode = product.getBarcode();
            if(barcode != null && !barcode.trim().isEmpty()){
                whereSQL.append(" AND barcode LIKE ?");
                parameter.add("%" + barcode + "%");
            }
            //...
            
            Number totalRecord = queryRunner.query(countSQL.append(whereSQL).toString(), 
                                                new ScalarHandler<>(),parameter.toArray());
            
            pageBean.setTotalRecord(totalRecord.intValue());

            //根据查询出的记录数量，在此基础上，限制内容，分页输出
            StringBuilder selectSQL = new StringBuilder("SELECT * FROM product ");
            //只输出第?项到第?项
            StringBuilder limitSQL = new StringBuilder(" LIMIT ?,?");        

            //parameter的最后两个元素就是上述limitSQL语句的限制范围，比如pageCode = 2,
            //pageRecord = 10,list = {10,20},表示输出从第10到第20项。

            parameter.add((pageCode - 1) * pageRecord);
            parameter.add(pageRecord);

            //以List的形式，将查询到的数据封装
            List<Product> beanList = queryRunner.query(
                            selectSQL.append(whereSQL).append(limitSQL).toString(), 
                            new BeanListHandler<>(Product.class), parameter.toArray());
            //为pageBean设定页面内容
            pageBean.setBeanList(beanList);
            return pageBean;

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
```

那么 whereSQL 中有一个 `1 = 1` 是干嘛的呢？这里面有涉及到 SQL 语法错误的操作，假设**所有的数据都为空**，且我们去掉 `1 = 1`，那么 SQL 语句就会有语法错误，会变成：

```
    SELECT * FROM product WHERE
```

由于 `1 = 1` 是永远成立的，所以添加 `1 = 1` 也就能够在没有输入的情况下查询到所有商品的信息

到此，数据持久层的工作就完成了

#### 4. Service 层

Service 层用于业务逻辑的设计，它调用 DAO 层的代码，然后在 Servlet 中调用 Service 层来进行业务的处理，为什么要这样分层呢？

因为封装 Service 层的业务逻辑，能够增加其独立性以及复用性，这其实也算是现在开发的约定之一吧

首先创建实例：

```
    private ProductDao productDao = new ProductDao();
```

Service 层的操作都是类似的，都只是调用 DAO 层的代码，这里给出增加商品的代码：

```
    public void add(Product product){
        productDao.add(product);
    }
```

**在这里需要增加一项操作**：根据 ID 查找商品，因为我们在 Servlet 中进行编辑商品之前，需要先根据 ID 来找到你要修改的商品

我们先在 DAO 层增加一个方法 `find(String id)`：

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

然后在 Service 层调用这个方法：

```
    public Product find(String id){
        return productDao.find(id);
    }
```

#### Servlet 层

终于到了最重要的阶段了，Servlet 就像是一个请求管理器一样，将用户的请求分配至对应的方法，来进行相对应的操作

首先创建 Service 层的实例：

```
    private ProductService productService = new ProductService();
```

然后添加商品，在用户输入数据之后，为商品添加一个随机生成的 ID，并将其添加至数据库：

```
    public String add(HttpServletRequest request, HttpServletResponse response) 
                throws IOException, ServletException{

        //设置编码
        request.setCharacterEncoding("utf-8");
        //将请求参数和Product进行Mapping
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        product.setId(CommonUtils.uuid());      //随机生成ID
        productService.add(product);

        //设定添加成功之后的信息
        //在 request 中设定名为 message 的属性，在 Jsp 中通过 ${message} 来读取这个属性的值
        request.setAttribute("message", "Add Product Successfully");
        //返回页面
        return "/message.jsp";
    }
```

删除商品，根据商品 ID 来进行操作：

```
    public String delete(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException{

        //得到要删除的商品的ID
        String id = request.getParameter("id");
        productService.delete(id);

        //设定删除成功之后的信息
        request.setAttribute("message", "Delete Product Successfully");
        return "/message.jsp";
    }
```

编辑商品，这个操作分两步，先根据 ID 得到商品的信息：

```
    public String preEdit(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException{
        //得到要编辑的商品的ID,查询到此商品的信息
        String id = request.getParameter("id");
        //查询到商品的信息
        Product product = productService.find(id);      

        //设定查询到商品信息之后的操作，将这个商品加入 request，在 Jsp 中通过 ${product.getxxx}来调用
        request.setAttribute("product", product);
        return "/edit.jsp";
    }
```

然后我们对得到的数据再进行修改：

```
    public String edit(HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException{
        /*方法和add方法类似，将得到的请求参数
          和product进行mapping，进行更新
         */
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        productService.edit(product);

        //设定编辑成功之后的信息
        request.setAttribute("message", "Edit Product Successfully");
        return "/message.jsp";
    }
```

在编写显示所有商品的代码前，我们需要先写出获取页码以及获取 URL 的方法：

```
    private int getPageCode(HttpServletRequest request){
        //得到关于页码的参数，如果没有，就设置为1
        String value = request.getParameter("pageCode");
        if(value == null || value.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(value);
    }
    
    //URL 拼接
    private String getUrl(HttpServletRequest request){
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        //queryString为查询字符串
        if (queryString.contains("&pageCode=")) {
            int index = queryString.lastIndexOf("&pageCode=");
            queryString = queryString.substring(0, index);
        }
        return contextPath + servletPath + "?" + queryString;
    }
```

然后再编写显示所有商品的代码：

```
    public String showAll(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        request.setCharacterEncoding("utf-8");
        int pageCode = getPageCode(request);
        int pageRecord = 10;        //每页10项记录

        PageBean<Product> pageBean = productService.showAll(pageCode, pageRecord);
        //得到请求的url
        pageBean.setUrl(getUrl(request));

        //设置页面内容
        request.setAttribute("pageBean",pageBean);
        return "/content.jsp";
    }
```

最后是模糊查找的代码，和上面的类似，也要分页显示：

```
    public String query(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        //得到请求参数，进行mapping
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);

        int pageCode = getPageCode(request);
        //每页10项记录
        int pageRecord = 10;

        PageBean<Product> pageBean = productService.query(product, pageCode, pageRecord);

        pageBean.setUrl(getUrl(request));

        //设定页面内容
        request.setAttribute("pageBean",pageBean);
        return "/content.jsp";
    }
```

#### Jsp

关于前端页面，这里就不多说，基础的 HTML 语法掌握了就能够解决

### 项目的截图：

![](http://upload-images.jianshu.io/upload_images/3426615-79022732f97f8e2a.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-f2b03a3c915892c4.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-640e6c7db3c2d823.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-e696c7c46e039742.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-7a2da38659c2871d.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](http://upload-images.jianshu.io/upload_images/3426615-d401fbb6c61f85c1.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 版本0.2

### 改进用户界面

![](http://upload-images.jianshu.io/upload_images/3426615-57ab75c1c170884b.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 项目已完结，祝大家学习快乐
