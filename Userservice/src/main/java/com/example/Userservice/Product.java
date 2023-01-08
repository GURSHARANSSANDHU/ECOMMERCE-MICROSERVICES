package com.example.Userservice;


public class Product 
{

	private int itemId;
	public Product(int itemId, String name, int price, int quantity) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public Product()
	{
		
	}
	@Override
	public String toString() {
		return "Product [itemId=" + itemId + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}
	private String name;
	private int price;
	private int quantity;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
