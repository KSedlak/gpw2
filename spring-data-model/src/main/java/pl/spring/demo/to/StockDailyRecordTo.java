package pl.spring.demo.to;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


public class StockDailyRecordTo implements Serializable {

	private Long recordId;
	private StockTo stock;
	private LocalDate date;
	private Double value;
	
	public  StockDailyRecordTo() {		
	}


	public StockDailyRecordTo(Long recordId, StockTo stock, LocalDate date, Double value) {
		super();
		this.recordId = recordId;
		this.stock = stock;
		this.date = date;
		this.setValue(value);
	}

	public StockDailyRecordTo(StockTo stock, LocalDate date, Double value) {
		super();
		this.recordId = recordId;
		this.stock = stock;
		this.date = date;
		this.setValue(value);
	}


	public Long getRecordId() {
		return recordId;
	}


	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}


	public StockTo getStock() {
		return stock;
	}


	public void setStock(StockTo stock) {
		this.stock = stock;
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