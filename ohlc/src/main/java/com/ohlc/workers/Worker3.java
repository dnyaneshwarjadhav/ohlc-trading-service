package com.ohlc.workers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.service.FiniteStateMachineService;
import com.ohlc.utils.Singleton;

/**
 * (WebsocketThread) Client subscriptions come here.
 * Maintains client list, and publishes (transmits) the BAR OHLC data as computed in real time.
 * @author Dnyaneshwar
 *
 */
public class Worker3 implements Runnable {
	
	String fileName="C:/Users/Dnyaneshwar/Desktop/trades/clients_subscriptions.json";

	@Override
	public void run() {
		while (true) {
			subscription();
		}
	}
	
	public void subscription() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while (true) {
			    line = br.readLine();
			    if (line != null) {
			        System.out.println("New client subscription requested : "+line);
			    }
			    
			    publishBARChart();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void publishBARChart() {
		try{
			synchronized(String.class) {
				FiniteStateMachineService fsm = Singleton.getInstance(FiniteStateMachineService.class);
				List<BAROHLCJson> list = fsm.getOHLCJsons();
				if(list!=null && list.size()>0) {
					System.out.println("Data published on----"+new Date());
					
					Iterator<BAROHLCJson> itr = list.iterator();
					while(itr.hasNext()) {
						BAROHLCJson json = new BAROHLCJson();
						if(json!=null && (json.getSym()!=null && json.getP()!=null && json.getTS2()!=null)) {
							System.out.println(json.getSym() + "      " + json.getP() + "     " + json.getTS2());
						}
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
