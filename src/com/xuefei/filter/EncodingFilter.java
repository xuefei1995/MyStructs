package com.xuefei.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//解决POST提交乱码问题
		arg0.setCharacterEncoding("utf-8");
		arg2.doFilter(arg0, arg1);
		
		/*	这里没用对GET请求进行乱码解决，因为按道理来说GET请求通过URL来进行传输的，编码应该为iso-8859-1，需要自己进行手动解码再重新编码
		 *  但是我在实际测试中发现，GET请求的数据直接通过getParameter拿到已经是正确的了，这里还不是很明白，所以下面写的那个装饰者类可以忽略，
		 *  在这里没什么用。如果GET获取到的是乱码的话，就创建MyRequest对象，重写request对象里面的getParameter()等方法来解决。
		 * */
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

//装饰者设计模式
class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	//解决GET乱码问题
	@Override
	public String getParameter(String name) {
		String value = this.request.getParameter(name);
		try {
			if("GET".equals(this.request.getMethod()))
				value=new String(value.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = this.request.getParameterValues(name);
		if("GET".equals(this.request.getMethod())){
			for (String s : values) {
				try {
					s=new String(s.getBytes("iso-8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return values;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> maps = this.request.getParameterMap();
		if("GET".equals(this.request.getMethod())){
			Set<Entry<String, String[]>> set = maps.entrySet();
			Iterator<Entry<String, String[]>> iter = set.iterator();
			while(iter.hasNext()){
				Entry<String, String[]> next = iter.next();
				String[] values = next.getValue();
				for (String s : values) {
					try {
						s=new String(s.getBytes("iso-8859-1"),"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return maps;
	}
}
