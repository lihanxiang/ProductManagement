package servlet;

import cn.itcast.commons.CommonUtils;
import page.PageBean;
import product.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet {
    //方法的返回值是跳转页面

    private ProductService productService = new ProductService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);   //将请求参数和Product Mapping
        product.setId(CommonUtils.uuid());      //随机生成ID
        productService.add(product);

        request.setAttribute("message", "添加商品成功");
        return "/message.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String id = request.getParameter("id");
        productService.delete(id);

        request.setAttribute("message", "删除商品成功");
        return "/message.jsp";
    }

    //edit操作需要两个操作：显示商品已保存的信息以及编辑商品信息，
    //显示商品信息方法名为preEdit,显示商品已保存的信息，
    //编辑方法名为edit，编辑商品信息及保存.
    public String preEdit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String id = request.getParameter("id");
        Product product = productService.find(id);      //找到商品的信息

        request.setAttribute("product", product);
        return "/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);
        productService.edit(product);

        request.setAttribute("message", "编辑用户成功");
        return "/message.jsp";
    }

    //在编写showAll方法之前，需要先编写getPageCode和getURL方法
    public int getPageCode(HttpServletRequest request){
        String value = request.getParameter("pageCode");
        if(value == null){
            return 1;
        }
        return Integer.parseInt(value);
    }

    public String getURL(HttpServletRequest request){
        return request.getRequestURL() + "?" + request.getQueryString();
    }


    public String showAll(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        int pageCode = getPageCode(request);
        int pageRecord = 10;        //每页10项记录

        PageBean<Product> pageBean = productService.showAll(pageCode, pageRecord);
        pageBean.setURL(getURL(request));

        return "/list.jsp";
    }

    public String query(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        Product product = CommonUtils.toBean(request.getParameterMap(), Product.class);

        int pageCode = getPageCode(request);
        int pageRecord = 10;

        PageBean<Product> pageBean = productService.query(product, pageCode, pageRecord);
        pageBean.setURL(getURL(request));

        request.setAttribute("pageBean",pageBean);
        return "/list.jsp";
    }
}
