package pl.spring.demo.service;

import java.time.LocalDate;
import java.util.List;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface StockMarketService {
		
 List<StockDailyRecordTo> getAllRecordsBySpecificdate(LocalDate date);

}
