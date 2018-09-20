package com.sound.common;

public class Response<T>{

	private String code;
	
	private String msg;
	
	private T data;
	
	public Response() {
		setRespone(ParamCode.SUCSESS);
	}
	
	public Response(boolean res, String msg) {
		if(res) {
			setRespone(ParamCode.SUCSESS);
		}else {
			setRespone(ParamCode.FAIL);
		}
		setMsg(msg);
	}
	public Response(ParamCode paramCode) {
		this.code = paramCode.getCode();
		this.msg = paramCode.getMsg();
		
	}
	
	public Response(boolean res) {
		if(res) {
			setRespone(ParamCode.SUCSESS);
		}else {
			setRespone(ParamCode.FAIL);
		}
	}

	public void setRespone(ParamCode params) {
		this.code = params.getCode();
		this.msg = params.getMsg();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}




}
