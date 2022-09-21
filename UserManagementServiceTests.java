package com.egiftcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.egiftcard.dao.IUserManagementServiceRepository;
import com.egiftcard.entity.User;
import com.egiftcard.exception.InvalidUserIdException;
import com.egiftcard.exception.NoDataException;
import com.egiftcard.exception.NoSuchUserException;
import com.egiftcard.service.IUserManagementService;

@SpringBootTest
public class UserManagementServiceTests {

	@Autowired 
	private IUserManagementService userManageService;
	
	@MockBean
	private IUserManagementServiceRepository userManageRepo;
	
	@Test
	void saveUserTest() throws InvalidUserIdException
	{
		
		User user1=new User(11,"Mahi","Jha","mahi@gmail.com","9192829132","sa&123","19 B Block");
		//User user2= new User(20,"Dakshta","Khatri","daksht@gmail.com","9198734343","Dak@132","Gumti");
		when(userManageRepo.save(user1)).thenReturn(user1);
		assertEquals(user1,userManageService.registerUser(user1));	
	}
	
	@Test
	void testGetUserByIdShouldThrowNoSuchUserException() 
	{
		
		assertThrows(NoSuchUserException.class, ()->{
			userManageService.getUserById(12);
			});
	}
	
	@Test
	void testDeleteUserByIdShouldThrowNoDataException()
	{
		assertThrows(NoDataException.class,()->{
		userManageService.deleteUserById(20);
		});
	}
	
	@Test
	void deleteUserTest() throws NoDataException 
	{
		@SuppressWarnings("unused")
		User user=new User(11,"Mahi","Jha","mahi@gmail.com","2324213354","sa&123","19 B Block");
		//verify(userManageService.deleteUserById(23));
		userManageRepo.deleteById(11);
		verify(userManageRepo,times(1)).deleteById(11);
		
	}
	
	
}
