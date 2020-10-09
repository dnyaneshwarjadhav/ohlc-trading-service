package com.ohlc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ohlc.service.TailFileReader;

@SpringBootApplication
public class SpringBootH2DatabaseExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2DatabaseExampleApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init (TailFileReader reader){
        return args -> {
        	reader.startAllWorker();
        	reader.read("C:/Users/Dnyaneshwar/Desktop/trades/trades.json");
        };
    }
}
