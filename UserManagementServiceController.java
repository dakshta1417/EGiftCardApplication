package com.egiftcard.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egiftcard.entity.User;
import com.egiftcard.exception.InvalidUserIdException;
import com.egiftcard.exception.NoDataException;
import com.egiftcard.exception.NoSuchUserException;
import com.egiftcard.service.IUserManagementService;

@RestController
@RequestMapping("/api")
public class UserManagementServiceController {

	@Autowired
	private IUserManagementService userServiceCtrl;
	
	//http://localhost:8080/EGiftCardApp/api/getAllUser
	@GetMapping(value="/getAllUser",produces="application/json")
	public ResponseEntity<List<User>> getAllUsers() throws NoDataException
{
		List<User> user= userServiceCtrl.getAllUsers();	
		
			return new ResponseEntity<List<User>> (user,HttpStatus.OK);		
}

	//http://localhost:8080/EGiftCardApp/api/Id
	@GetMapping("/Id/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) throws NoSuchUserException 
{
		User user=userServiceCtrl.getUserById(userId);
		
	return new ResponseEntity<User> (user, HttpStatus.OK);	
}
	
	//http://localhost:8080/EGiftCardApp/api/firstName/Jack
	@GetMapping("/firstName/{firstName}")
	public ResponseEntity<User> searchByFirstName(@PathVariable("firstName") String firstName)
	{
		
		return new ResponseEntity<User> (userServiceCtrl.searchByFirstName(firstName),HttpStatus.FOUND);
	}
	
	//http://localhost:8080/EGiftCardApp/api/Email/
	@GetMapping("/Email/{email}")
	public ResponseEntity<User> searchByEmail(@PathVariable("email") String email)
	{
		return new ResponseEntity<User> (userServiceCtrl.getUserByEmail(email),HttpStatus.FOUND);
	}
	
	//http://localhost:8080/EGiftCardApp/api/Update
	@PutMapping(value="/Update/{userId}",consumes="application/json", produces="application/json")
	public  ResponseEntity<User> updateUserById(@Valid @RequestBody User user,@PathVariable("userId") int userId) throws NoSuchUserException
	{
		return new ResponseEntity<User>(userServiceCtrl.updateUserById(user, userId),HttpStatus.OK);
	}
	
	//http://localhost:8080/EGiftCardApp/api/forgetPassword/
	@PutMapping("/forgetPassword/{email}")
	public ResponseEntity<User> changePassword(@Valid @RequestBody User user,@PathVariable("email") String email) throws NoSuchUserException
	{
		return new ResponseEntity<User>(userServiceCtrl.changePassword(user, email),HttpStatus.NOT_FOUND);
	}

	//http://localhost:8080/EGiftCardApp/api/postUser
	@PostMapping(value="/postUser",consumes="application/json", produces="application/json")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws InvalidUserIdException
	{	
		return new ResponseEntity<User>(userServiceCtrl.registerUser(user),HttpStatus.CREATED);
	}
	
	//http://localhost:8080/EGiftCardApp/api/delete
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("userId") int userId) throws NoDataException
	{
		if(userId==0)
			throw new NoDataException("data not available");
			else
			{
		userServiceCtrl.deleteUserById(userId);
		return new  ResponseEntity<String> ("Deleted user with id " + userId + " successfully",HttpStatus.OK);
	}
		
		
	}
	
}
