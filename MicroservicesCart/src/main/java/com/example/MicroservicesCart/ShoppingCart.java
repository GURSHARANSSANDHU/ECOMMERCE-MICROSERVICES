
package com.example.MicroservicesCart;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

//import com.example.BankingAPI.DataModelLayer.Account;

@Entity
public class ShoppingCart {

	@Id
	private int customerId;

	@OneToMany(targetEntity=CartProduct.class, cascade=CascadeType.ALL)
	@JoinColumn(name="customerId", referencedColumnName="customerId")
	private List<CartProduct> l1;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<CartProduct> getL1() {
		return l1;
	}

	public void setL1(List<CartProduct> l1) {
		this.l1 = l1;
	}

}
