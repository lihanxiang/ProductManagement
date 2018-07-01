package service;

import dao.ProductDao;
import page.PageBean;
import product.Product;

/*ProductService是为了调用ProductDao中已
  定义的接口而设计的，并被ProductServlet所用
 */

public class ProductService {
    private ProductDao productDao = new ProductDao();

    public void add(Product product){
        productDao.add(product);
    }

    public void delete(String id){
        productDao.delete(id);
    }

    public void edit(Product product){
        productDao.edit(product);
    }

    public Product find(String id){
        return productDao.find(id);
    }

    public PageBean<Product> showAll(int pageCode, int pageRecord){
        return productDao.showAll(pageCode, pageRecord);
    }

    public PageBean<Product> query(Product product, int pageCode, int pageRecord){
        return productDao.query(product, pageCode, pageRecord);
    }
}
