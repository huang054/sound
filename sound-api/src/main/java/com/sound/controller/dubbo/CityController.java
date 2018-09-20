package com.sound.controller.dubbo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.service.dubbo.CityDubboConsumerService;

@RestController
public class CityController {
  /*  @Autowired
	private CityDubboConsumerService cityDubboConsumerService;
	@RequestMapping("/city")
	public Response city() {
		Response res = new Response();
		String city=cityDubboConsumerService.printCity();
		   res.setRespone(ParamCode.SUCSESS);
	        res.setMsg("sucsess");
	        res.setData(city);
	        return res;
	}*/
}
