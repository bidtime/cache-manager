package org.bidtime.cache;

import org.apache.commons.lang.StringUtils;

/**
 * 不做登录验证枚举
 * 
 * @author jss
 * 
 */
public class MemcacheKeyManage extends CacheManage {
	
	public MemcacheKeyManage() {
		
	}

	public String getKeyId(String key) {
		if (StringUtils.isNotEmpty(userFlag)) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(key);
				sb.append(userFlag);
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
				sb.append(key);
				sb.append(userFlag);
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
	
	@Override
	public void set(String key, int seconds, Object value) {
		super.set(getKeyId(key), seconds, value);
	}
	
	@Override
	public void replace(String key, int seconds, Object value) {
		super.replace(getKeyId(key), seconds, value);
	}
	
	@Override
	public Object get(String key, boolean delete) {
		return super.get(getKeyId(key), false);
	}
	
	@Override
	public void delete(String key) {
		super.delete(getKeyId(key));
	}

	protected String userFlag;

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

}
