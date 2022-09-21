package com.egiftcard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.egiftcard.entity.User;
import com.egiftcard.exception.InvalidUserIdException;
import com.egiftcard.exception.NoDataException;
import com.egiftcard.exception.NoSuchUserException;

@Service
public interface IUserManagementService {

	List<User> getAllUsers()throws NoDataException ;
	
	User getUserById(int userId) throws NoSuchUserException;
	User registerUser(User user) throws InvalidUserIdException;
	String deleteUserById(int userId)throws NoDataException;
	User updateUserById(User user, int userId)throws NoSuchUserException;
	
	User changePassword(User user,String email) throws NoSuchUserException;
	User searchByFirstName(String firstName);
	User getUserByEmail(String email);

	
}
