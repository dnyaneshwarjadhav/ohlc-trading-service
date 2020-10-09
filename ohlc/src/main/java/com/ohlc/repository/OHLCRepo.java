package com.ohlc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohlc.model.BAROHLCEntity;

//@Repository
public interface OHLCRepo extends CrudRepository<BAROHLCEntity, Integer>{

	@Query(nativeQuery = true, value="SELECT * FROM bar_chart p WHERE p.trade_bar_id= (SELECT bar_num FROM trad_bar WHERE is_published=FALSE ORDER BY cre_date ASC LIMIT 1 )")
	List<BAROHLCEntity> findLastUnPublishedBars();
	
	@Query(nativeQuery = true, value="SELECT * FROM bar_chart p  order by cre_Date asc limit 1 ")
	BAROHLCEntity findLastUnPublishedBar();
}
