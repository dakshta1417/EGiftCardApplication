package com.egiftcard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @Column(name = "userid")
	    private int userId;
	    
	    @NotBlank(message = "First name cannot be blank")
	    @Column(name = "first_name")
	    private String firstName;
	    
	    @NotBlank(message = "Last name cannot be blank")
	    @Column(name = "last_name")
	    private String lastName;
	    
	    @Email(message = "Please, Enter valid mail")
	    private String email;
	    
	    @Pattern(regexp="^[0-9]{10}", message="Mobile number should have 10 digits only")
	    private String mobile;
	    
	    @Size(min=8, message="Minimum password length should be 8 characters")
	    private String password;
	    
	    private String address;
	    
	    public int getUserId() {
	        return userId;
	    }
	    public void setUserId(int userId) {
	        this.userId = userId;
	    }
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getMobile() {
	        return mobile;
	    }
	    public void setMobile(String mobile) {
	        this.mobile = mobile;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getAddress() {
	        return address;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	    
		@Override
	    public String toString() {
	        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
	                + ", mobile=" + mobile + ", password=" + password + ", address=" + address + "]";
	    }
		public User(int userId, @NotBlank(message = "First name cannot be blank") String firstName,
				@NotBlank(message = "Last name cannot be blank") String lastName,
				@Email(message = "Please, Enter valid mail") String email, String mobile,
				@Size(min = 8, message = "Minimum password length should be 8 characters") String password,
				String address) {
			super();
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.mobile = mobile;
			this.password = password;
			this.address = address;
		}
		
		public User() {
			super();
		}
	
	   
	}
	

