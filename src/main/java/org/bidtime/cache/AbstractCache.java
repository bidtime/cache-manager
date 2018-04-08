package org.bidtime.cache;

/**
 * 
 * jss
 */
public abstract class AbstractCache {

	// set
  
  public abstract void setString(String key, int seconds, String s) throws Exception;
  
  public abstract void set(String key, int seconds, Object o) throws Exception;
	
	// delete
	
	public abstract void delete(String key) throws Exception;
	
	// replace
	
	public abstract void replace(String key, int seconds, Object o) throws Exception;
	
	// get

  public abstract String getString(String key, boolean del) throws Exception;

  //public abstract <K> K get(String key, Class<K> k) throws Exception;
  public abstract Object get(String key, boolean del) throws Exception;

}
