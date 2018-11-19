package com.vhl.avro.bean;

import org.apache.avro.reflect.AvroIgnore;
import org.apache.avro.reflect.AvroName;
import org.apache.avro.reflect.Nullable;

public class BeanUser {
	
	@Nullable
	private String name;
	private String city;
	private String street;
	private Integer codeP;
	//@Nullable
	private String gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getCodeP() {
		return codeP;
	}
	public void setCodeP(Integer codeP) {
		this.codeP = codeP;
	}
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	
}
