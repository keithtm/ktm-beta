package com.ktm.services.data;

import com.ktm.models.AppUser;

// TODO: Auto-generated Javadoc
/**
 * The Interface DataAccess.
 */
public interface DataAccess {

	/**
	 * Put user.
	 * 
	 * @param appUser
	 *            the app user
	 */
	public void putUser(AppUser appUser);

	/**
	 * Delete user.
	 * 
	 * @param userId
	 *            the user id
	 */
	public void deleteUser(String userId);

	/**
	 * Gets the user for id.
	 * 
	 * @param userId
	 *            the user id
	 * @return the user for id
	 */
	public AppUser getUserForId(String userId);

	/**
	 * Checks if is user screen name unique.
	 * 
	 * @param screenName
	 *            the screen name
	 * @return true, if is user screen name unique
	 */
	public boolean isUserScreenNameUnique(String screenName);

}
