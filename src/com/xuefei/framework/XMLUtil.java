package com.xuefei.framework;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLUtil {
	
	private static InputStream ips= XMLUtil.class.getResourceAsStream("/xuefei.xml");
	
	public static Document getDoc(){
		SAXReader reader=new SAXReader();
		Document doc=null;
		try {
			doc = reader.read(ips);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
