package pl.spring.demo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pl.spring.demo.mapper.StockDailyRecordMapper;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.repository.StockDailyRecordRepository;
import pl.spring.demo.service.StockDailyRecordService;

@Service
@Transactional()
public class StockDailyRecordServiceImpl implements StockDailyRecordService{

	@Autowired
	StockDailyRecordRepository  stockRepo;
	
	@Override
	public StockDailyRecordTo saveStockDailyRecord(StockDailyRecordTo c) {

	return StockDailyRecordMapper.map(stockRepo.save(StockDailyRecordMapper.map(c)));
	}

	@Override
	public List<StockDailyRecordTo> findAll() {
		
		return StockDailyRecordMapper.map2ToList(stockRepo.findAll());
	}


 
}
