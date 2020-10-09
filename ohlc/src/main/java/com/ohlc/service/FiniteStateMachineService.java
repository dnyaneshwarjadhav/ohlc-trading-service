package com.ohlc.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.model.BAROHLCEntity;
import com.ohlc.model.TradeBarEntity;
import com.ohlc.repository.OHLCRepo;
import com.ohlc.repository.TradeBarRepo;
import com.ohlc.transformer.BAROHLCEntityToBAROHLCJsonTf;
import com.ohlc.utils.CommonUtilities;
import com.ohlc.utils.Singleton;

@Service
public class FiniteStateMachineService {

	private static Integer tradeBarCounter = 1;
	
	@Autowired
	private OHLCRepo repo;
	
	@Autowired
	private  TradeBarRepo tradeRepo;
	
	@Autowired
	private BAROHLCEntityToBAROHLCJsonTf transformer;

	public void storeOHLCData(BAROHLCJson json) {
		BAROHLCEntity entity = new BAROHLCEntity();
		BeanUtils.copyProperties(json, entity);
		repo.save(entity);
	}
	
	/**
	 * This method will pull out objects for generating BAR Chart
	 * with range of input param long timestamp + 15 seconds from original list
	 * @param timestamp
	 * @return
	 */
	public void constructBARChart(long timestamp) {
		try {
			getDatesBetweenStartAndFinishWithFilter(timestamp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Filter dates with Lambda
     *
     * @throws ParseException
     */
    private List<BAROHLCJson> getDatesBetweenStartAndFinishWithFilter(long endDateTs)  {
    	tradeBarCounter = tradeBarCounter + 1;
    	Date endDate = new Date(endDateTs);
    	Date startDate = CommonUtilities.addTimeBySeconds(endDate, -15);
    	System.out.println("Start Date : "+startDate + ", End Date : " + endDate);
    	
    	//Making clone copy to avoid data loss from 'ohlcJsonList' list
    	List<BAROHLCEntity> list = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
    	
    	//This should perform at DB level
    	List<BAROHLCEntity> charGeneratingList = list.stream()
                .filter(ohlcJsn -> new Date(ohlcJsn.getTS2()).after(startDate) && new Date(ohlcJsn.getTS2()).before(endDate))
                .collect(Collectors.toList());
    	
    	charGeneratingList.parallelStream().forEach(chart -> {
    		chart.setTradeBarId(new Long(tradeBarCounter));
    		repo.save(chart);
    	});
    	
    	tradeRepo.save(new TradeBarEntity(tradeBarCounter, false, new Date()));
    	
    	System.out.println("charGeneratingList : "+charGeneratingList.size());
    	return CollectionUtils.collect(charGeneratingList, this.transformer, new ArrayList<BAROHLCJson>());
    }

	public List<BAROHLCJson> getOHLCJsons() {
		try {
			return CollectionUtils.collect(repo.findLastUnPublishedBars(), Singleton.getInstance(BAROHLCEntityToBAROHLCJsonTf.class), new ArrayList<BAROHLCJson>());	
		} catch(Exception e) {
			return null;
		}
	}
	
	public BAROHLCJson getLast() {
		try {
			return this.transformer.transform(repo.findLastUnPublishedBar());	
		} catch(Exception e) {
			return null;
		}
	}
}
