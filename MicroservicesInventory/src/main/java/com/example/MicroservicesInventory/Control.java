package com.example.MicroservicesInventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Inventory")
public class Control 
{
	@Autowired
	private Serve s1;
	 
	
	
	@PostMapping("/add/product")
	public String create(@RequestBody Product i)
	{
		return this.s1.insert(i);
		//System.out.println(flag);
	}
	
	@PutMapping("/update/product/{id}/{quantity}")
	public String delete(@PathVariable int id, @PathVariable int quantity) throws UserDefinedException
	{
		 return this.s1.update(id,quantity);
	}
	
	
	@GetMapping("/show/all/products")
	public List <Product> readAll() throws UserDefinedException
	{
		return this.s1.show();
	}
	
	
	@PutMapping("/update/product/price")
	public String updatePrice(@RequestBody PriceUpdation p) throws UserDefinedException
	{
		return this.s1.updatePrice(p);
	}
	
	
	@GetMapping("/read/one/{id}")
	public Product readOne(@PathVariable int id ) throws UserDefinedException
	{
		System.out.println(id);
		return this.s1.getOne(id);
	}
	
	
	
	

}
