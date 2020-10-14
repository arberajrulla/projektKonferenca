package com.ikubinfo.konferenca.utils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class UtilMessages {
	
	public final static ResourceBundle bundle = ResourceBundle
			.getBundle("com.ikubinfo.konferenca.message");
	
	public static void addMessageSuccess(String info, String incomingMessage) { 
		PrimeFaces current = PrimeFaces.current();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, info ,incomingMessage);
		requestContext().addMessage(null, message);
		current.executeScript("PF('dialogSuccess').show()");
	}
	
	public static void addMessageError(String info, String incomingMessage) { 
		PrimeFaces current = PrimeFaces.current();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, info,incomingMessage);
		requestContext().addMessage(null, message);
		current.executeScript("PF('dialogError').show()");
	}
	
	public static void addCustomMessage(String info, String incomingMessage) { 
		PrimeFaces current = PrimeFaces.current();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, info,incomingMessage);
		requestContext().addMessage(null, message);
		current.executeScript("PF('dialogCustom').show()");
	}
	
	private static FacesContext requestContext() {
		return FacesContext.getCurrentInstance();
	}

}
