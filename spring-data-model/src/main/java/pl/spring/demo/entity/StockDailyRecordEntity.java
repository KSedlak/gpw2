package pl.spring.demo.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "stock_daily_record")
public class StockDailyRecordEntity implements Serializable {

	private Long recordId;
	private StockEntity stock;
	private Date date;
	private Double value;

	public StockDailyRecordEntity() {
	}

	public StockDailyRecordEntity(Long recordId, StockEntity stock, Date date, Double value) {
		super();
		this.recordId = recordId;
		this.stock = stock;
		this.date = date;
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	public StockEntity getStock() {
		return this.stock;
	}

	public void setStock(StockEntity stock) {
		this.stock = stock;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", unique = true, nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}