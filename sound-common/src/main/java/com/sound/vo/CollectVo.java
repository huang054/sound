package com.sound.vo;


public class CollectVo<T> {
	private Iterable<T> list;
	public Iterable<T> getList() {
		return list;
	}
	public void setList(Iterable<T> albums) {
		this.list = albums;
	}
	
}
