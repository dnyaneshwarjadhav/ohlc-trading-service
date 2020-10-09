package com.ohlc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bar_chart")
public class BAROHLCEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sym")
	public String sym;
	
	@Column(name = "t")
	public String t;
	
	@Column(name = "p")
	public Float p;
	
	@Column(name = "q")
	public Float q;
	
	@Column(name = "ts")
	public Float tS;
	
	@Column(name = "side")
	public String side;
	
	@Column(name = "ts2")
	public Long tS2;
	
	@Column(name = "trade_bar_id")
	public Long tradeBarId;
	
	@Column(name = "cre_date")
	public Date creDate;

}
