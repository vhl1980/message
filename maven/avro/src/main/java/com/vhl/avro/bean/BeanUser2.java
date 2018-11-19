package com.vhl.avro.bean;

import org.apache.avro.reflect.AvroIgnore;
import org.apache.avro.reflect.AvroName;
import org.apache.avro.reflect.Nullable;

public class BeanUser2 {

	private String street;
	@AvroIgnore
	private String city;
	private Integer codeP;
	@Nullable
	@AvroName(value = "ZZ")
	private String zozo;
	

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
	public String getZozo() {
		return zozo;
	}
	public void setZozo(String zozo) {
		this.zozo = zozo;
	}


	
}
