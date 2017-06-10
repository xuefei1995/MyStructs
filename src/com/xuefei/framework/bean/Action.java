package com.xuefei.framework.bean;

import java.util.Map;

public class Action {
	private String name;
	private String path;
	private String method;
	private Map<String,Result> map=null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, Result> getMap() {
		return map;
	}
	public void setMap(Map<String, Result> map) {
		this.map = map;
	}
	
}
