package com.ohlc.workers;

import org.springframework.beans.factory.annotation.Autowired;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.service.FiniteStateMachineService;
import com.ohlc.service.JsonParserHelper;
import com.ohlc.utils.Singleton;

/**
 * Reads the Trades data input (line by line from JSON),
 * and sends the packet to the FSM (Finite-State-Machine) thread
 * @author Dnyaneshwar
 *
 */
public class Worker1 implements Runnable {
	
	private String newData;
	
	private FiniteStateMachineService fsmService;
	
	public Worker1(String newData){
		this.newData=newData;
	}
	
	public Worker1(FiniteStateMachineService fsmService){
		this.fsmService=fsmService;
	}

	@Override
	public void run() {
		try {
			while (true) {
				//FiniteStateMachineService fsm = Singleton.getInstance(FiniteStateMachineService.class);
				if(this.newData!=null && !this.newData.isEmpty()) {
					this.fsmService.storeOHLCData(JsonParserHelper.convertJSONToObject(this.newData, BAROHLCJson.class));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setNewData(String newData) {
		this.newData = newData;
	}

}
