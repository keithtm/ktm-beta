package com.ktm.controllers.web;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.ktm.models.AppUser;
import com.ktm.services.data.DataAccess;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleDisplay.
 */
public class SimpleDisplay implements Controller {

	/** The logger. */
	protected final Log logger = LogFactory.getLog(getClass());

	/** The view. */
	private String view;

	/**
	 * Sets the view.
	 * 
	 * @param view
	 *            the new view
	 */
	public void setView(String view) {
		this.view = view;
	}

	/** The data access. */
	private DataAccess dataAccess;

	/**
	 * Sets the data access.
	 * 
	 * @param dataAccess
	 *            the new data access
	 */
	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	/** The user service. */
	private UserService userService = UserServiceFactory.getUserService();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// prepare common model for all simple views
		User user = userService.getCurrentUser();
		AppUser appUser = dataAccess.getUserForId(user.getUserId());
		String logoutURL = userService.createLogoutURL("/");

		Map<String, Object> model = new HashMap<>();
		model.put("appUser", appUser);
		model.put("logoutURL", logoutURL);

		return new ModelAndView(view, model);
	}

}
