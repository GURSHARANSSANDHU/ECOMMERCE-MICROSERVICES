package com.example.Userservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class Serve 
{
	
	@Autowired
	private DAO d1;
	
	
	@Autowired
	private RestTemplate rest;
	
	
	@Autowired
	private WebClient web;
	
	//CREATE OPERATION
	public String insert(User u) throws UserDefinedException 
	{
		
		List <User> l1 = this.d1.findAll();
		User c = null;
		for(User t : l1)
		{
			if(t.getId() == u.getId())
			{
				c = t;
				break;
			}
		}
		
		if(c != null)
		{
			throw new UserDefinedException("A USER IS ALREADY PRESENT WITH THIS SPECIFIED ID, YOU CAN ADD A CART CORRESPONDING TO THIS USER");
		}
		else
		{
		
		this.d1.save(u);
        ShoppingCart sc = new ShoppingCart();
		
		sc.setCustomerId(u.getId());
		try
		{
			
		
		this.rest.postForEntity("http://Microservices-Cart/ShoppingCart/create", sc, null);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return e.getMessage();
		}
		
		return "THE USER PROFULE AND HIS CORRESPONDING CART HAS BEEN CREATED SUCCESFULLY";
		}
		
	}
	 
	//Add new Product in  the cart................
	public String updateAddProduct(CartProduct cp, int id) throws UserDefinedException
	{
		 Object o = null;
		List <User> l1 = this.d1.findAll();
		User c = null;
		for(User u : l1)
		{
			if(u.getId() == id)
			{
				c = u;
				break;
			}
		}
		
		if(c == null)
		{
			throw new UserDefinedException("No user is present with this specified userID");
		}
		else
		{
			ShoppingCart sc = null;
		
			try
			{
			    sc = this.rest.getForObject("http://Microservices-Cart/ShoppingCart/get/one/cart/" + id, ShoppingCart.class);
			}
			catch(Exception e)
			{
			
				return e.getMessage();
			
			}	
			   if(sc == null)
			   {
				   throw new UserDefinedException("THIS PARTICULAR USER DOES NOT HAVE A SHOPPING CART");
			   }
			   else
			   {

				   try
				   {
		           this.rest.postForEntity("http://Microservices-Cart/ShoppingCart/update/add/product/" + id, cp, null);
				   }
				   catch(Exception e)
				   {
					   System.out.println("CCCC");
					   return e.getMessage();
				   }
		        return "the new product have been added to the cart of the specified customer"; 
		  
		
		

	}
		}
}
	
	
	public String createCart(int id) throws UserDefinedException// creating a cart opf a specific user................
	{
	
		List <User> l1 = this.d1.findAll();
		User c = null;
		for(User u : l1)
		{
			if(u.getId() == id)
			{
				c = u;
				break;
			}
		}
		
		if(c == null)
		{
			throw new UserDefinedException("No user is present is present with this particular id");
		}
		else
		{
			ShoppingCart sc = new ShoppingCart();
			sc.setCustomerId(id);;
			try
			{
			this.rest.postForEntity("http://Microservices-Cart/ShoppingCart/create",sc,null);
			}
			catch(Exception e)
			{
				return e.getMessage();
			}
			return "A cart has been created for the specified user";
		}
	
		
	}
	
	public String updateRemoveProduct(int userId, int productId) throws UserDefinedException// removing a product from the cart of a specified uer.........
	{
		String res = null;
		List <User> l1 = this.d1.findAll();
		User c = null;
		for(User u : l1)
		{
			if(u.getId() == userId)
			{
				c = u;
				break;
			}
		}
		
		
		if(c == null)
		{
			throw new UserDefinedException("no user exists with this specified userId");
		}
		
		else if(c != null)
		{
			ShoppingCart sc = null;
			try
			{
			 sc = this.rest.getForObject("http://Microservices-Cart/ShoppingCart/get/one/cart/" + userId, ShoppingCart.class);
			}
			catch(Exception e)
			{
				System.out.println(e);
				return e.getMessage();
			}
			if(sc == null)
			{
				throw new UserDefinedException("no cart is mapped to this specified user");
			}
			else
			{
				try
				{
				 this.rest.put("http://Microservices-Cart/ShoppingCart/update/remove/product/" + userId + "/" + productId, new CartProduct());
				}
				catch(Exception e)
				{
					return e.getMessage();
				}
				 res =  "THE specified product has been removed from the cart of the specified user";
			}
		
		}
			
			
		
		return res;
	
	}
	
	
	public ShoppingCart getCart(int userId) throws UserDefinedException
	{
	    User c = null;
	    List <User> l1 = this.d1.findAll();
	    for(User u : l1)
	    {
	    	if(u.getId() == userId)
	    	{
	    		c = u;
	    		break;
	    	}
	    }
	  
	    
	    if(c == null)
	    {
	    	throw new UserDefinedException("NO USER EXISTS WITH THE SPECIFIED USERID");
	    }
	    else
	    {
	    	try
	    	{
	    	ShoppingCart sc = null;
	    	sc = this.rest.getForObject("http://Microservices-Cart/ShoppingCart/get/one/cart/" + userId, ShoppingCart.class);
	    	return sc;
	    }
	    	catch(Exception e)
	    	{
	    		System.out.println(e);
	    		return null;
	    	}
	    }
	    
	    
	   
	
	   
	       
		
		
	}
	
	
	 
    public List<Product> getInventoryItems()
    {
    	List <Product> l3 = null;
    			
    	try
    	{
    
    	ResponseEntity<Product[]> r1 = this.rest.getForEntity("http://Microservices-Inventory/Inventory/show/all/products", Product[].class);
    	List <Product> l1 = Arrays.asList(r1.getBody());
    	l3 = new ArrayList <Product> ();
    	for(Object o : l1 )
    	{
    		l3.add((Product)o);
    	}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    		return null;
    	}
    	return l3;
    }
    
    
    
    public List<ShoppingCart> getAllCarts()
    {
    	ResponseEntity <ShoppingCart[]> response = null;
    	List <ShoppingCart> l1 = null;
    	try
    	{
    	 response = this.rest.getForEntity("http://Microservices-Cart/ShoppingCart/get/all/carts", ShoppingCart[].class);
    	  l1 = Arrays.asList(response.getBody());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
       
        return l1;
    }
    	
}
	
	
	


