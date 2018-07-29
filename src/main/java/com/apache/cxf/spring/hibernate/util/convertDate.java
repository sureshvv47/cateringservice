package com.apache.cxf.spring.hibernate.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class convertDate {

	public static void main(String args[]) {
		
		Date today = new Date();
		XMLGregorianCalendar cal = toXMLGregorianCalendar(today);
		System.out.println("XMLGregorianCalendar from Date in Java : " + cal);
		
		Date date = toDate(cal);
		System.out.println("java.util.Date from XMLGregorianCalendar in Java : " + date);
	}
	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		GregorianCalendar grecal = new GregorianCalendar();
		grecal.setTime(date);
		XMLGregorianCalendar xmlCal = null;
		try {
			xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(grecal);
		} catch(DatatypeConfigurationException ex) {
			ex.printStackTrace();
		}
		return xmlCal; 
	}
	
	public static Date toDate(XMLGregorianCalendar cal) {
		return cal.toGregorianCalendar().getTime();
	}
}