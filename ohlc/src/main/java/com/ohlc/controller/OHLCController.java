package com.ohlc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.bean.Clients;
import com.ohlc.bean.TradeBarJson;
import com.ohlc.service.FiniteStateMachineService;

@Controller
public class OHLCController {
	
	@Autowired
	FiniteStateMachineService fsm;

	@GetMapping("/register")
	public ResponseEntity<?> getAllCitiesByStateId(@RequestBody Clients client) {
		return ResponseEntity.ok(publishBARChart(client));
	}
	
	public List<TradeBarJson> publishBARChart(Clients client) {
		try{
			List<TradeBarJson> tradeBars = new ArrayList<TradeBarJson>();
			
			if(client.getEvent().equals("subscribe")) {
				List<BAROHLCJson> list = fsm.getOHLCJsons();
				if(list!=null && list.size()>0) {
					
					list.forEach(barChrt -> {
						tradeBars.add(populateDate(barChrt, list));
					});
					
					System.out.println("Data published on----"+new Date());
					
					Iterator<BAROHLCJson> itr = list.iterator();
					while(itr.hasNext()) {
						BAROHLCJson json = new BAROHLCJson();
						if(json!=null && (json.getSym()!=null && json.getP()!=null && json.getTS2()!=null)) {
							System.out.println(json.getSym() + "      " + json.getP() + "     " + json.getTS2());
						}
					}
					
					return tradeBars;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private TradeBarJson populateDate(BAROHLCJson barChrt, List<BAROHLCJson> list) {
		Float volume = 0.0f;
		Float o_ = 0.0f;
		Float h_ = 0.0f;
		Float l_ = 0.0f;
		Float c_ = 0.0f;
		
		Iterator<BAROHLCJson> itr = list.iterator();
		while(itr.hasNext()) {
			BAROHLCJson current = itr.next();
			volume = volume + current.getQ();
		}
		
		/**
		 * Agreegate of Volume
		 */
		if(volume > 0 && (list!=null && list.size()>0)) {
			volume = volume / list.size();
		}
		
		return new TradeBarJson(String.valueOf(o_), String.valueOf(h_), String.valueOf(l_), String.valueOf(c_), String.valueOf(volume), "ohlc_notify", barChrt.getSym(), barChrt.getTradeBarId());
	}
}
