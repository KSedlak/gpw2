package pl.spring.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;




public interface StockDailyRecordRepository extends JpaRepository<StockDailyRecordEntity, Long>, StockDailyRecordAdditionalQueries{



}