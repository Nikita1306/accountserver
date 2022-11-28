package com.example.accountserver.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

@Service
@Log4j2
public class StatsService {

	MetricRegistry mr = new MetricRegistry();
	Meter meter = mr.meter("requests");

	private ConcurrentHashMap<String, Double> statsMap;

	
	Double pastHourResponseTime = 0.0;
	
	public void setPastHourResponseTime(Double pastHourResponseTime) {
		this.pastHourResponseTime = pastHourResponseTime;
	}

	private String TOTAL_COUNT = "TOTAL";
	private String PAST_MINUTE_AVERAGE_RESPONSE_TIME = "PAST_MINUTE_AVERAGE_RESPONSE_TIME";
	
	
	public StatsService() {
		statsMap = new ConcurrentHashMap<>();
		statsMap.put(TOTAL_COUNT, 0.0);
		meter.mark();
	}

	public double numberOfRequests() {
		log.info("Meter count: " + meter.getFifteenMinuteRate());

		log.info("Meter count: " + meter.getCount());
		return statsMap.get(TOTAL_COUNT);
	}
	
	public void storeRequest(String method) {
		log.info("TOTAL_COUNT BEFORE + " + statsMap.get(TOTAL_COUNT));
		Double totalStats = statsMap.get(TOTAL_COUNT);
		statsMap.put("AVERAGE_RESPONSE", meter.getMeanRate());
		statsMap.put(TOTAL_COUNT, totalStats + 1);
		log.info("TOTAL_COUNT AFTER + " + statsMap.get(TOTAL_COUNT));
	}

}