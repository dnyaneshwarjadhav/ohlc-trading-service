package com.ohlc.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublishTradeBarJson {
	private List<TradeBarJson> tradeBars;

}
