package pl.spring.demo.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "company")
public class CompanyEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMPANY_ID")
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<StockDailyRecordEntity> stockDailyRecords = new HashSet<StockDailyRecordEntity>(0);
    
    protected CompanyEntity() {
}


	public CompanyEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public CompanyEntity(Long id, String name, Set<StockDailyRecordEntity> stockDailyRecords) {
		super();
		this.id = id;
		this.name = name;
		this.stockDailyRecords = stockDailyRecords;
	}


	public CompanyEntity(String name) {
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
		return this.stockDailyRecords;
	}

	public void setStockDailyRecords(Set<StockDailyRecordEntity> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
	}


	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }

	    final CompanyEntity other = (CompanyEntity) obj;
	    if(this.name.equals(null)|| other.name.equals(null) || !this.name.equals(other.name)){
	    	return false;}


	    return true;
	}
	@Override
	public int hashCode() {
	        return this.getName().hashCode();
	    }
}
