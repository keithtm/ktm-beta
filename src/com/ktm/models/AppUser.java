package com.ktm.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class AppUser.
 */
public class AppUser {

	/** The Kind. */
	public static String Kind = "AppUser";

	/** The Valid. */
	public static String Valid = "valid";

	/**
	 * Instantiates a new user.
	 * 
	 * @param userId
	 *            the user id
	 * @param createDate
	 *            the create date
	 * @param modifyDate
	 *            the modify date
	 * @param screenName
	 *            the screen name
	 * @param active
	 *            the active
	 * @param emailAddress
	 *            the email address
	 */
	@JsonCreator
	public AppUser(@JsonProperty("userId") String userId,
			@JsonProperty("createDate") Date createDate,
			@JsonProperty("modifyDate") Date modifyDate,
			@JsonProperty("screenName") String screenName,
			@JsonProperty("active") boolean active,
			@JsonProperty("emailAddress") String emailAddress) {
		super();
		this.userId = userId;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.screenName = screenName;
		this.active = active;
		this.emailAddress = emailAddress;
	}

	/** The user id. */
	private String userId;

	/** The create date. */
	private Date createDate;

	/** The modify date. */
	private Date modifyDate;

	/** The screen name. */
	private String screenName;

	/** The active. */
	private boolean active;

	/** The email address. */
	private String emailAddress;

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Gets the creates the date.
	 * 
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Gets the modify date.
	 * 
	 * @return the modify date
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * Gets the screen name.
	 * 
	 * @return the screen name
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Gets the checks if is active.
	 * 
	 * @return the checks if is active
	 */
	public boolean getIsActive() {
		return active;
	}

	/**
	 * Gets the email address.
	 * 
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [" + (userId != null ? "userId=" + userId + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "")
				+ (screenName != null ? "screenName=" + screenName + ", " : "")
				+ "active=" + active + ", "
				+ (emailAddress != null ? "emailAddress=" + emailAddress : "")
				+ "]";
	}

	/**
	 * Sets the creates the date.
	 * 
	 * @param createDate
	 *            the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Sets the modify date.
	 * 
	 * @param modifyDate
	 *            the new modify date
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Test screen name valid.
	 * 
	 * @param screenName
	 *            the screen name
	 * @return the string
	 */
	public String testScreenNameValid(String screenName) {
		// screenName checks
		if (screenName.length() < 3) {
			return "screen name must be at least 3 characters";
		} else if (screenName.length() > 20) {
			return "screen name must be no more than 20 characters";
		} else if (!screenName.matches("[\\w]*")) {
			return "screen name must only contain word characters matching the \\w metacharacter";
		}
		return Valid;
	}

	/**
	 * Checks if is active.
	 * 
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 * 
	 * @param active
	 *            the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Sets the screen name.
	 * 
	 * @param screenName
	 *            the new screen name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Sets the email address.
	 * 
	 * @param emailAddress
	 *            the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
