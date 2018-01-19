package dao;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Product;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class ProductDao {

    private QueryRunner queryRunner = new TxQueryRunner();

    public void add(Product product){
        try{
            String sql = "INSERT INTO product VALUES (?,?,?,?,?,?)";
            Object[] parameter = {product.getBarcode(),product.getName(),product.getUnits(),product.getPurchasePrice(),
                                    product.getSalePrice(),product.getInventory()};
            queryRunner.update(sql,parameter);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(Product product){

    }
}
