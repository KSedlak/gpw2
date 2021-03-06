package pl.spring.demo.desktop.model.brokerageOffice;

import java.time.LocalDate;
import java.util.List;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import pl.spring.demo.desktop.model.transaction.marketTransaction.MarketTransaction;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface BrokerageOffice extends ApplicationListener<ApplicationEvent>, ApplicationContextAware {

	List<StockDailyRecordTo> getTodayStockValues();

	List<StockDailyRecordTo> getStocksByCompanyName(String name);

	StockDailyRecordTo getStocksByCompanyNameAndDate(String name, LocalDate d);

	StockDailyRecordTo getStocksByCompanyNameFromToday(String name);

	List<StockDailyRecordTo> getStocksByCompanyNameFromDateToDate(String name, LocalDate A, LocalDate B);

	List<StockDailyRecordTo> getStocksByCompanyNameFromDateXDaysBefore(String name, LocalDate A, Integer X);

	List<StockDailyRecordTo> getStocksByCompanyNameFromTodayXDaysBefore(String name, Integer X);

	List<StockDailyRecordTo> getStocksFromTodayXDaysBefore(Integer X);

	List<StockDailyRecordTo> getCheapesStocksFromToday(Integer X);

	MarketTransaction makeTransaction(MarketTransaction t);
}
