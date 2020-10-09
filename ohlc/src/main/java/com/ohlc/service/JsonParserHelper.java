package com.ohlc.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohlc.utils.ObjectMapperSingletonUtils;

public class JsonParserHelper {
	/**
	 * ObjectMapper
	 */
	private static ObjectMapper mapper = ObjectMapperSingletonUtils.getMapper();

	/**
	 * Convert JSON to object.
	 * 
	 * @param <T>      the generic type
	 * @param jsonData the json data
	 * @param obj      the obj
	 * @return the Type of obj
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertJSONToObject(String jsonData, Class<T> obj) {
		try {
			return mapper.readValue(jsonData, obj);
		} catch (Exception e) {
			//System.out.println("JsonParseException in convertJSONToObject : "+e.getMessage());//, e, e);
		}
		return null;
	}
	
	/**
	 * Convert JSON to object.
	 *
	 * @param obj the obj
	 * @return String
	 */
	public static String convertObjectToJSON(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			System.out.println("Exception in Converting Object to Json : "+e.getMessage());//, e, e);
			return null;
		}
	}
}
