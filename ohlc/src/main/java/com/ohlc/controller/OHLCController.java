package com.ohlc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ohlc.bean.Clients;

@Controller
public class OHLCController {

	@GetMapping("/register")
	public ResponseEntity<?> getAllCitiesByStateId(@RequestBody Clients client) {
		return ResponseEntity.ok("");
	}
}
