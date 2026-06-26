package model;

import java.time.LocalDateTime;

public class Product {
	private int productId;
	private String productName;
	private int productAmount;
	private int productPrice;
	private LocalDateTime createAt;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String productName, int productAmount, int productPrice, LocalDateTime createAt) {
		super();
		this.productName = productName;
		this.productAmount = productAmount;
		this.productPrice = productPrice;
		this.createAt = createAt;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
}
