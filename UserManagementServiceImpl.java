package com.egiftcard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egiftcard.dao.IUserManagementServiceRepository;
import com.egiftcard.entity.User;
import com.egiftcard.exception.InvalidUserIdException;
import com.egiftcard.exception.NoDataException;
import com.egiftcard.exception.NoSuchUserException;

@Service
public class UserManagementServiceImpl implements IUserManagementService{
	
	@Autowired
	private IUserManagementServiceRepository userManage;

	@Override
	public List<User> getAllUsers() throws NoDataException
	{
	
	if(userManage.findAll().isEmpty())
	{
		throw new NoDataException("DATA NOT AVAILABLE!");
	}
		return userManage.findAll();
	}

	@Override
	public User getUserById(int userId) throws NoSuchUserException 
	{
		Optional<User> user=userManage.findById(userId);
		if(!user.isPresent())
		{
			throw new NoSuchUserException("No user present with user id " + userId);
		}
		 return user.get();
	}
	
	@Override
	public User registerUser(User user) throws InvalidUserIdException
	{

		Optional<User> user1=userManage.findById(user.getUserId());
		if(user1.isPresent())
			throw new InvalidUserIdException("Enter correct user ID as User ID "+ user.getUserId() +" already exists");
		else
		{
			return userManage.save(user);
		}
	}

	@Override
	public User updateUserById(User user,int userId) throws NoSuchUserException 
	{
		User preUser=userManage.findById(userId).orElse(null);
		
		if(preUser==null) 
		{
		throw new NoSuchUserException("Cannot update user as User does not exist with id "+ userId );	
		}
		else 
		{
		preUser.setAddress(user.getAddress());
		preUser.setEmail(user.getEmail());
		preUser.setMobile(user.getMobile());
		preUser.setPassword(user.getPassword());
		preUser.setFirstName(user.getFirstName());
		preUser.setLastName(user.getLastName());
		return userManage.save(preUser);
		}
		
	}

	@Override
	public String deleteUserById(int userId) throws NoDataException 
	{

		Optional<User> user=userManage.findById(userId);
		if(user.isPresent())
		{
			userManage.deleteById(userId);
			return "Data deleted";		
		}
		else
			throw new NoDataException("DATA NOT AVAILABLE!! Please, Insert correct id to delete data");
		
	}

		@Override
		public User changePassword(User user, String email) throws NoSuchUserException
		{
		
			User preUser=userManage.findByEmail(email);
			if(preUser==null)
			{
				throw new NoSuchUserException("user with mail id: "+ email +" doesn't exists");
			}
			 preUser.setPassword(user.getPassword());
			
			return userManage.save(preUser);
		}

		@Override
		public User searchByFirstName(String firstName)
		{
			return userManage.findByFirstName(firstName);
		}

		@Override
		public User getUserByEmail(String email) 
		{
			
			return userManage.findByEmail(email);
		}


}

