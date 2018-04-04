package org.bidtime.cache;

/**
 * 
 * jss
 */
public abstract class AbstractCache {

	// set
	
	public abstract void set(String key, int seconds, Object o) throws Exception;
	
	// delete
	
	public abstract void delete(String key) throws Exception;
	
	// replace
	
	public abstract void replace(String key, int seconds, Object o) throws Exception;
	
	// get

	public abstract Object get(String key) throws Exception;

}
