package service.impl;
import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;
public class ProductServiceImpl implements ProductService {
    ProductDao pdi = new ProductDaoImpl();
    @Override
    public Product createProduct(Product product) {
        if (pdi.selectByName(product.getProductName()) == null) {
            pdi.insert(product);

            return pdi.selectById(product.getProductId());
        }
        return null;
    }
    @Override
    public String getAllProducts() {
        return pdi.selectAll();
    }
    @Override
    public Product getProductById(int productId) {
        return pdi.selectById(productId);
    }
    @Override
    public Product getProductByName(String productName) {
        return pdi.selectByName(productName);
    }
    @Override
    public Product getProductByPrice(int productPrice) {
        return pdi.selectByPrice(productPrice);
    }
    @Override
    public Product updateProduct(Product product) {
        Product p = pdi.selectById(product.getProductId());
        if (p != null) {
            pdi.update(product);
            // 同樣重新撈一次，確保回傳的物件帶有正確的 create_at
            // （update() 只會改 name/amount/price，不會動到 create_at）
            return pdi.selectById(product.getProductId());
        }
        return null;
    }
    @Override
    public void deleteProduct(int productId) {
        Product p = pdi.selectById(productId);
        if (p != null) {
            pdi.delete(productId);
        }
    }
}
