package com.example.Userservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/UserProfile")
public class Control 
{
	
	@Autowired
	private Serve s1;
	
	
	@PostMapping("/create")
	public String insert(@RequestBody User u) throws UserDefinedException
	{
		return this.s1.insert(u);
	}
	
	
	@PutMapping("/update/add/product/{id}")
	public String updateAddproduct(@PathVariable int id, @RequestBody CartProduct cp) throws UserDefinedException
	{
		return this.s1.updateAddProduct(cp, id);
	}
	
	
	@PostMapping("/create/new/cart/{id}")
	public String createCart(@PathVariable int id) throws UserDefinedException
	{
		return this.s1.createCart(id);
	}
	
	
	@PutMapping("/update/cart/remove/product/{userId}/{productId}")
	public String removeProduct(@PathVariable int userId, @PathVariable int productId) throws UserDefinedException
	{
		return this.s1.updateRemoveProduct(userId, productId);
	}
	
	
	@GetMapping("/get/userCart/{userId}")
	public ShoppingCart getCart(@PathVariable int userId) throws UserDefinedException
	{
		return this.s1.getCart(userId);
	}
	
	
	@GetMapping("/get/Inventory/products") //implementing ResponseEntity in service...................
	public ResponseEntity<List<Product>> getInventoryProducts()
	{
		List<Product> l1 =  this.s1.getInventoryItems();
		MultiValueMap <String, String> hm = new LinkedMultiValueMap<String, String>();
		hm.add("GURSHARAN", "SOBER");
	
	     HttpHeaders h = new HttpHeaders(hm);// HttpHeaders has all the functions of class/ class datatype implementing predefined interface map; 
	
		HttpStatus status = HttpStatus.FOUND;
		ResponseEntity <List<Product>> r = new ResponseEntity<List<Product>>(l1,h,status);
	    
		return r;
	}
	
	@GetMapping("get/all/carts")
	public ResponseEntity<List<ShoppingCart>> getAllCarts()
	{
		List <ShoppingCart> l1 = this.s1.getAllCarts();
		MultiValueMap <String,String> m1 = new LinkedMultiValueMap<String, String>();
		m1.add("CARTS", "ALL");
		HttpHeaders h = new HttpHeaders(m1);
		ResponseEntity <List<ShoppingCart>> response = new ResponseEntity<List<ShoppingCart>>(l1, h, HttpStatus.FOUND);
		return response;
		
	}

	
}
