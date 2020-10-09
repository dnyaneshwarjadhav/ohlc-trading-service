package com.ohlc.service;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohlc.workers.Worker1;
import com.ohlc.workers.Worker2;
import com.ohlc.workers.Worker3;

@Service
public class TailFileReader {
	
	@Autowired
	private FiniteStateMachineService fsmService;
	
	Worker1 w1;
	Worker2 w2;
	Worker3 w3;

	public void startAllWorker() {
		w1 = new Worker1(this.fsmService);
		w2 = new Worker2(this.fsmService);
		w3 = new Worker3();

		Thread t1 = new Thread((Runnable) w1);
		Thread t2 = new Thread((Runnable) w2);
		Thread t3 = new Thread((Runnable) w3);

		t1.start();
		t2.start();
		t3.start();
	}

	public void read(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				System.out.println("New Line :: " + line);
				w1.setNewData(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
