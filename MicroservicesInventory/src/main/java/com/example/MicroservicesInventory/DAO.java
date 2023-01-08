package com.example.MicroservicesInventory;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;


@Repository
public interface DAO extends JpaRepositoryImplementation<Product, Integer>
{
	
	
	
	public abstract Product getByitemId(int itemId); 

}
