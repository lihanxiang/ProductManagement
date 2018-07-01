package demo;

import cn.itcast.commons.CommonUtils;
import product.Product;
import service.ProductService;

public class Demo {
    public static void main(String[] args){
        ProductService productService = new ProductService();
        Product product = new Product();
        for (int i = 1; i < 20; i++) {
            product.setId(CommonUtils.uuid());
            product.setBarcode("123456" + i);
            product.setName("T-shirt" + i);
            product.setUnits("piece");
            product.setPurchasePrice("100" + i);
            product.setSalePrice("150" + i);
            product.setInventory("50");
            productService.add(product);
        }
    }
}
