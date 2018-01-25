package product;

/*Product类为了设置数据的基本信息
  以及数据封装而设计的
 */

public class Product {
    private String id;              //由CommonUtils.uuid()自动生成的唯一ID
    private String barcode;         //条形码
    private String name;            //名称
    private String units;           //单位
    private String purchasePrice;   //进价
    private String salePrice;       //售价
    private String inventory;       //库存

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public String getUnits() {
        return units;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
    public String getSalePrice() {
        return salePrice;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
    public String getInventory() {
        return inventory;
    }
}
