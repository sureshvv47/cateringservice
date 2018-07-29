package com.apache.cxf.spring.hibernate.util;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

public class serviceException implements Serializable {
	private String faultCode;
	private String faultMessage;
	
	public serviceException() {	
	}
	
	public String getFaultCode() {
		return faultCode;
	}
	
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	
	public String getFaultMessage() {
		return faultMessage;
	}
	
	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
}