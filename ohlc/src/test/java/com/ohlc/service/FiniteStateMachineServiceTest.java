package com.ohlc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.model.BAROHLCEntity;
import com.ohlc.repository.OHLCRepo;
import com.ohlc.repository.TradeBarRepo;
import com.ohlc.transformer.BAROHLCEntityToBAROHLCJsonTf;
import com.ohlc.utils.Singleton;

@RunWith(SpringJUnit4ClassRunner.class)
public class FiniteStateMachineServiceTest {

	private static Integer tradeBarCounter = 1;
	
	@Mock
	private OHLCRepo repo;
	
	@Mock
	private  TradeBarRepo tradeRepo;
	
	
	@InjectMocks
	private FiniteStateMachineService service = new FiniteStateMachineService();
	
	@Mock
	private BAROHLCEntityToBAROHLCJsonTf transformer;
	
	BAROHLCJson bar1 = new BAROHLCJson("sym", "t", new Float(1), new Float(1), new Float(1), "side", new Long(1), new Long(1));
	BAROHLCJson bar2 = new BAROHLCJson("sym2", "t2", new Float(1), new Float(1), new Float(1), "side2", new Long(1), new Long(1));

	List<BAROHLCJson> mockList = Arrays.asList(bar1, bar2);
	
	BAROHLCEntity barEntity1 = new BAROHLCEntity(new Long(1), "", "", new Float(1), new Float(1), new Float(1), "", new Long(1), new Long(1), new Date());
	BAROHLCEntity barEntity2 = new BAROHLCEntity(new Long(1), "", "", new Float(1), new Float(1), new Float(1), "", new Long(1), new Long(1), new Date());
	
	List<BAROHLCEntity> mockEntityList = Arrays.asList(barEntity1, barEntity2);
	

    @Ignore
    @Test
	public void getOHLCJsons() {
		try {
			Mockito.when(repo.findLastUnPublishedBars()).thenReturn(mockEntityList);
			
			CollectionUtils.collect(repo.findLastUnPublishedBars(), Singleton.getInstance(BAROHLCEntityToBAROHLCJsonTf.class), new ArrayList<BAROHLCJson>());	
		} catch(Exception e) {
		}
	}
}
