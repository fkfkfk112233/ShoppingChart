package dao;

import model.Cart;

public interface CartDao {

    // 新增商品到購物車
    void insert(Cart cart);

    // 查詢全部購物車
    String selectAll();

    // 查詢某位會員的購物車
    String selectByUserId(int userId);

    // 查詢某件商品
    Cart selectByProductId(int productId);

    // 更新購物車(數量、總金額)
    void update(Cart cart);

    // 刪除某件商品
    void delete(int productId);

    // 清空某位會員的購物車
    void deleteByUserId(int userId);

}