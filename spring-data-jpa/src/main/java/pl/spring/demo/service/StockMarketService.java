package pl.spring.demo.service;

import java.time.LocalDate;
import java.util.List;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface StockMarketService {

 List<StockDailyRecordTo> findAllRecordsBySpecificdate(LocalDate date);
 StockDailyRecordTo findStockDailyRecordsByDateAndCompanyName(LocalDate d, String name);
 List<StockDailyRecordTo> findStockDailyRecordsByCompanyNameFromDateAToB(String name, LocalDate A, LocalDate B);
 List<StockDailyRecordTo> findStockDailyRecordsFromDateAToB(LocalDate A, LocalDate B);
 List<StockDailyRecordTo> findAllByCompanyName(String name);
 List<StockDailyRecordTo> findCheapestFromDay(LocalDate d, int resultLimit);
 List<StockDailyRecordTo> findExpensiveFromDay(LocalDate d, int resultLimit, List<String> companiesToCheck);

}
