package com.appsdeveloperblogs.share.dto;

import java.io.Serializable;
import java.util.List;

import com.appsdeveloperblogs.ui.model.request.AddressRequestModel;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 8789242481981954685L;
	private int id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassoword;
	private String emailVerificationToken;
	private boolean emailVerificationStatus = false;
	private List<AddressDto> addresses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassoword() {
		return encryptedPassoword;
	}

	public void setEncryptedPassoword(String encryptedPassoword) {
		this.encryptedPassoword = encryptedPassoword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public boolean setEmailVerificationStatus(boolean emailVerificationStatus) {
		return this.emailVerificationStatus = emailVerificationStatus;
	}

//	public List<AddressDto> getaddresses() {
//		return addresses;
//	}
//
//	public void setAddress(List<AddressDto> addresses) {
//		this.addresses = addresses;
//	}
	
	
	public List<AddressDto> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}
//	@Override
//	public String toString() {
//		return addresses.get(0).getUserDetails().toString();
//	}

	
}
