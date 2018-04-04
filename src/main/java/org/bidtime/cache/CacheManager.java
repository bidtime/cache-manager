package org.bidtime.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import net.spy.memcached.MemcachedClient;

/**
 * 
 * jss
 */
public class CacheManager {
	
	private static final Logger log = LoggerFactory.getLogger(CacheManager.class);
	
	private AbstractCache clientCache;

	protected int defaultTm = 2 * 60 * 60;		// default: 2h = 7200s;
	
	public CacheManager() {
	}

	protected String getKeyId(String key, String ext) {
		return key + ext;
	}
	
	// set
	
	public void set(String key, int seconds, Object value) {
		try {
			clientCache.set(key, seconds, value);
			if (log.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				try {
					sb.append("set ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("set: key {}, {}", key, e);
		}
	}

	public void set(String key, Object value) {
		this.set(key, defaultTm, value);
	}

	public void set(String key, String ext, int seconds, Object value) {
		this.set(getKeyId(key, ext), seconds, value);
	}

	public void set(String key, String ext, Object value) {
		this.set(key, ext, defaultTm, value);
	}
	
	// replace

	public void replace(String key, int seconds, Object value) {
		try {
			clientCache.replace(key, seconds, value);
			if (log.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				try {
					sb.append("replace ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					sb.append(" tm(");
					sb.append(seconds);
					sb.append("s).");
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("replace: key {}, {}", key, e);
		}
	}

	public void replace(String key, Object value) {
		this.replace(key, defaultTm, value);
	}
	
	public void replace(String key, String ext, int seconds, Object value) {
		this.replace(getKeyId(key, ext), seconds, value);
	}
	
	public void replace(String key, String ext, Object value) {
		this.replace(key, ext, defaultTm, value);
	}
	
	// get

	public Object get(String key, boolean delete) {
		Object value = null;
		try {
			value = clientCache.get(key);
			if (log.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				try {
					sb.append("get ");
					sb.append(key);
					sb.append(":");
					sb.append(value);
					log.debug(sb.toString());
				} finally {
					sb.setLength(0); // 设置StringBuffer变量的长度为0
					sb = null;
				}
			}
		} catch (Exception e) {
			log.error("get: key {}, {}", key, e);
		} finally {
			if (delete) {
				this.delete(key);
			}
		}
		return value;
	}

	public Object get(String key) {
		return this.get(key, false);
	}
	
	//      get ext

	public Object get(String key, String ext, boolean delete) {
		return this.get(getKeyId(key, ext), delete);
	}

	public Object get(String key, String ext) {
		return this.get(key, ext, false);
	}
	
	//     get string
	
	public String getString(String key) {
		return getString(key, false);
	}
	
	public String getString(String key, boolean delete) {
		Object obj = get(key, delete);
		return String.valueOf( obj );
	}
	
	// delete
	
	public void delete(String key) {
		try {
			clientCache.delete(key);
		} catch (Exception e) {
			log.error("delete: key {}, {}", key, e);
		}
	}
	
	public void delete(String key, String ext) {
		delete(getKeyId(key, ext));
	}
	
	// equals
	
    public boolean equals(String key, String value) {
		return CacheUtils.eq(getString(key), value);
    }
    
    public boolean notEquals(String key, String value) {
		return !CacheUtils.eq(getString(key), value);
    }
    
    public boolean equals(String key, String newValue, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equals(key, newValue);
    	} else {
    		return CacheUtils.equalsWithoutEmpty(getString(key), newValue);
    	}
    }
    
    public boolean notEquals(String key, String newValue, boolean allowEmpty) {
    	if (allowEmpty) {
    		return notEquals(key, newValue);
    	} else {
    		return CacheUtils.notEquals(getString(key), newValue);
    	}
    }
    
    public boolean equalsIgnoreCase(String key, String value) {
    	return CacheUtils.eqIgnoreCase(getString(key), value);
    }
    
    public boolean equalsIgnoreCase(String key, String newValue, boolean allowEmpty) {
    	if (allowEmpty) {
    		return equalsIgnoreCase(key, newValue);
    	} else {
    		return CacheUtils.equalsIgnoreCaseWithoutEmpty(getString(key), newValue);
    	}
    }
	
	// dec
	
	public void dec(String key) {
		inc(key, -1);
	}
	
	public void dec(String key, String ext) {
		inc(key, ext, -1);
	}
	
	public void dec(String key, String ext, int var) {
		inc(key, ext, -var);
	}

	// inc
	
	public void inc(String key) {
		inc(key, 1);
	}
	
	public void inc(String key, String ext) {
		inc(key, ext, 1);
	}

	public void inc(String key, String ext, int var) {
		this.inc(key + ext, var);
	}
	
	//     inc raw
	
	public void inc(String key, int var) {
		Object value = get(key);
		if (value == null) {
			set(key, var);
		} else {
			if (value instanceof Integer) {
				set(key, (Integer) value + var);
			} else if (value instanceof Long) {
				set(key, (Long) value + var);
			} else if (value instanceof Short) {
				set(key, (Short) value + var);
			} else if (value instanceof Double) {
				set(key, (Double) value + var);
			} else if (value instanceof Float) {
				set(key, (Float) value + var);
			} else {
				int n = Integer.parseInt(String.valueOf(value));
				set(key, n + var);
			}
		}
	}

	public Integer getInteger(String key) {
		Object value = get(key);
		if (value != null) {
			if (value instanceof Number) {
				return ((Number) value).intValue();
			} else {
				return Integer.parseInt(String.valueOf(value));
			}
		} else {
			return null;
		}
	}

	// compareTo

	public int compareTo(String key, Integer value) {
		Integer oldVal = getInteger(key);
		if (oldVal != null) {
			return oldVal.compareTo(value);
		} else {
			return -1;
		}
	}

	public int compareTo(String key, int value) {
		Integer oldVal = getInteger(key);
		if (oldVal != null) {
			return oldVal.compareTo(value);
		} else {
			return -1;
		}
	}
	
	// defaultTm

	public int getDefaultTm() {
		return defaultTm;
	}

	public void setDefaultTm(int defaultTm) {
		this.defaultTm = defaultTm;
	}

	public AbstractCache getClientCache() {
		return clientCache;
	}

	public void setClientCache(AbstractCache clientCache) {
		this.clientCache = clientCache;
	}

}
