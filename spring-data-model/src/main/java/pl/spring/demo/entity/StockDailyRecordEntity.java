package pl.spring.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "stock_daily_record")
public class StockDailyRecordEntity implements Serializable {

	private Long recordId;
	private StockEntity stock;
	private Date day;
	private Double valueOfStock;

	public StockDailyRecordEntity() {
	}

	public StockDailyRecordEntity(Long recordId, StockEntity stock, Date date, Double value) {
		super();
		this.recordId = recordId;
		this.stock = stock;
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
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RECORD_ID", unique = true, nullable = false)
	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "STOCK_ID", nullable = true)
	public StockEntity getStock() {
		return this.stock;
	}

	public void setStock(StockEntity stock) {
		this.stock = stock;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", unique = true, nullable = false, length = 10)
	public Date getDate() {
		return this.day;
	}

	public void setDate(Date date) {
		this.day = date;
	}
}