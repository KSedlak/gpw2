package pl.spring.demo.model.stockDailyRecord;


import java.io.Serializable;
import java.time.LocalDate;
import pl.spring.demo.model.company.CompanyTo;



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

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final StockDailyRecordTo other = (StockDailyRecordTo) obj;
	    if ((this.company == null) ? (other.company != null) : !this.company.getName().equals(other.company.getName())) {
	        return false;
	    }

	    return true;
	}
	@Override
	public int hashCode() {
	        return this.getCompany().getName().hashCode();
	    }
}