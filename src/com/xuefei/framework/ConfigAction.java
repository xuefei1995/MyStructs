package com.xuefei.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.xuefei.framework.bean.Action;
import com.xuefei.framework.bean.Result;



public class ConfigAction {
	
	private Map<String,Action> actions=new HashMap<String,Action>();
	
	public ConfigAction() {
		init();
	}
	
	//封装action对象
	@SuppressWarnings("unchecked")
	public void init(){
		Document doc = XMLUtil.getDoc();
		List<Element> nodes = doc.selectNodes("//action");
		for (Element element : nodes) {
			Action ac=new Action();
			ac.setName(element.attributeValue("name"));
			ac.setPath(element.attributeValue("path"));
			ac.setMethod(element.attributeValue("method"));
			//封装对应的result对象
			List<Element> elem = element.elements("result");
			Map<String,Result> map=new HashMap<String,Result>();
			for (Element ele : elem) {
				Result rs=new Result();
				rs.setName(ele.attributeValue("name"));
				rs.setType(ele.attributeValue("type"));
				rs.setUri(ele.getText().trim());
				map.put(rs.getName(), rs);
			}
			ac.setMap(map);
			actions.put(ac.getName(), ac);
		}
	}
	
	public Map<String, Action> getActions() {
		return actions;
	}
	
	
}
