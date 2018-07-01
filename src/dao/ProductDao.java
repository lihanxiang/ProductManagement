package dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import page.PageBean;
import product.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* ProductDao类是为了进行数据库操作
 *（添加，删除，编辑，查询）而设计的
 */

public class ProductDao {

    private QueryRunner queryRunner = new TxQueryRunner();

    //添加商品
    public void add(Product product){
        try{
            String sql = "INSERT INTO product VALUES (?,?,?,?,?,?,?)";
            //Object数组的内容是为了填充SQL语句中 "?" 的位置
            Object[] parameter = {product.getId(),product.getBarcode(),product.getName(),product.getUnits(),
                                product.getPurchasePrice(), product.getSalePrice(),product.getInventory()};
            queryRunner.update(sql,parameter);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //删除商品
    public void delete(String id){
        try{
            String sql = "DELETE FROM product WHERE id = ?";

            queryRunner.update(sql,id);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //编辑商品，parameter数组的内容用来填充SQL语句中的 "?"
    public void edit(Product product){
        try{
            String sql = "UPDATE product SET barcode = ?, name = ?, units = ?, purchasePrice = ?, " +
                            "salePrice = ?, inventory = ? WHERE id = ?";
            Object[] parameter = {product.getBarcode(), product.getName(), product.getUnits(), product.getPurchasePrice(),
                                    product.getSalePrice(), product.getInventory(), product.getId()};
            queryRunner.update(sql,parameter);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //查找商品信息，封装为Bean对象，后期使用
    public Product find(String id){
        try{
            String sql = "SELECT * FROM product WHERE id = ? ";
            return queryRunner.query(sql, new BeanHandler<>(Product.class),id);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //显示所有商品
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
            Object[] parameter = {(pageCode - 1) * pageRecord, pageRecord};     //数组从0开始，而页码从1开始

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

    public PageBean<Product> query(Product product, int pageCode, int pageRecord){
        //query功能采用模糊查询的方式，只需输入多个信息中的一个，
        //甚至不需要这个信息的全部，就能查询到所需信息。
        //例如查找名为xx的商品，只需输入x（不是全名）就能查询到xx商品。
        //“WHERE 1 = 1”在不输入任何信息的情况下，显示出所有商品的信息
        //%x%表示只需要输入x，就能找出含有x的项目
        //trim方法去掉字符前后的空格，以免只输入空格

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

            String name = product.getName();
            if(name != null && !name.trim().isEmpty()){
                whereSQL.append(" AND name LIKE ? ");
                parameter.add("%" + name + "%");
            }

            //单位，库存，进价和售价不进行模糊查询，以免查询到不必要的
            String units = product.getUnits();
            if(units != null && !units.trim().isEmpty()){
                whereSQL.append(" AND units = ? ");
                parameter.add(units);
            }

            String purchasePrice = product.getPurchasePrice();
            if(purchasePrice != null && !purchasePrice.trim().isEmpty()){
                whereSQL.append(" AND purchasePrice = ? ");
                parameter.add(purchasePrice);
            }

            String salePrice = product.getSalePrice();
            if(salePrice != null && !salePrice.trim().isEmpty()){
                whereSQL.append(" AND salePrice = ? ");
                parameter.add(salePrice);
            }

            String inventory = product.getInventory();
            if(inventory != null && !inventory.trim().isEmpty()){
                whereSQL.append(" AND inventory = ? ");
                parameter.add(inventory);
            }

            //先查询出有多少条记录
            Number totalRecord = queryRunner.query(countSQL.append(whereSQL).toString(), new ScalarHandler<>(),parameter.toArray());
            pageBean.setTotalRecord(totalRecord.intValue());

            //根据查询出的记录数量，在此基础上，限制内容，分页输出
            StringBuilder selectSQL = new StringBuilder("SELECT * FROM product ");
            StringBuilder limitSQL = new StringBuilder(" LIMIT ?,?");        //只输出第?项到第?项

            //parameter的最后两个元素就是上述limitSQL语句的限制范围，比如pageCode = 2,
            //pageRecord = 10,list = {10,20},表示输出从第10到第20项。

            parameter.add((pageCode - 1) * pageRecord);
            parameter.add(pageRecord);

            //以List的形式，将查询到的数据封装
            List<Product> beanList = queryRunner.query(selectSQL.append(whereSQL).append(limitSQL).toString(),
                                                        new BeanListHandler<>(Product.class), parameter.toArray());
            //为pageBean设定页面内容
            pageBean.setBeanList(beanList);
            return pageBean;

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
