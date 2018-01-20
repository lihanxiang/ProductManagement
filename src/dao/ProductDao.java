package dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import product.Product;
import page.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private QueryRunner queryRunner = new TxQueryRunner();

    public void add(Product product){
        try{
            String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?)";
            Object[] parameter = {product.getId(),product.getBarcode(),product.getName(),product.getUnits(),
                                product.getPurchasePrice(), product.getSalePrice(),product.getInventory()};
            queryRunner.update(sql,parameter);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(String id){
        try{
            String sql = "DELETE FROM product WHERE id = ?";

            queryRunner.update(sql,id);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void edit(Product product){
        try{
            String sql = "UPDATE product SET barcode = ?, name = ?, units = ?, purchase_price = ?, " +
                            "sale_price = ?, inventory = ?";
            Object[] parameter = {product.getBarcode(), product.getName(), product.getUnits(),
                                product.getPurchasePrice(), product.getSalePrice(), product.getInventory()};
            queryRunner.update(sql,parameter);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Product find(String id){
        Product product = new Product();
        try{
            String sql = "SELECT FORM product WHERE id = ? ";
            product = queryRunner.query(sql, new BeanHandler<>(Product.class),id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public PageBean<Product> showAll(int pageCode, int pageRecord){

        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setPageCode(pageCode);
        pageBean.setPageRecord(pageRecord);

        try{
            String sql = "SELECT COUNT(*) FROM product";                //计算一共有多少记录
            int totalRecord = queryRunner.query(sql, new ScalarHandler<>());

            pageBean.setTotalPage(totalRecord);

            sql = "SELECT * FROM product ORDER BY name LIMIT ?,?";      //检索从第?行到第?行的记录
            Object[] parameter = {(pageCode - 1) * pageRecord, pageRecord};     //数组从0开始，而页码从1开始

            List<Product> beanList = queryRunner.query(sql, new BeanListHandler<>(Product.class), parameter);

            pageBean.setBeanList(beanList);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return pageBean;
    }

    public PageBean<Product> query(Product product, int pageCode, int pageRecord){
        //query功能采用模糊搜索的方式，只需输入多个信息中的一个，
        // 甚至不需要这个信息的全部，就能查询到所需信息。
        //例如查找名为xx的商品，只需输入x（不是全名）就能查询到xx商品。
        //关于“WHERE 1 = 1”的做法将在README.md中讲述
        //%x%表示只需要x，就能找出含有x的项目

        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setPageCode(pageCode);
        pageBean.setPageRecord(pageRecord);

        try{
            StringBuilder countSQL = new StringBuilder("SELECT COUNT(*) FROM product ");
            StringBuilder whereSQL = new StringBuilder("WHERE 1 = 1 ");
            List<Object> parameter = new ArrayList<>();

            String barcode = product.getBarcode();
            if(barcode != null){
                whereSQL.append("AND barcode LIKE ? ");     //使用mysql的like来查询
                parameter.add("%" + barcode + "%");
            }

            String name = product.getName();
            if(name != null){
                whereSQL.append("AND name LIKE ? ");
                parameter.add("%" + name + "%");
            }

            String units = product.getUnits();
            if(units != null){
                whereSQL.append("AND units LIKE ? ");
                parameter.add("%" + units + "%");
            }

            String purchasePrice = product.getPurchasePrice();
            if(purchasePrice != null){
                whereSQL.append("AND purchase_price LIKE ? ");
                parameter.add("%" + purchasePrice + "%");
            }

            String salePrice = product.getSalePrice();
            if(salePrice != null){
                whereSQL.append("AND sale_price LIKE ? ");
                parameter.add("%" + salePrice + "%");
            }

            String inventory = product.getInventory();
            if(inventory != null){
                whereSQL.append("AND inventory LIKE ? ");
                parameter.add("%" + inventory + "%");
            }

            int totalRecord = queryRunner.query(countSQL.append(whereSQL).toString(), new ScalarHandler<>(),parameter);
            pageBean.setTotalPage(totalRecord);

            StringBuilder selectSQL = new StringBuilder("SELECT * FROM product ");
            StringBuilder limitSQL = new StringBuilder("LIMIT ?,?");        //只输出第?项到第?项

            //list的元素就是上述limitSQL语句的限制范围，比如pageCode = 2,
            //pageRecord = 10,list = {10,20},表示输出从第10到第20项。
            List<Object> list = new ArrayList<>();
            list.add((pageCode - 1) * pageRecord);
            list.add(pageRecord);

            List<Product> beanList = queryRunner.query(selectSQL.append(whereSQL).append(limitSQL).toString(),
                                                        new BeanListHandler<>(Product.class), list.toArray());
            pageBean.setBeanList(beanList);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return pageBean;
    }
}
