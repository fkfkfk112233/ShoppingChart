package service;

import model.Product;

public interface ProductService {

    // 新增商品
    Product createProduct(Product product);

    // 查詢全部商品
    String getAllProducts();

    // 依商品ID查詢
    Product getProductById(int productId);

    // 依商品名稱查詢
    Product getProductByName(String productName);

    // 依價格查詢
    Product getProductByPrice(int productPrice);

    // 更新商品
    Product updateProduct(Product product);

    // 刪除商品
    void deleteProduct(int productId);

}