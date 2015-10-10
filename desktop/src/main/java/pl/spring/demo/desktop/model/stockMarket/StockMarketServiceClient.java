package pl.spring.demo.desktop.model.stockMarket;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import pl.spring.demo.desktop.model.calendar.event.DayChanged;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface StockMarketServiceClient extends ApplicationListener<DayChanged>, ApplicationContextAware {

List<StockDailyRecordTo> getTodayValues();
List<StockDailyRecordTo> getStocksByCompanyName(String name);
List<StockDailyRecordTo> getStocksByCompanyNameAndDate(String name, LocalDate d);
List<StockDailyRecordTo> getStocksByCompanyNameFromDateToDate(String name, LocalDate A, LocalDate B);
List<StockDailyRecordTo> getStockDailyRecordsFromDateAToB(LocalDate A, LocalDate B);
List<StockDailyRecordTo> getStocksByCompanyNameFromTodayXDaysBefore(String name, Integer X);
List<StockDailyRecordTo> getStocksFromTodayXDaysBefore(Integer X);
}
