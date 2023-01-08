package com.example.MicroservicesInventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Serve 
{
	
	@Autowired
	private DAO d1;
	
	@Autowired
	private ComTesting ct;
	

	public String insert(Product i)
	{
	 if(i == null)
	 {
		 return "the item you want to insert in the db is null";
	 }
	 else
	 {
		 this.d1.save(i);
		 return "the item is successfully inserted into the db";
	 }

   }



	public String update(int id, int quantity) throws UserDefinedException 
	{
		
		// TODO Auto-generated method stub
		
		
		List <Product> l1 = this.d1.findAll();
		Product c = null;
		String response = null;
		for(Product p : l1)
		{
			if(p.getItemId() == id)
			{
				c = p;
				break;
			}
			
		}
		if(c == null)
		{
			//return "the list does not contains product by this specific id";
			throw new UserDefinedException("the list does not contains product by this specific id");
			
		}
		else
		{
		c.setQuantity(quantity);
		this.d1.save(c);
		response =  "the quantity of the specified product has been updated";
		}
		return response;
	}



	public List<Product> show() throws UserDefinedException 
	{
		// TODO Auto-generated method stub
		
		System.out.println(this.ct.getName() + "CCCCCCCCCCCCCCCCCCCCC");
		List <Product> l1 =  this.d1.findAll();
		//List <Product> l1 =  this.s1.show();
		if(l1 == null || l1.size() <= 0)
		{
			throw new UserDefinedException("THE DATABASE IS EMPTY");
			
		}
		else
		{
			return l1;
		}
	}



	public String updatePrice(PriceUpdation p) throws UserDefinedException {
		// TODO Auto-generated method stub
		List <Product> l1 = this.d1.findAll();
		Product c = null;
		String response = null;
		for(Product q : l1)
		{
			if(q.getItemId() == p.getId())
			{
				c = q;
				break;
			}
		}
		if(c == null)
		{
			throw new UserDefinedException("now product is present with this specified id:");
			//return "now product is present with this specified id:";
			
		}
		else
		{
		c.setPrice(p.getNewPrice());
		this.d1.save(c);
		response =  "the price of the specified product has been updated";
		}
		return response;
	}
	
	public Product getOne(int id) throws UserDefinedException
	{
		//this.d1.find
		Product p =  this.d1.getByitemId(id);
		if(p == null)
		{
			throw new UserDefinedException("THE SPECIFIED PRODUCT IS NOT PRESENT IN THE DB");
		}
		else
		{
			return p;
		}
	}
}
