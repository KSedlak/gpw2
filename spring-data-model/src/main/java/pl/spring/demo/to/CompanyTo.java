package pl.spring.demo.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import pl.spring.demo.entity.StockDailyRecordEntity;


public class CompanyTo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Long id;
    private String name;
    private Set<StockDailyRecordEntity> stockDailyRecords = new HashSet<StockDailyRecordEntity>(0);

    public CompanyTo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    

	public CompanyTo(Long id, String name, Set<StockDailyRecordEntity> stockDailyRecords) {
		super();
		this.id = id;
		this.name = name;
		this.stockDailyRecords = stockDailyRecords;
	}
	
	public CompanyTo( String name, Set<StockDailyRecordEntity> stockDailyRecords) {
		super();
		this.name = name;
		this.stockDailyRecords = stockDailyRecords;
	}
	public CompanyTo( String name) {
		super();
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<StockDailyRecordEntity> getStockDailyRecords() {
		return stockDailyRecords;
	}
	public void setStockDailyRecords(Set<StockDailyRecordEntity> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
	}
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }

	    final CompanyTo other = (CompanyTo) obj;
	    if(this.name.equals(null)|| other.name.equals(null) || !this.name.equals(other.name)){
	    	return false;}

	 
	    return true;
	}
	@Override
	public int hashCode() {
	        return this.getName().hashCode();
	    }


}
