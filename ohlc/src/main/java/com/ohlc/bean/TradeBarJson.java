package com.ohlc.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeBarJson {
	private Integer id;
	private List<BAROHLCJson> barRepresentation;
	
	
}
