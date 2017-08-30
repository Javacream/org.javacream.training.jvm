package org.javacream.training.jvm.util.jmx;

public interface JmxCacheMBean {
 
	int getSize();
	void put(String key, String value);
	String get (String key);
	void clear();
	void remove(String key);
}
