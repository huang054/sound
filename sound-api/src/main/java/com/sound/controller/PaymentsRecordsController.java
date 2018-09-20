package com.sound.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.PaymentsRecords;
import com.sound.service.PaymentsRecordsService;
import com.sound.vo.Charts;
import com.sound.vo.PlayMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
  
@RestController
// @Api(tags="用户排行榜")
@RequestMapping(path = "/charts")
public class PaymentsRecordsController extends BaseController<PaymentsRecords> {

	private static final Logger logger = LoggerFactory.getLogger(PaymentsRecordsController.class);
	@Autowired
	private PaymentsRecordsService paymentService;

	// @ApiOperation(value = "排名接口")
	@GetMapping(path = "/charts.do")
	public Response charts() {
		Map<String, Object> map = new HashMap<>();
		Response res = new Response<>();

		try {
			List<Charts> charts = paymentService.findChartsByPayments();
			logger.info("排名接口已经废弃"+charts.toString());
			res.setRespone(ParamCode.SUCSESS);
			map.put("charts", charts);
			res.setData(map);
			res.setMsg("查询排名数据成功");
		} catch (Exception e) {
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
			res.setMsg("查询排名数据失败");
		}

		return res;
	}

}
