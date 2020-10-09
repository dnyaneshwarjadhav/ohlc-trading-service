package com.ohlc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohlc.model.TradeBarEntity;

@Repository
public interface TradeBarRepo extends CrudRepository<TradeBarEntity, Integer>{

}
