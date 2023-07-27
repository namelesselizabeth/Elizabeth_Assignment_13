package com.coderscampus.Elizabeth_Assignment_13.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Address {

	private Long userId;
	private User user;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String region;
	private String country;
	private String zipcode;
	@Id
	@GeneratedValue(generator="myGenerator")
	@GenericGenerator(name="myGenerator", strategy = "foreign", parameters=@Parameter(value="user", name = "property"))
	public Long getUserId() {
		return userId;
	}

	@OneToOne(cascade = CascadeType.ALL)@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	@Column(length=200)
	public String getAddressLine1() {
		return addressLine1;
	}

	@Column(length=200)
	public String getAddressLine2() {
		return addressLine2;
	}

	@Column(length=100)
	public String getCity() {
		return city;
	}

	@Column(length=100)
	public String getRegion() {
		return region;
	}

	@Column(length=100)
	public String getCountry() {
		return country;
	}

	@Column(length=15)
	public String getZipcode() {
		return zipcode;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
