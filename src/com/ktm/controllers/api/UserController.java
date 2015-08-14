package com.ktm.controllers.api;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.ktm.models.AppError;
import com.ktm.models.AppUser;
import com.ktm.services.data.DataAccess;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
public class UserController {

	/** The logger. */
	protected final Log logger = LogFactory.getLog(getClass());

	/** The user service. */
	private UserService userService = UserServiceFactory.getUserService();

	/** The data access. */
	@Autowired
	private DataAccess dataAccess;

	/** The Constant errorMap. */
	private static final Map<Integer, String> errorMap;
	static {
		Map<Integer, String> staticMap = new HashMap<Integer, String>();
		staticMap.put(101, "User Not Registered");
		staticMap.put(102, "Screen Name Exists Already");
		staticMap.put(103, "User Already Registered");
		staticMap.put(104, null);// this one is dynamic
		errorMap = Collections.unmodifiableMap(staticMap);
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user")
	public ResponseEntity<?> getUser() {

		User user = userService.getCurrentUser();
		AppUser appUser = dataAccess.getUserForId(user.getUserId());

		if (appUser == null) {
			AppError appError = new AppError(101, errorMap);
			return new ResponseEntity<AppError>(appError, HttpStatus.NOT_FOUND); // return
		}

		return new ResponseEntity<AppUser>(appUser, HttpStatus.OK); // return
	}

	/**
	 * Delete user.
	 * 
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/user")
	public ResponseEntity<?> deleteUser() {

		User user = userService.getCurrentUser();
		dataAccess.deleteUser(user.getUserId());

		return new ResponseEntity<String>(user.getUserId(), HttpStatus.OK); // return
	}

	/**
	 * Gets the user screen name unique.
	 * 
	 * @param screenName
	 *            the screen name
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/user/screenNameUnique/{screenName}")
	public ResponseEntity<?> getUserScreenNameUnique(
			@PathVariable String screenName) {

		if (!dataAccess.isUserScreenNameUnique(screenName)) {
			AppError appError = new AppError(102, errorMap);
			return new ResponseEntity<AppError>(appError,
					HttpStatus.BAD_REQUEST); // return
		}

		return new ResponseEntity<String>(AppUser.Valid, HttpStatus.OK);

	}

	/**
	 * Post user.
	 * 
	 * @param appUser
	 *            the app user
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/user")
	public ResponseEntity<?> postUser(@RequestBody AppUser appUser) {

		User user = userService.getCurrentUser();

		// use values from google account
		appUser.setUserId(user.getUserId());
		appUser.setEmailAddress(user.getEmail());
		// set other non-screenName values
		appUser.setActive(true);
		Date now = new Date();
		appUser.setCreateDate(now);
		appUser.setModifyDate(now);

		// check unique
		AppUser appUserTestExists = dataAccess.getUserForId(user.getUserId());

		if (appUserTestExists != null) {
			AppError appError = new AppError(103, errorMap);
			return new ResponseEntity<AppError>(appError,
					HttpStatus.BAD_REQUEST); // return
		}

		String testScreenNameResult = appUser.testScreenNameValid(appUser
				.getScreenName());
		if (testScreenNameResult != AppUser.Valid) {
			AppError appError = new AppError(104, testScreenNameResult);
			return new ResponseEntity<AppError>(appError,
					HttpStatus.BAD_REQUEST); // return
		}

		if (!dataAccess.isUserScreenNameUnique(appUser.getScreenName())) {
			AppError appError = new AppError(102, errorMap);
			return new ResponseEntity<AppError>(appError,
					HttpStatus.BAD_REQUEST); // return
		}

		dataAccess.putUser(appUser);

		return new ResponseEntity<AppUser>(appUser, HttpStatus.OK); // return
	}

	/**
	 * Put user.
	 * 
	 * @param appUser
	 *            the app user
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/user")
	public ResponseEntity<?> putUser(@RequestBody AppUser appUser) {

		User user = userService.getCurrentUser();

		// use values from google account
		appUser.setUserId(user.getUserId());
		appUser.setEmailAddress(user.getEmail());
		// set other non-screenName values
		appUser.setActive(appUser.isActive());
		Date now = new Date();
		appUser.setModifyDate(now);

		// check exists
		AppUser appUserTestExists = dataAccess.getUserForId(user.getUserId());

		if (appUserTestExists == null) {
			AppError appError = new AppError(101, errorMap);
			return new ResponseEntity<AppError>(appError, HttpStatus.NOT_FOUND); // return
		}
		appUser.setCreateDate(appUserTestExists.getCreateDate());

		// other validations
		String testScreenNameResult = appUser.testScreenNameValid(appUser
				.getScreenName());
		if (testScreenNameResult != AppUser.Valid) {
			AppError appError = new AppError(104, testScreenNameResult);
			return new ResponseEntity<AppError>(appError,
					HttpStatus.BAD_REQUEST); // return
		}

		// if new screen name, check it is unique
		if (!appUser.getScreenName().equals(appUserTestExists.getScreenName())) {
			if (!dataAccess.isUserScreenNameUnique(appUser.getScreenName())) {
				AppError appError = new AppError(102, errorMap);
				return new ResponseEntity<AppError>(appError,
						HttpStatus.BAD_REQUEST); // return
			}
		}

		dataAccess.putUser(appUser);

		return new ResponseEntity<AppUser>(appUser, HttpStatus.OK); // return
	}
}
