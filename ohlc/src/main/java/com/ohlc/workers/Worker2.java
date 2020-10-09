package com.ohlc.workers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.service.FiniteStateMachineService;
import com.ohlc.utils.CommonUtilities;

/**
 * (FSM) computes OHLC packets based on 15 seconds (interval) 
 * and constructs 'BAR' chart data, based on timestamp TS2. (ignore TS)
 * @author Dnyaneshwar
 *
 */
@Component
public class Worker2 implements Runnable {

	@Autowired
	private BAROHLCJson lastElem;
	
	private FiniteStateMachineService fsm;
	
	public Worker2(FiniteStateMachineService fsm){
        this.fsm = fsm;
    }
	
	@Override
	public void run() {
		while (true) {
			try {
				this.lastElem = this.fsm.getLast();
				if(this.lastElem!=null) {
					if((new Date().getTime() - CommonUtilities.convertTime(this.lastElem.getTS2()!=null?new Long(this.lastElem.getTS2()):new Long(0)).getTime() >= 15)) {
						fsm.constructBARChart(new Long(this.lastElem.getTS2()));
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
