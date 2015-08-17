package com.daro.rental.housing.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "loginController")
//@SessionScoped
@RequestScoped
@Controller
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(LoginController.class);
	
	public final static String FILTER_PROCESSES_URL = "/j_spring_security_check";

	
	private String username;
	private String password;

    @ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * Login.
     *
     * @return the string
     */
	public String login() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extenalContext = facesContext.getExternalContext();
		try {
			RequestDispatcher dispatcher;
			ServletRequest servletRequest = (ServletRequest) extenalContext.getRequest();
			dispatcher = servletRequest.getRequestDispatcher(this.getFilterProcessesUrl());
			dispatcher.forward((ServletRequest) extenalContext.getRequest(),
					(ServletResponse) extenalContext.getResponse());
			facesContext.responseComplete();			
		} catch (ServletException e) {
			logger.error(e.getMessage());
			setErrorMessage(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			setErrorMessage(e.getMessage());
		}
		return null;
	}

	private String getFilterProcessesUrl(){
		
		String url = FILTER_PROCESSES_URL + "?";
		url += UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY + "=" + username + "&";
		url += UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY + "=" + password;
		
		return url;		
	}
	
    /**
     * Login.
     *
     * @return the string
     */
    public String login2() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "Secured";
    }
    
    public String logout(){
        SecurityContextHolder.clearContext();
        return "loggedout";
    }
 
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
 
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    public void setErrorMessage(String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,  null);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
