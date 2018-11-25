package com.vhl.bson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vhl.bson.bean.BeanUser;

import de.undercouch.bson4jackson.BsonFactory;

public class SerDerBson {

	public static void main(String[] args) {
		// Create POJO
		BeanUser user = new BeanUser();
		
		user.setName("john");
		user.setCity("city");
		user.setStreet("street");
		user.setCodeP(78000);
		user.setGender("H");
		

	    try {
	    	// ObjectMapperSample 
	    	ObjectMapper mapper = new ObjectMapper(new BsonFactory());
	    	
	    	// Ser
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	mapper.writeValue(baos, user);
			System.out.println("Ser : "+ baos);
			
			// Der
			ByteArrayInputStream bais = new ByteArrayInputStream(
				      baos.toByteArray());
			BeanUser derUser = mapper.readValue(baos.toByteArray(), BeanUser.class);
			System.out.println("User der get name : " +derUser.getName()); 
			
			// ManualSample 
			BsonFactory factory = new BsonFactory();
			 //serialize data
		    ByteArrayOutputStream baosManual = new ByteArrayOutputStream();
		    JsonGenerator gen = factory.createJsonGenerator(baosManual);
		    gen.writeStartObject();
		    gen.writeFieldName("name");
		    gen.writeString(user.getName());
		    gen.close();

		    //deserialize data
		    ByteArrayInputStream baisManuel = new ByteArrayInputStream(
		    		baosManual.toByteArray());
		    JsonParser parser = factory.createJsonParser(baisManuel);
		    BeanUser userManual = new BeanUser();
		    parser.nextToken();
		    while (parser.nextToken() != JsonToken.END_OBJECT) {
		      String fieldname = parser.getCurrentName();
		      System.out.println("SET : "+fieldname);
		      parser.nextToken();
		      if ("name".equals(fieldname)) {
		    	  userManual.setName(parser.getText());
		      }
		    }
		    System.out.println("UserManual der get name : " +userManual.getName()); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}


		
	}

}
