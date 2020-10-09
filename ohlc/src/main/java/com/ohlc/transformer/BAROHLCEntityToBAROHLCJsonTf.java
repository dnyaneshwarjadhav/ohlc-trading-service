package com.ohlc.transformer;

import org.apache.commons.collections4.Transformer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ohlc.bean.BAROHLCJson;
import com.ohlc.model.BAROHLCEntity;


/**
 *
 * @author Dnyaneshwar Jadhav
 */
@Component
public class BAROHLCEntityToBAROHLCJsonTf implements Transformer<BAROHLCEntity, BAROHLCJson> {
	
	/**
	 */
	@Override
	public BAROHLCJson transform(BAROHLCEntity entity) {
		BAROHLCJson json = new BAROHLCJson();
		BeanUtils.copyProperties(entity, json);
		return json;
	}
}
