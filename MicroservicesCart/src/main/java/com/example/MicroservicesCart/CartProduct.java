package com.example.MicroservicesCart;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CartProduct 
{
	
	@Id
	private int productId;
	private String name;
	private int price;
	public int getProductId() 
	{
		return productId;
	}
	@Override
	public String toString() {
		return "CartProduct [productId=" + productId + ", name=" + name + ", price=" + price + "]";
	}
	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
