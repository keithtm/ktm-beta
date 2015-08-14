package com.ktm.services.data.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.ktm.models.AppUser;
import com.ktm.services.data.DataAccess;

// TODO: Auto-generated Javadoc
/**
 * The Class DataAccessGdatastore.
 * 
 * Typically would not use NoSQL datastore for everything. But, Google makes the
 * datastore freemium, but not their SQL database. So, using NoSQL datastore for
 * everything in this implementation.
 */
public class DataAccessGdatastore implements DataAccess {

	/** The logger. */
	protected final Log logger = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ktm.services.data.DataAccess#putUser(com.ktm.models.AppUser)
	 */
	@Override
	public void putUser(AppUser appUser) {
		// saves the AppUser data to the datastore
		// Kind=AppUser, Key=userId
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Entity newEntity = new Entity(AppUser.Kind, appUser.getUserId());
		newEntity.setProperty("userId", appUser.getUserId());
		newEntity.setProperty("createDate", appUser.getCreateDate());
		newEntity.setProperty("modifyDate", appUser.getModifyDate());
		newEntity.setProperty("screenName", appUser.getScreenName());
		newEntity.setProperty("active", appUser.getIsActive());
		newEntity.setProperty("emailAddress", appUser.getEmailAddress());
		datastore.put(newEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ktm.services.data.DataAccess#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String userId) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key oldKey = KeyFactory.createKey(AppUser.Kind, userId);
		datastore.delete(oldKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ktm.services.data.DataAccess#getUserForId(java.lang.String)
	 */
	@Override
	public AppUser getUserForId(String userId) {
		// get the AppUser from the datastore for the given key
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key oldKey = KeyFactory.createKey(AppUser.Kind, userId);
		try {
			Entity oldEntity = datastore.get(oldKey);
			AppUser appUser = new AppUser(
					(String) oldEntity.getProperty("userId"),
					(Date) oldEntity.getProperty("createDate"),
					(Date) oldEntity.getProperty("modifyDate"),
					(String) oldEntity.getProperty("screenName"),
					(boolean) oldEntity.getProperty("active"),
					(String) oldEntity.getProperty("emailAddress"));
			return appUser;
		} catch (Exception e) {
			logger.warn(e.toString());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ktm.services.data.DataAccess#isUserScreenNameUnique(java.lang.String)
	 */
	@Override
	public boolean isUserScreenNameUnique(String screenName) {
		// query users for screenName
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query(AppUser.Kind);
		Filter queryFilter = new FilterPredicate("screenName",
				FilterOperator.valueOf("EQUAL"), screenName);
		query.setFilter(queryFilter);
		int ifetchLimit = Integer.parseInt("1");
		List<Entity> resultingList = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(ifetchLimit));
		if (!resultingList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
