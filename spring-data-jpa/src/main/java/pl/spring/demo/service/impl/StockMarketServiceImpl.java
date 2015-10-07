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
public class StockMarketServiceImpl implements StockMarketService{

	@Autowired
	StockDailyRecordRepository  stockDailyRecordDao;
	
	@Override
	public List<StockDailyRecordTo> getAllRecordsBySpecificdate(LocalDate date) {
		return StockDailyRecordMapper.map2ToList(stockDailyRecordDao.findStockDailyRecordsByDate(DateMapper.map(date)));
	}



 
}
