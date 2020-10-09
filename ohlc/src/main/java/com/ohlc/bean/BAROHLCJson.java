package com.ohlc.bean;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class BAROHLCJson {
	@JsonProperty("sym")
	public String sym;
	
	@JsonProperty("T")
	public String t;
	
	@JsonProperty("P")
	public Float p;
	
	@JsonProperty("Q")
	public Float q;
	
	@JsonProperty("TS")
	public Float tS;
	
	@JsonProperty("side")
	public String side;
	
	@JsonProperty("TS2")
	public Long tS2;

}
