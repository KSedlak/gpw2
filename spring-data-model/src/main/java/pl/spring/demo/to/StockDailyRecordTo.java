package pl.spring.demo.to;


import java.io.Serializable;
import java.time.LocalDate;



public class StockDailyRecordTo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2022442777720923904L;
	private Long recordId;
	private CompanyTo company;
	private LocalDate date;
	private Double value;
	
	public  StockDailyRecordTo() {		
	}


	public StockDailyRecordTo(Long recordId, CompanyTo comp, LocalDate date, Double value) {
		super();
		this.recordId = recordId;
		this.company = comp;
		this.date = date;
		this.setValue(value);
	}

	public StockDailyRecordTo(Long recordId, LocalDate date, Double value) {
		super();
		this.recordId = recordId;
		this.date = date;
		this.setValue(value);
	}
	public StockDailyRecordTo(CompanyTo comp, LocalDate date, Double value) {
		super();
		this.company = comp;
		this.date = date;
		this.setValue(value);
	}


	public Long getRecordId() {
		return recordId;
	}


	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}



	public CompanyTo getCompany() {
		return company;
	}


	public void setCompany(CompanyTo company) {
		this.company = company;
	}


	public LocalDate  getDate() {
		return date;
	}


	public void setDate(LocalDate  date) {
		this.date = date;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}

}