package pl.spring.demo.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.demo.mapper.DateMapper;
import pl.spring.demo.mapper.StockDailyRecordMapper;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.repository.StockDailyRecordRepository;
import pl.spring.demo.service.StockMarketService;

@Service
@Transactional()
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
	StockDailyRecordRepository  stockDailyRecordDao;

	@Override
	public List<StockDailyRecordTo> findAllRecordsBySpecificdate(LocalDate date) {
		return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsByDate(DateMapper.map(date)));
	}

	@Override
	public StockDailyRecordTo findStockDailyRecordsByDateAndCompanyName(LocalDate d, String name) {
		return StockDailyRecordMapper.map(stockDailyRecordDao.findStockDailyRecordsByDateAndCompanyName(DateMapper.map(d), name));
	}

	@Override
	public List<StockDailyRecordTo> findStockDailyRecordsByCompanyNameFromDateAToB(String name, LocalDate A, LocalDate B) {
		return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsByCompanyNameFromDateAToB(name, DateMapper.map(A),  DateMapper.map(B)));
	}

	@Override
	public List<StockDailyRecordTo> findAllByCompanyName(String name) {
	return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsByCompanyName(name));
	}

	@Override
	public List<StockDailyRecordTo> findStockDailyRecordsFromDateAToB(LocalDate A, LocalDate B) {
	return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsFromDateAToB(DateMapper.map(A), DateMapper.map(B)));
	}

	@Override
	public List<StockDailyRecordTo> findCheapestFromDay(LocalDate d, int resultLimit) {
		return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsCheapestByDate(DateMapper.map(d), resultLimit));
	}

	@Override
	public List<StockDailyRecordTo> findExpensiveFromDay(LocalDate d, int resultLimit, List<String> companiesToCheck) {
		return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsExpensiveByDate(DateMapper.map(d), resultLimit, companiesToCheck));
	}





}
