package pl.spring.demo.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.to.StockTo;

public class StockMapper {

	public static StockTo map(StockEntity stockEntity) {
			if (stockEntity != null) {			
			return new StockTo(
					stockEntity.getStockId(),
					CompanyMapper.map(stockEntity.getCompany()),
					StockDailyRecordMapper.map2To(stockEntity.getStockDailyRecords())
					);
		}
		return null;
	}

	public static StockEntity map(StockTo stockTo) {
		if (stockTo != null) {
			return new StockEntity(
					stockTo.getStockId(),
					CompanyMapper.map(stockTo.getCompany()),
					StockDailyRecordMapper.map2Entity(stockTo.getStockDailyRecords()));
		}
		return null;
	}

	public static Set<StockTo> map2To(Set<StockEntity> stockEntities) {
		return stockEntities.stream().map(StockMapper::map).collect(Collectors.toSet());
	}

	public static Set<StockEntity> map2Entity(Set<StockTo> stockEntities) {
		return stockEntities.stream().map(StockMapper::map).collect(Collectors.toSet());
	}
}
