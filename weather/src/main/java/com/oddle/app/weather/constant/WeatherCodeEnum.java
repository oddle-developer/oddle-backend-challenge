package com.oddle.app.weather.constant;

public enum WeatherCodeEnum {
	
	DRIZZLE("Drizzle"),
	THUNDERSTORM("Thunderstorm"),
	RAIN("Rain"),
	SNOW("Snow"),
	MIST("Mist"),
	SMOKE("Smoke"),
	HAZE("Haze"),
	DUST("Dust"),
	FOG("Fog"),
	SAND("Sand"),
	ASH("Ash"),
	SQUALL("Squall"),
	TORNADO("Tornado"),
	CLEAR("Clear"),
	CLOUDS("Clouds");
	
	WeatherCodeEnum(String name){
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
