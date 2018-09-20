package com.sound.utils;

public enum Color {
	RAD(0, "红色"), ORANGE(1, "橙色"), YELLOW(2, "黄色"), GREEN(3, "绿色"), BLUE(4, "蓝色"), PURPLE(5, "紫色"), PINK(6,
			"粉色"), WHITE(7, "白色"), BLACK(8, "黑色"), BROWN(9, "棕 色"), GREY(10, "灰色");

	private int type;
	private String color;

	private Color(int type, String color) {
		this.type = type;
		this.color = color;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
