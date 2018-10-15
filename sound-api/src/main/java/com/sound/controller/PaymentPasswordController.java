package com.sound.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.PaymentPassword;
import com.sound.model.UserModel;
import com.sound.service.PaymentPasswordService;
import com.sound.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "支付密码")
@RequestMapping(path = "/paymentPassword")
public class PaymentPasswordController extends BaseController<PaymentPassword>{
	
	@Autowired
	private PaymentPasswordService paymentPasswordService;
	
	@Resource
	private RedisService redisService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentPasswordController.class);
	

	@ApiOperation(value = "判断是否设置支付密码接口")
	@GetMapping(path = "/paymentPassword.do")
	public Response isPaymentPassword(HttpServletRequest request) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("请重新登陆");
			return res;
		}
		
		try {
			PaymentPassword paymentPassword=paymentPasswordService.findPaymentPasswordByuserId(userModel.getId());
			if(null==paymentPassword) {
				res.setData(false);
			}else {
				res.setData(true);
			}
			res.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
		}
		return res;
	}

	@ApiOperation(value = "设置支付密码接口")
	@GetMapping(path = "/savePaymentPassword.do")
	public Response savePaymentPassword(HttpServletRequest request,String paymentPassword) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("请重新登陆");
			return res;
		}
		try {
			PaymentPassword password = new PaymentPassword();
			password.setPaymentPassword(paymentPassword);
			password.setUserId(userModel.getId());
			paymentPasswordService.save(password);
			res.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
		}
		return res;
	}
	
	
}
