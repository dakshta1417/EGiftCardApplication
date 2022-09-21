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
	System.out.println("Inside service of get all users method");
	
	if(userManage.findAll().isEmpty())
	{
		System.out.println("Inside if block");
		throw new NoDataException("DATA NOT AVAILABLE!");
	}
		return userManage.findAll();
	}

	@Override
	public User getUserById(int userId) throws NoSuchUserException 
	{
		System.out.println("Inside service of get user by id method");
		Optional<User> user=userManage.findById(userId);
	//	System.out.println(user.get()+"after 39");
		if(!user.isPresent())
		//if(!(listUser.contains(user.get())))
		{
			throw new NoSuchUserException("No user present with user id " + userId);
		}
		 return user.get();
	}
	
	@Override
	public User RegisterUser(User user) throws InvalidUserIdException
	{
		System.out.println("Inside service of register user method");	
		Optional<User> user1=userManage.findById(user.getUserId());
		if(user1.isPresent())
			throw new InvalidUserIdException("Enter correct user ID as User ID "+ user.getUserId() +" already exists");
		else
		return userManage.save(user);
	}

	@Override
	public User UpdateUserById(User user,int userId) throws NoSuchUserException 
	{
		System.out.println("Inside update user by id method");
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
		//return userManage.saveAndFlush(userManage.findById(userId));
	

	
	@Override
	public String deleteUserById(int userId) throws NoDataException 
	{
		System.out.println("Inside delete user by id method");
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
			System.out.println("Inside change password service layer");
			User preUser=userManage.findByEmail(email);
			if(preUser==null)
			{
				throw new NoSuchUserException("user with mail id: "+ email +" doesn't exists");
			}
			 preUser.setPassword(user.getPassword());
			
			return userManage.save(preUser);
		}

		@Override
		public User SearchByFirstName(String firstName)
		{
			System.out.println("Searching user by first name");
			User user=userManage.findByFirstName(firstName);
			return user;
		}

		@Override
		public User getUserByEmail(String email) {
			
			System.out.println("searching user by email");
			User user=userManage.findByEmail(email);
			return user;
		}


}

