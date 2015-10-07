package pl.spring.demo.mapper;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

	public class StockDailyRecordMapper {

		public static StockDailyRecordTo map(StockDailyRecordEntity stockDailyRecordEntity) {
			if (stockDailyRecordEntity != null) {
				return new StockDailyRecordTo(
						stockDailyRecordEntity.getRecordId(),
					CompanyMapper.map(stockDailyRecordEntity.getCompany()),
					DateMapper.map(stockDailyRecordEntity.getDate()),
					stockDailyRecordEntity.getValue()

						);
			}
			return null;
		}

		public static StockDailyRecordEntity map(StockDailyRecordTo stockDailyRecordTo) {
			if (stockDailyRecordTo != null) {
				return new StockDailyRecordEntity(stockDailyRecordTo.getRecordId(),
						CompanyMapper.map(stockDailyRecordTo.getCompany()),
						DateMapper.map(stockDailyRecordTo.getDate()),
						stockDailyRecordTo.getValue()
						);
			}
			return null;
		}

		public static Set<StockDailyRecordTo> map2To(Set<StockDailyRecordEntity> stockDailyRecordEntities) {
			return stockDailyRecordEntities.stream().map(StockDailyRecordMapper::map).collect(Collectors.toSet());
		}

		public static Set<StockDailyRecordEntity> map2Entity(Set<StockDailyRecordTo> stockDailyRecordEntities) {
			return stockDailyRecordEntities.stream().map(StockDailyRecordMapper::map).collect(Collectors.toSet());
		}
		public static List<StockDailyRecordTo> map2ToList(List<StockDailyRecordEntity> stockDailyRecordEntities) {
			return stockDailyRecordEntities.stream().map(StockDailyRecordMapper::map).collect(Collectors.toList());
		}

		public static List<StockDailyRecordEntity> mapList2Entity(List<StockDailyRecordTo> stockDailyRecordEntities) {
			return stockDailyRecordEntities.stream().map(StockDailyRecordMapper::map).collect(Collectors.toList());
		}

	}


