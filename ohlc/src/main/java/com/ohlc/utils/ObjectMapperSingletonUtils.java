package com.ohlc.utils;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * This class is to provide singleton object of ObjectMapper. Since it is very
 * heavy to load ObjectMapper. So, this Utility will provide already
 * instantiated ObjectMapper object.
 * 
 * @author Dnyaneshwar
 *
 */
public class ObjectMapperSingletonUtils implements Serializable {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The instance of object mapper
	 */
	private static ObjectMapper instance = null;

	/**
	 * The Private Constructor
	 */
	private ObjectMapperSingletonUtils() {
		// no cons
	}

	/**
	 * It is return singleton object for object mapper
	 * 
	 * @return {@link ObjectMapper}
	 */
	public static ObjectMapper getMapper() {
		if (Objects.isNull(instance)) {
			synchronized (ObjectMapperSingletonUtils.class) {
				if (Objects.isNull(instance)) {
					instance = new ObjectMapper();
				}
			}
		}
		instance.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return instance;
	}

	/**
	 * Read Resolve
	 * 
	 * @return instance Object
	 */
	protected Object readResolve() {
		return instance;
	}
}
