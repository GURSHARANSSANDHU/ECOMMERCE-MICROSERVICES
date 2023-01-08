package com.example.MicroservicesCart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.MicroservicesInventory.ShoppingCart;

@RestController
@RequestMapping("/ShoppingCart")
public class Control 
{
	@Autowired
	private Serve s1;
	
	@RequestMapping("/create")
	public String create(@RequestBody ShoppingCart c) throws UserDefinedException
	{
		return this.s1.add(c);
	}
	
	
	@RequestMapping("/update/add/product/{customerId}")
	public String updateAddProduct(@RequestBody CartProduct cp, @PathVariable int customerId) throws UserDefinedException
	{
		System.out.println(cp);
		return this.s1.updateAddProduct(cp, customerId);
	}
	
	@RequestMapping("/update/remove/product/{customerId}/{productId}")
	public String updateRemoveProduct(@PathVariable int customerId, @PathVariable int productId) throws UserDefinedException
	{
	
		return this.s1.updateRemoveProduct(customerId, productId);
	}
	
	@RequestMapping("/delete/cart/{id}")
	public String deleteCart(@PathVariable int id) throws UserDefinedException
	{
		return this.s1.deleteCart(id);
	}

	
	@GetMapping("/get/one/cart/{id}")
	public ShoppingCart getOne(@PathVariable int id)
	{
		return this.s1.getOne(id);
	}
	
	
	@GetMapping("/get/all/carts")
	public List<ShoppingCart> getAllCarts()
	{
		return this.s1.getAllCarts();
	}
	
	
	
    
	
	
}
