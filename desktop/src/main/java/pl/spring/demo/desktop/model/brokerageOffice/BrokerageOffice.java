package pl.spring.demo.desktop.model.brokerageOffice;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import pl.spring.demo.desktop.model.GPW.event.StockRatesChanged;
import pl.spring.demo.desktop.model.Transaction.Transaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface BrokerageOffice extends ApplicationListener<StockRatesChanged>, ApplicationContextAware {

List<StockDailyRecordTo> getTodayStockValues();
List<StockDailyRecordTo> getStocksByCompanyName(String name);
List<StockDailyRecordTo> getStocksByCompanyNameAndDate(String name, LocalDate d);
List<StockDailyRecordTo> getStocksByCompanyNameFromDateToDate(String name, LocalDate A, LocalDate B);
List<StockDailyRecordTo> getStocksByCompanyNameFromDateXDaysBefore(String name, LocalDate A, Integer X);
List<StockDailyRecordTo> getStocksByCompanyNameFromTodayXDaysBefore(String name, Integer X);
List<StockDailyRecordTo> getStocksFromTodayXDaysBefore(Integer X);

Transaction  makeTransaction(Transaction t);
}

