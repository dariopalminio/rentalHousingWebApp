package com.daro.rental.housing.controller;
import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * @author Dario Palminio
 * 
 */
@ManagedBean(name="localeController")
@SessionScoped
@Controller
public class LocaleController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(LocaleController.class);
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public LocaleController(){
    	logger.info("LocaleController constructor.");
    	setLanguage("es");
    }
    
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
    	logger.info("LocaleController.getLanguage:" + locale.getLanguage());
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
    	logger.info("LocaleController.setLanguage.");
        this.locale = new Locale(language);        
        FacesContext context= FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale(language));
		//locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

	public void changeToSpanish(){
		this.locale = new Locale("es"); 
		FacesContext context= FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		//locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
	
		public void changeToArgentina(){
		FacesContext context= FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale("es", "ar"));
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
}
