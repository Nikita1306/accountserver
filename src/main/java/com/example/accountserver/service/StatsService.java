package com.example.accountserver.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

@Service
@Log4j2
public class StatsService {

	MetricRegistry mr = new MetricRegistry();
	Meter meterGet = mr.meter("requestsGET");
	Meter meterPut = mr.meter("requestsPUT");
	private ConcurrentHashMap<String, Double> statsMap;

	private String TOTAL_COUNT_GET = "TOTAL_GET";
	private String TOTAL_COUNT_PUT = "TOTAL_PUT";
	
	
	public StatsService() {
		statsMap = new ConcurrentHashMap<>();
		statsMap.put(TOTAL_COUNT_GET, 0.0);
		statsMap.put(TOTAL_COUNT_PUT, 0.0);
		meterGet.mark();
		meterPut.mark();
	}

	public void resetStats() {
		statsMap.put(TOTAL_COUNT_GET, 0.0);
		statsMap.put(TOTAL_COUNT_PUT, 0.0);
	}

	public double numberOfRequests(String method) {
		log.info(meterGet.getOneMinuteRate());
		log.info(meterPut.getOneMinuteRate());
		return method.equals("GET") ? statsMap.get(TOTAL_COUNT_GET) : statsMap.get(TOTAL_COUNT_PUT);
	}
	public double rateOfRequests(String method) {
		return method.equals("GET") ? meterGet.getOneMinuteRate() * 60 : meterPut.getOneMinuteRate() * 60;
	}
	public void storeRequest(String method) {
		if (method.equals("GET")) {
			meterGet.mark();
			log.info("TOTAL_COUNT_GET BEFORE + " + statsMap.get(TOTAL_COUNT_GET));
			Double totalStats = statsMap.get(TOTAL_COUNT_GET);
			statsMap.put(TOTAL_COUNT_GET, totalStats + 1);
			log.info("TOTAL_COUNT_GET AFTER + " + statsMap.get(TOTAL_COUNT_GET));
		} else {
			meterPut.mark();
			log.info("TOTAL_COUNT_PUT BEFORE + " + statsMap.get(TOTAL_COUNT_PUT));
			Double totalStats = statsMap.get(TOTAL_COUNT_PUT);
			statsMap.put(TOTAL_COUNT_PUT, totalStats + 1);
			log.info("TOTAL_COUNT_PUT AFTER + " + statsMap.get(TOTAL_COUNT_PUT));
		}
	}

}