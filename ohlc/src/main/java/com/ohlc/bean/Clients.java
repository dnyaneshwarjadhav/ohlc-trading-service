package com.ohlc.bean;

import lombok.Data;

@Data
public class Clients {

	private String event;
	private String symbol;
	private Long interval;
}
