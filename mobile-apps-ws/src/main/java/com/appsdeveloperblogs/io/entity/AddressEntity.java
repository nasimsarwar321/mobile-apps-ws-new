package com.appsdeveloperblogs.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.appsdeveloperblogs.share.dto.UserDto;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {

	
	private static final long serialVersionUID = 1386501893709622383L;
	@Id
	@GeneratedValue
	private long id;
	@Column(length = 30, nullable = false)
	private String addressId;
	@Column(length = 30, nullable = false)
	private String city;
	@Column(length = 30, nullable = false)
	private String country;
	@Column(length = 30, nullable = false)
	private String streetName;
	@Column(length = 30, nullable = false)
	private String postalCode;
	@Column(length = 30, nullable = false)
	private String type;
    @ManyToOne     // many address to one user 
    @JoinColumn(name ="user_id" )
	private UserEntity userDetails;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserEntity getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}
	
	@Override
	public String toString() {
		return "AddressEntity [id=" + id + ", addressId=" + addressId + ", city=" + city + ", country=" + country
				+ ", streetName=" + streetName + ", postalCode=" + postalCode + ", type=" + type + ", userDetails="
				+ userDetails + "]";
	}

}