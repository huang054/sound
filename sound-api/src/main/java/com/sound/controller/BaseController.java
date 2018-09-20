
package com.sound.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;

import com.sound.common.MyValidationUtils;
import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.UserModel;
import com.sound.service.BaseService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
 

public class BaseController<T>  {

	@Autowired
	protected BaseService<T> service;
 
	@Value("${qiniu.sound.download}")
	public String soundDownload;

	@Value("${qiniu.imgs.download}")
	public String imgsDsownload;

	public static boolean isLogin(HttpServletRequest request){
		UserModel userModel = getUserInfo(request);

		if(userModel==null||userModel.getId()<=0){
			return false;
		}
		return true;

	}

	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	public static UserModel getUserInfo(HttpServletRequest request){
		
		String ssoToken = getCookieByName(request);
		
		if(ssoToken==null) return null;
		HttpSession session = request.getSession();
		UserModel userModel = (UserModel)session.getAttribute(ssoToken);
		return userModel;
	}
	/**
	 * 根据名字获取cookie
	 * @param request
	 *            cookie名字
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request) {
		String name = "ssoToken";
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		System.out.println(cookieMap.toString());
		if (cookieMap.containsKey(name)) {
			System.out.println(name);
			Cookie cookie = (Cookie) cookieMap.get(name);
			System.out.println(cookie.getValue());
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 *
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	public Response<T> findById(Long id) {
		Response<T> resp = new Response<T>(true);
		if(id==null) {
			resp.setRespone(ParamCode.PARAMERROR);
			return resp;
		}
		try {
			T t = service.findById(id);
			resp.setData(t);
		}catch(Exception e) {
			resp = new Response<T>(false);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	
	public Response<T> save(T t, BindingResult result) {
		if(result.hasErrors()) {
			return new Response<T>(false,MyValidationUtils.parseErrors(result));
		}
		Response<T> resp = new Response<T>(true);
		try {
			service.save(t);
		}catch(Exception e) {
			resp = new Response<T>(false);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	public Response<Iterable<T>> findAll(T t){
		
		Response<Iterable<T>> resp = new Response<Iterable<T>>(true);
		try {
			Example<T> example = Example.of(t);
			Iterable<T> data = service.findAll(example);
			resp.setData(data);
		}catch(Exception e) {
			resp = new Response<Iterable<T>>(false);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	
	public Response<Iterable<T>> findAll() {
		Response<Iterable<T>> resp = new Response<Iterable<T>>(true);
		try {
			Iterable<T> data = service.findAll();
			resp.setData(data);
		}catch(Exception e) {
			resp = new Response<Iterable<T>>(false);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	
	public Response<Void> deleteById(Long id) {
		Response<Void> resp = new Response<Void>(true);
		try {
			service.deleteById(id);
		}catch(Exception e) {
			resp = new Response<Void>(false);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	public Response<Page<T>> findByPage(T t, int page, int size) {
		Response<Page<T>> resp = new Response<Page<T>>(true);
		try {
			Example<T> example = Example.of(t);
			Sort sort = Sort.by(Sort.Direction.DESC, "id");
			PageRequest pageable = PageRequest.of(page, size,sort);
			Page<T> pageT = service.findAll(example,pageable);
			resp.setData(pageT);
		}catch(Exception e) {
			resp = new Response<Page<T>>(false);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
