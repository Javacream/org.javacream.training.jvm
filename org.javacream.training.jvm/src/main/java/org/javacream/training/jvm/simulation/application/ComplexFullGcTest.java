package org.javacream.training.jvm.simulation.application;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ComplexFullGcTest {

	public static void main(String[] args) throws Exception{
		while(true){
			ArrayList<SoftReference<Document>> cache = new ArrayList<SoftReference<Document>>();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(ComplexFullGcTest.class.getResourceAsStream("/demo.xml"));
			cache.add(new SoftReference<Document>(doc));
			System.out.println("Parsing document done");
		}
	}
}
