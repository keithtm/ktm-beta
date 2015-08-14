package com.ktm.models;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class AppError.
 */
public class AppError {

	/**
	 * Instantiates a new app error.
	 * 
	 * @param errorNum
	 *            the error num
	 * @param errorMsg
	 *            the error msg
	 */
	public AppError(int errorNum, String errorMsg) {
		super();
		this.errorNum = errorNum;
		this.errorMsg = errorMsg;
	}

	/**
	 * Instantiates a new app error.
	 * 
	 * @param errorNum
	 *            the error num
	 * @param errorMap
	 *            the error map
	 */
	public AppError(int errorNum, Map errorMap) {
		super();
		this.errorNum = errorNum;
		this.errorMsg = errorMap.get(errorNum).toString();
	}

	/**
	 * Instantiates a new app error.
	 * 
	 * @param errorNum
	 *            the error num
	 * @param errorMap
	 *            the error map
	 * @param property
	 *            the property
	 */
	public AppError(int errorNum, Map errorMap, String property) {
		super();
		this.errorNum = errorNum;
		this.errorMsg = String.format(errorMap.get(errorNum).toString(),
				property);
	}

	/** The error num. */
	private int errorNum;

	/** The error msg. */
	private String errorMsg;

	/**
	 * Gets the error num.
	 * 
	 * @return the error num
	 */
	public int geterrorNum() {
		return errorNum;
	}

	/**
	 * Sets the error num.
	 * 
	 * @param errorNum
	 *            the new error num
	 */
	public void seterrorNum(int errorNum) {
		this.errorNum = errorNum;
	}

	/**
	 * Gets the error msg.
	 * 
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 * 
	 * @param errorMsg
	 *            the new error msg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppError [errorNum=" + errorNum + ", "
				+ (errorMsg != null ? "errorMsg=" + errorMsg : "") + "]";
	}

}
