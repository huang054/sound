package com.sound.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil<T> {

	Gson gson = new Gson();

	public  List<T> gsonToList(String str) {

		return gson.fromJson(str, new TypeToken<List<T>>() {
		}.getType());
	}

	public T gsonToBean(String str, Class<T> entityClass) {
		return  gson.fromJson(str, entityClass);
	}

	public Map<String, T> gsonToMap(Map maps) {

		return gson.fromJson(gson.toJson(maps), new TypeToken<Map<String, T>>() {
		}.getType());
	}

	public Map<String, T> gsonToMapList(Map maps) {

		return gson.fromJson(gson.toJson(maps), new TypeToken<Map<String, List<T>>>() {
		}.getType());
	}
}
