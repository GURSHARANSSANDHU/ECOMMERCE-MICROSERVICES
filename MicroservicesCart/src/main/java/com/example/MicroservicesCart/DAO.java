package com.example.MicroservicesCart;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;


@Repository
public interface DAO extends JpaRepositoryImplementation<ShoppingCart, Integer> 
{
   
}
