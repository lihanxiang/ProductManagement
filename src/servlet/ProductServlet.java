package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import page.PageBean;
import product.Product;
import service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*ProductServlet是为了实现页面间的连接
  和通信而设计的，实现了ProductService
  中定义的接口
 */

public class ProductServlet extends BaseServlet{
    /*在控制器中，方法的返回值是
      跳转页面和JSP页面共同开发
    */
    private ProductService productService = new ProductService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        request.setCharacterEncoding("utf-8");
        //将请求参数和Product进行Mapping
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        product.setId(CommonUtils.uuid());      //随机生成ID
        productService.add(product);

        //设定添加成功之后的信息
        request.setAttribute("message", "Add Product Successfully");
        return "/message.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{

        //得到要删除的商品的ID
        String id = request.getParameter("id");
        productService.delete(id);

        //设定删除成功之后的信息
        request.setAttribute("message", "Delete Product Successfully");
        return "/message.jsp";
    }

    /*edit操作需要两个操作：显示商品已保存的信息以及编辑商品信息，
      显示商品信息方法名为preEdit,显示商品已保存的信息，
      编辑方法名为edit，编辑商品信息及保存.
    */
    public String preEdit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        //得到要编辑的商品的ID,查询到此商品的信息
        String id = request.getParameter("id");
        Product product = productService.find(id);      //查询到商品的信息

        //设定查询到商品信息之后的操作
        request.setAttribute("product", product);
        return "/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        /*方法和add方法类似，将得到的请求参数
          和product进行mapping，进行更新
         */
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        productService.edit(product);

        //设定编辑成功之后的信息
        request.setAttribute("message", "Edit Product Successfully");
        return "/message.jsp";
    }

    //在编写showAll方法之前，需要先编写getPageCode和getURL方法
    private int getPageCode(HttpServletRequest request){
        //得到关于页码的参数，如果没有，就设置为1
        String value = request.getParameter("pageCode");
        if(value == null || value.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(value);
    }

    //只需要得到在请求中页码(PageCode)之前的内容
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

    //显示当前的所有商品信息
    public String showAll(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
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

    /*如果查询的内容有中文，需要设置IDEA，Tomcat
      以及MySQL的编码方式为"utf-8",否则会有乱码
     */
    public String query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
}
