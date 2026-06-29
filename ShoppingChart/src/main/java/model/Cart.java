package model;

import java.time.LocalDateTime;

public class Cart {

    private int userId;
    private int productId;
    private int quantity;
    private int sum;
    private LocalDateTime createAt;

    public Cart() {
    }

    public Cart(int userId, int productId, int quantity, int sum, LocalDateTime createAt) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.sum = sum;
        this.createAt = createAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

}
