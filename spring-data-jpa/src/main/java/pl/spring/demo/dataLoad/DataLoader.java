package pl.spring.demo.dataLoad;




import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import pl.spring.demo.dataLoad.fileReader.CsvReader;
import pl.spring.demo.model.company.CompanyTo;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.service.CompanyService;
import pl.spring.demo.service.StockDailyRecordService;

@Component
@DependsOn("entityManagerFactory")
@PersistenceContext
public class DataLoader  {
	
	@Autowired
	private StockDailyRecordService stockDailyRecordService;

	@Autowired
	private CompanyService comp;
	
    @Autowired
	private CsvReader reader;
    

    private static final String SEPARATOR = ",";
    
	private Set<CompanyTo> companies;//keep unique objects
	private List<StockDailyRecordTo> dailyRecords;
	
	public DataLoader() {
		super();
		this.companies= new HashSet<CompanyTo>();
		this.dailyRecords=new ArrayList<StockDailyRecordTo>();
	}
	public DataLoader(CsvReader reader) {

		this.reader = reader;
		this.companies= new HashSet<CompanyTo>();
		this.dailyRecords=new ArrayList<StockDailyRecordTo>();
	}

	public CsvReader getReader() {
		return reader;
	}

	public void setReader(CsvReader reader) {
		this.reader = reader;
	}

	@PostConstruct
	public void loadData(){

		List<String> records=reader.readRecords();
		records.stream().forEach(record->{
		String[] entry =record.split(SEPARATOR);

		CompanyTo companyStock=new CompanyTo(entry[0]);
		companyStock=keepSameObjects(companyStock);
		LocalDate date=getDateFromString(entry[1]);
		Double value=getDoubleFromString(entry[2]);
		

		StockDailyRecordTo dailyRecord=new StockDailyRecordTo(companyStock, date, value);
		dailyRecords.add(dailyRecord);
	
	});
		companies.stream().forEach(c->comp.saveCompany(c));
		dailyRecords.stream().forEach(stock->{
		CompanyTo c =comp.findCompanyByName(stock.getCompany().getName());//get already saved entity
		stock.setCompany(c);
		stockDailyRecordService.saveStockDailyRecord(stock);
		
		});
		stockDailyRecordService.findAll().stream().forEach(x->{
			System.out.println("added: "+x.getCompany().getName()+" "+x.getDate()+" "+x.getValue());}
		);	
			

		
		
		


	}

	private CompanyTo keepSameObjects(CompanyTo comp){//should prevent to create same companies
		companies.add(comp);
		return companies.stream().filter(c->c.equals(comp)).findFirst().get();
	}

	public static LocalDate getDateFromString(String s){
			try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			   LocalDate date = LocalDate.parse(s, formatter);
				return date;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;


	}
	
	public static Double getDoubleFromString(String s){
			try {
				return Double.parseDouble(s);
			} catch (Exception e) {

				e.printStackTrace();
			}
		return null;
}
	
}	