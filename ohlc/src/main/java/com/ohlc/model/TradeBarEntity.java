package com.ohlc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "trade_bar")
public class TradeBarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "bar_num")
	public Integer barNum;
	
	@Column(name = "is_published")
	public Boolean isPublished;
	
	@Column(name = "cre_date")
	public Date creDate;

	public TradeBarEntity(Integer barNum, Boolean isPublished, Date creDate) {
		super();
		this.barNum = barNum;
		this.isPublished = isPublished;
		this.creDate = creDate;
	}

}
