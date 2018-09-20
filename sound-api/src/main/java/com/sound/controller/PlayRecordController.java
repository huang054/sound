package com.sound.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.AudioModel;
import com.sound.model.Currency;
import com.sound.model.PlayRecord;
import com.sound.model.UserModel;
import com.sound.service.AudioService;
import com.sound.service.CurrencyService;
import com.sound.service.PlayRecordService;
import com.sound.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
  
@RestController
@Api(description= "播放音频获取的币")
@RequestMapping(path = "/charts")
public class PlayRecordController extends BaseController<PlayRecord> {

	@Autowired
	private AudioService audioService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private PlayRecordService playService;
	@Autowired
	private UserService userService;

	@ApiOperation(value = "播放音频获取的币数量")
	@GetMapping(path = "/userPlayAudio.do")
	public Response userPlayAudio(String userPhoneNum, String audioId, String playTime) {
		Response res = new Response();
		UserModel user = userService.findByUserName(userPhoneNum);
		AudioModel audio = audioService.findById(Long.parseLong(audioId));
		PlayRecord playRecord = playService.find(user.getId(), Long.parseLong(audioId));
		List<Currency> currency = currencyService.findCurrencyByAudioId(Long.parseLong(audioId));
		if (null == playRecord) {
			PlayRecord play = new PlayRecord();
			play.setAudioId(Long.parseLong(audioId));
			play.setAudioPlayTime(Long.parseLong(playTime));
			play.setUserId(user.getId());
			playService.save(play);
			for (Currency c : currency) {
				c.setCurrencyAccount(new BigDecimal(playTime)
						.divide(new BigDecimal(audio.getDurantionSec()), 8, RoundingMode.HALF_UP)
						.multiply(c.getCurrencyAccount()).setScale(8));
			}
			res.setData(currency);
			res.setMsg("获取币成功");
			res.setRespone(ParamCode.SUCSESS);
			return res;
		}
		if (Long.parseLong(playTime) == audio.getDurantionSec()) {
			playRecord.setAudioPlayTime(Long.parseLong(playTime));
			playService.save(playRecord);
			res.setData(currency);
			res.setMsg("获取币成功");
			res.setRespone(ParamCode.SUCSESS);
			return res;
		}
		if (playRecord.getAudioPlayTime() > 0) {
			if (playRecord.getAudioPlayTime() < Long.parseLong(playTime)) {
				playRecord.setAudioPlayTime(Long.parseLong(playTime));
				playService.save(playRecord);
				for (Currency c : currency) {
					c.setCurrencyAccount(new BigDecimal(playTime)
							.subtract(new BigDecimal(playRecord.getAudioPlayTime())
									.divide(new BigDecimal(audio.getDurantionSec()), 8, RoundingMode.HALF_UP))
							.multiply(c.getCurrencyAccount()).setScale(8));
				}
				res.setData(currency);
				res.setMsg("获取币成功");
				res.setRespone(ParamCode.SUCSESS);
				return res;
			}

		}
		return res;
	}

}
