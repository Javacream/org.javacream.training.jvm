package org.javacream.training.jvm.util.jmx;

import java.util.HashMap;

public class JmxCache implements JmxCacheMBean {

	private HashMap<String, String> internalCache;

	{
		internalCache = new HashMap<String, String>();
	}

	public void clear() {
		internalCache.clear();
	}

	public String get(String key) {
		return internalCache.get(key);
	}

	public int getSize() {
		return internalCache.size();
	}

	public void put(String key, String value) {
		internalCache.put(key, value);
	}

	public void remove(String key) {
		internalCache.remove(key);
	}

}
