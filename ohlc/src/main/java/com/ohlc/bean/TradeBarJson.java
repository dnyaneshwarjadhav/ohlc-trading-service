package com.ohlc.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeBarJson {
	@JsonProperty("o")
	public String o;
	
	@JsonProperty("h")
	public String h;
	
	@JsonProperty("l")
	public String l;
	
	@JsonProperty("c")
	public String c;
	
	@JsonProperty("volume")
	public String volume;
	
	@JsonProperty("event")
	public String event;
	
	@JsonProperty("symbol")
	public String symbol;
	
	@JsonProperty("bar_num")
	public Long barNum;
	
	
}
