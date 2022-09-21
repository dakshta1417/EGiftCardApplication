package com.egiftcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egiftcard.entity.User;

@Repository
public interface IUserManagementServiceRepository extends JpaRepository<User,Integer>{

	public User findByEmail(String email);
	public User findByFirstName(String firstName);
	
}
