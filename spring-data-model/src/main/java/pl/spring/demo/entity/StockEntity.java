package pl.spring.demo.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "stock")
public class StockEntity implements Serializable {

	private Long stockId;
	@OneToOne(cascade=CascadeType.ALL, mappedBy="stock")  
	private CompanyEntity company;	
	private Set<StockDailyRecordEntity> stockDailyRecords = new HashSet<StockDailyRecordEntity>(
			0);
	

	public StockEntity() {
	}

	
	public StockEntity(Long stockId, CompanyEntity company, Set<StockDailyRecordEntity> stockDailyRecords) {
		super();
		this.stockId = stockId;
		this.company = company;
		this.stockDailyRecords = stockDailyRecords;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Long getStockId() {
		return this.stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
	public Set<StockDailyRecordEntity> getStockDailyRecords() {
		return this.stockDailyRecords;
	}

	public void setStockDailyRecords(Set<StockDailyRecordEntity> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
	}
}