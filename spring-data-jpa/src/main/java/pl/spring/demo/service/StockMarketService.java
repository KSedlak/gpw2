package pl.spring.demo.service;

import java.time.LocalDate;
import java.util.List;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface StockMarketService {

 List<StockDailyRecordTo> findAllRecordsBySpecificdate(LocalDate date);
 List<StockDailyRecordTo> findStockDailyRecordsByDateAndCompanyName(LocalDate d, String name);
 List<StockDailyRecordTo> findStockDailyRecordsByCompanyNameFromDateAToB(String name, LocalDate A, LocalDate B);
 List<StockDailyRecordTo> findStockDailyRecordsFromDateAToB(LocalDate A, LocalDate B);
 List<StockDailyRecordTo> findAllByCompanyName(String name);
}
