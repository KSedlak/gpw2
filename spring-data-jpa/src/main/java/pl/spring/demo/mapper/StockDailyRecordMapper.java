package pl.spring.demo.mapper;



import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
	import pl.spring.demo.entity.StockDailyRecordEntity;
	import pl.spring.demo.to.StockDailyRecordTo;

	public class StockDailyRecordMapper {

		public static StockDailyRecordTo map(StockDailyRecordEntity stockDailyRecordEntity) {
			if (stockDailyRecordEntity != null) {
				return new StockDailyRecordTo(
						stockDailyRecordEntity.getRecordId(),
					CompanyMapper.map(stockDailyRecordEntity.getCompany()),
					convertDateToLocal(stockDailyRecordEntity.getDate()),
					stockDailyRecordEntity.getValue()
						
						
						);
			}
			return null;
		}

		public static StockDailyRecordEntity map(StockDailyRecordTo stockDailyRecordTo) {
			if (stockDailyRecordTo != null) {
				return new StockDailyRecordEntity(stockDailyRecordTo.getRecordId(),
						CompanyMapper.map(stockDailyRecordTo.getCompany()),
						convertLocalToDate(stockDailyRecordTo.getDate()),
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
		public static LocalDate convertDateToLocal(Date d){

			Instant instant = convertFromSQLDateToJAVADate(d).toInstant();
			ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
			LocalDate date = zdt.toLocalDate();		
			return date;
			
			
		}
	public static java.util.Date convertFromSQLDateToJAVADate(Date d) {
        java.util.Date javaDate = null;
        if (d != null) {
            javaDate = new Date(d.getTime());
        } 
        return javaDate;
    } 
		public static Date convertLocalToDate(LocalDate d){
			return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}
	
	
