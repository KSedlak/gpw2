package pl.spring.demo.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



public class StockTo implements Serializable {

	private Long stockId;
	private CompanyTo company;	
	private Set<StockDailyRecordTo> stockDailyRecords = new HashSet<StockDailyRecordTo>(
			0);
	

	public StockTo() {
	}


	public StockTo(Long stockId, CompanyTo company, Set<StockDailyRecordTo> stockDailyRecords) {
		super();
		this.stockId = stockId;
		this.company = company;
		this.stockDailyRecords = stockDailyRecords;
	}


	public StockTo( CompanyTo company) {
		super();
		this.company = company;

	}

	public Long getStockId() {
		return stockId;
	}


	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}


	public CompanyTo getCompany() {
		return company;
	}


	public void setCompany(CompanyTo company) {
		this.company = company;
	}


	public Set<StockDailyRecordTo> getStockDailyRecords() {
		return stockDailyRecords;
	}


	public void setStockDailyRecords(Set<StockDailyRecordTo> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
	}

	


}