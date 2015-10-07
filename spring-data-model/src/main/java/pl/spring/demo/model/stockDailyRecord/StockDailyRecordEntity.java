package pl.spring.demo.model.stockDailyRecord;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pl.spring.demo.model.company.CompanyEntity;


@Entity
@Table(name = "stock_daily_record")
public class StockDailyRecordEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2701249247584509851L;
	private Long recordId;

	private CompanyEntity company;
	private Date day;
	private Double valueOfStock;

	public StockDailyRecordEntity() {
	}

	public StockDailyRecordEntity(Long recordId, CompanyEntity comp, Date date, Double value) {
		super();
		this.recordId = recordId;
		this.company = comp;
		this.day = date;
		this.valueOfStock = value;
	}
	public StockDailyRecordEntity(Long recordId, Date date, Double value) {
		super();
		this.recordId = recordId;
		this.day = date;
		this.valueOfStock = value;
	}


	public Double getValue() {
		return valueOfStock;
	}

	public void setValue(Double value) {
		this.valueOfStock = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RECORD_ID", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@ManyToOne()
	   @JoinColumn(name = "COMPANY_ID")
	public CompanyEntity getCompany() {
		return this.company;
	}

	public void setCompany(CompanyEntity comp) {
		this.company = comp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", unique = false, nullable = false, length = 10)
	public Date getDate() {
		return this.day;
	}

	public void setDate(Date date) {
		this.day = date;
	}
}