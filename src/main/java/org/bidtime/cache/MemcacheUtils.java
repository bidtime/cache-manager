package org.bidtime.cache;

/**
 * 
 * jss
 */
public class MemcacheUtils {
	
    protected static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	
    protected static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
	// equals
	
    protected static boolean equalsWithoutEmpty(String oldValue, String newValue) {
		if ( isNotEmpty(oldValue) ) {
			return oldValue.equals(newValue);
		} else {
			return false;
		}
	}
    
	public static boolean notEquals(String oldValue, String newValue) {
		if ( isNotEmpty(oldValue) ) {
			return !oldValue.equals(newValue);
		} else {
			return false;
		}
	}

    protected static boolean equalsIgnoreCaseWithoutEmpty(String oldValue, String newValue) {
		if ( isNotEmpty(oldValue) ) {
			return oldValue.equalsIgnoreCase(newValue);
		} else {
			return false;
		}
	}
	
    protected static boolean eq(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    
    protected static boolean eqIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }
	
//	// dec
//	
//	public void dec(MemcachedClient cacheClient, String key, int seconds) {
//		inc(cacheClient, key, seconds, -1);
//	}
//	
//	public void dec(MemcachedClient cacheClient, String key, String ext, int seconds) {
//		inc(cacheClient, key, ext, seconds, -1);
//	}
//	
//	public void dec(MemcachedClient cacheClient, String key, String ext, int seconds, int var) {
//		inc(cacheClient, key, ext, seconds, -var);
//	}
//
//	// inc
//	
//	public void inc(MemcachedClient cacheClient, String key, int seconds) {
//		inc(cacheClient, key, seconds, 1);
//	}
//	
//	public void inc(MemcachedClient cacheClient, String key, String ext, int seconds) {
//		inc(cacheClient, key, ext, seconds, 1);
//	}
//
//	public void inc(MemcachedClient cacheClient, String key, String ext, int seconds, int var) {
//		this.inc(cacheClient, key + ext, seconds, var);
//	}
//	
//	//     inc raw
//	
//	public void inc(MemcachedClient cacheClient, String key, int seconds, int var) {
//		Object value = get(cacheClient, key);
//		if (value == null) {
//			set(cacheClient, key, seconds, var);
//		} else {
//			if (value instanceof Integer) {
//				set(cacheClient, key, seconds, (Integer) value + var);
//			} else if (value instanceof Long) {
//				set(cacheClient, key, seconds, (Long) value + var);
//			} else if (value instanceof Short) {
//				set(cacheClient, key, seconds, (Short) value + var);
//			} else if (value instanceof Double) {
//				set(cacheClient, key, seconds, (Double) value + var);
//			} else if (value instanceof Float) {
//				set(cacheClient, key, seconds, (Float) value + var);
//			} else {
//				int n = Integer.parseInt(String.valueOf(value));
//				set(cacheClient, key, seconds, n + var);
//			}
//		}
//	}
//
//	public Integer getInteger(MemcachedClient cacheClient, String key) {
//		Object value = get(cacheClient, key);
//		if (value != null) {
//			if (value instanceof Number) {
//				return ((Number) value).intValue();
//			} else {
//				return Integer.parseInt(String.valueOf(value));
//			}
//		} else {
//			return null;
//		}
//	}
//
//	// compareTo
//
//	public int compareTo(MemcachedClient cacheClient, String key, Integer value) {
//		Integer oldVal = getInteger(cacheClient, key);
//		if (oldVal != null) {
//			return oldVal.compareTo(value);
//		} else {
//			return -1;
//		}
//	}
//
//	public int compareTo(MemcachedClient cacheClient, String key, int value) {
//		Integer oldVal = getInteger(cacheClient, key);
//		if (oldVal != null) {
//			return oldVal.compareTo(value);
//		} else {
//			return -1;
//		}
//	}

}
