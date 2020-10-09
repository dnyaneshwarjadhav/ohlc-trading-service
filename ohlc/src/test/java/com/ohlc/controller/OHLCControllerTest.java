package com.ohlc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OHLCController.class)
public class OHLCControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void retrieveDetailsForCourse() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{\"event\":\"ohlc_notify\",\"symbol\":\"XXBTZUSD\",\"bar_num\":2}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
