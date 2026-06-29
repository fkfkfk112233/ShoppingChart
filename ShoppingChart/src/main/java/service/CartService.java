package service;

import model.Cart;

public interface CartService {

    // 加入購物車
    Cart addCart(Cart cart);

    // 查看購物車
    String getMyCart(int userId);

    // 修改商品數量
    Cart updateCart(Cart cart);

    // 移除商品
    void removeProduct(int userId,int productId);

    // 清空購物車
    void clearCart(int userId);

}