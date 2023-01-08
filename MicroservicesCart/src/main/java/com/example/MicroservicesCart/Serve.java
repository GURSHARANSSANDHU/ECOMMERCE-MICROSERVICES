package com.example.MicroservicesCart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Serve {

	@Autowired
	private DAO d1;

	@Autowired
	private RestTemplate rt;

	public String add(ShoppingCart c) throws UserDefinedException// create
	{
		if (c == null) {
			throw new UserDefinedException("no cart is created in the datbase as the cart id is not specified ");
		} else {
			List<ShoppingCart> l1 = this.d1.findAll();
			ShoppingCart c1 = null;
			for (ShoppingCart sc : l1) {
				if (sc.getCustomerId() == c.getCustomerId()) {
					c1 = sc;
					break;
				}
			}
			if (c1 != null) {
				throw new UserDefinedException("A SHOPPING CART IS ALREADY PRESENT WITH THIS SPECIFIED ID");
			} else {
				this.d1.save(c);
				return "A new Cart is added with the specified id";
			}
		}

	}

	public String updateAddProduct(CartProduct cp, int cid) throws UserDefinedException// update
	{
		// RestTemplate rt = new RestTemplate();
		System.out.println("CCCCCCCC");
		int flag = 0;
		try {
			Product p = this.rt.getForObject(
					"http://Microservices-Inventory/Inventory/read/one" + "/" + cp.getProductId(), Product.class);
			System.out.println("NNNNNNNNN");
			System.out.println(p);
			System.out.println(cp);
			if (p == null || p.getQuantity() == 0) {
				throw new UserDefinedException(
						"the specified product to be added in the ShoppingCart is exhausted  in the db");
			} else {
				int quantity = p.getQuantity();
				List<ShoppingCart> l1 = this.d1.findAll();

				ShoppingCart c = null;
				for (ShoppingCart sc : l1) {
					if (sc.getCustomerId() == cid) {
						c = sc;
						break;
					}
				}

				if (p.getName().equalsIgnoreCase(cp.getName())) {
					CartProduct c3 = null;
					System.out.println(cp.getProductId());
					for (CartProduct temp : c.getL1()) {
						System.out.println(temp);
						if (temp.getProductId() == cp.getProductId()) {
							c3 = temp;
							break;
						}
					}
					if (c3 == null) {
						c.getL1().add(cp);
						this.d1.save(c);
						quantity = quantity - 1;
						rt.put("http://Microservices-Inventory/Inventory/update/product/" + p.getItemId() + "/"
								+ quantity, p);
						return "the new Product has been added into the cart and its quantity has been updated in the database";
					} else {
						flag = 1;
						throw new UserDefinedException("the product is already present in the cart of the customer");

					}
				} else {
					return "the Id and the name of the product are mismatching";
				}

			}
		} catch (Exception e) {
			System.out.println(e);
			if (flag == 1) {
				throw new UserDefinedException("the product is already present in the cart of the customer");
			}
			return e.getMessage();
			// throw new UserDefinedException("No Product is present with the specified id
			// in the DB");
		}
	}

	public String updateRemoveProduct(int cusId, int id) throws UserDefinedException// update
	{
		Product p = this.rt.getForObject("http://Microservices-Inventory/Inventory/read/one" + "/" + id, Product.class);
		List<ShoppingCart> l1 = this.d1.findAll();
		ShoppingCart r = null;
		for (ShoppingCart c : l1) {
			if (c.getCustomerId() == cusId) {
				r = c;
				break;
			}
		}
		if (r != null) {
			int i = 0;
			for (CartProduct cp : r.getL1()) {
				if (cp.getProductId() == id) {
					break;
				} else {
					i = i + 1;
				}
			}
			r.getL1().remove(i);
			this.d1.save(r);
			int quantity = p.getQuantity() + 1;
			rt.put("http://Microservices-Inventory/Inventory/update/product/" + p.getItemId() + "/" + quantity, p);

			return "the specified Product hase been removed from the cart of the specified customer";
		}

		else {
			throw new UserDefinedException("the specified customer does not have any corresponding cart");
		}

	}

	public String deleteCart(int custId) throws UserDefinedException// delete
	{
		List<ShoppingCart> l1 = this.d1.findAll();
		ShoppingCart c = null;
		for (ShoppingCart sc : l1) {
			if (sc.getCustomerId() == custId) {
				c = sc;
				break;
			}
		}

		if (c == null) {
			throw new UserDefinedException("No cart is present with the specified id");
		} else {
			for (CartProduct cp : c.getL1()) {
				int id = cp.getProductId();
				Product p = this.rt.getForObject("http://Microservices-Inventory/Inventory/read/one/" + id,
						Product.class);
				this.rt.put(
						"http://Microservices-Inventory/Inventory/update/product/" + id + "/" + (p.getQuantity() + 1),
						p);
			}
			this.d1.delete(c);
			return "the cart with this specific Id has been deleted from  the database";

		}
	}

	public ShoppingCart getOne(int custId)// read
	{
		List<ShoppingCart> l1 = this.d1.findAll();
		ShoppingCart c = null;
		for (ShoppingCart sc : l1) {
			if (sc.getCustomerId() == custId) {
				c = sc;
				break;
			}
		}

		return c;
	}

	public List<ShoppingCart> getAllCarts() {

		return this.d1.findAll();
	}
	
	
	

}
