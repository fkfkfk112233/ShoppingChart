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
            return product;

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
            return product;

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
