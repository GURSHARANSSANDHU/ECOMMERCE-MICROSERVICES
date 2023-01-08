package com.example.Userservice;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface DAO extends JpaRepositoryImplementation <User, Integer> 
{
	

}
