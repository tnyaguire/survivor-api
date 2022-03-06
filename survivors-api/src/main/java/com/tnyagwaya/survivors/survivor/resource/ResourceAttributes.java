package com.tnyagwaya.survivors.survivor.resource;

import java.util.HashMap;

public class ResourceAttributes extends HashMap<String, Object>{

	public void addData(String key, Object value) {
		put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAs(String key, Class<T> valueType) {
		return (T)get(key);
	}
}
