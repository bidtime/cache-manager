package org.bidtime.cache;

import org.apache.commons.lang.StringUtils;

/**
 * 不做登录验证枚举
 * 
 * @author jss
 * 
 */
public class CacheFlagKeyManager extends CacheKeyManager {
	
	public CacheFlagKeyManager() {
		
	}

	@Override
	public String getKeyId(String key) {
		if (StringUtils.isNotEmpty(userFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(userFlag);
				sb.append(key);
				return sb.toString();
			} finally {
				sb.setLength(0);
				sb = null;
			}
		} else {
			return key;
		}
	}

	@Override
	public String getKeyId(String key, String ext) {
		if (StringUtils.isNotEmpty(userFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(userFlag);
				sb.append(key);
				sb.append(ext);
				return sb.toString();
			} finally {
				sb.setLength(0);
				sb = null;
			}
		} else {
			return key;
		}
	}

}
