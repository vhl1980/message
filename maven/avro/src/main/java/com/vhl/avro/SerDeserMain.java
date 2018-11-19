package com.vhl.avro;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.reflect.ReflectData;
import com.vhl.avro.bean.BeanUser;
import com.vhl.avro.bean.BeanUser2;



public class SerDeserMain {

	public static void main(String[] args) {

		Schema schema = ReflectData.get().getSchema(BeanUser.class);

		System.out.println("schema BeanUser = " + schema.toString(true));

		GenericRecord datum = new GenericData.Record(schema);

		//datum.put("name", "john");
		datum.put("city","city");
		datum.put("street","street");
		datum.put("codeP", 78000);
		datum.put("gender", "H");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DatumWriter<GenericRecord> writer =
				new GenericDatumWriter<GenericRecord>(schema);
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		

		try {
			writer.write(datum, encoder);
			
			encoder.flush();
			out.close();
			
			// SER
			System.out.println("-----");
			System.out.println("OUTPUT SER");
			System.out.println(out);
			System.out.println("-----");
			
			// WRITE INTO FILE
			File file = new File("data/data.avro");
			DatumWriter<GenericRecord> writerFile = new GenericDatumWriter<GenericRecord>(schema);
			DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(writerFile);
			dataFileWriter.create(schema, file);


			for(int i=0; i < 1000000; i++){
				dataFileWriter.append(datum);
			}

			
			dataFileWriter.close();

			// DESER
			DatumReader<GenericRecord> reader =
					new GenericDatumReader<GenericRecord>(schema);
			Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(),
					null);
			GenericRecord result = reader.read(null, decoder);
			System.out.println("DESER SIMPLE");
			System.out.println(result);
			
			
			// CHANGE SCHEMA
			Schema schema2 = ReflectData.get().getSchema(BeanUser2.class);
			System.out.println("schema BeanUser2 = " + schema2.toString(true));

			// SCHEMA = ORIGINAL WRITER- SCHEMA 2 = TARGET READER
			DatumReader<GenericRecord> reader2 =
					new GenericDatumReader<GenericRecord>(schema, schema2);
			Decoder decoder2 = DecoderFactory.get().binaryDecoder(out.toByteArray(),
					null);
			GenericRecord result2 = reader2.read(null, decoder2);
			System.out.println("DESER WITH CHANGE SCHEMA ON READ");
			System.out.println(result2);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
