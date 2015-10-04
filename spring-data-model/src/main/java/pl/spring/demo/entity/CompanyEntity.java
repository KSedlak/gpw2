package pl.spring.demo.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Company")
public class CompanyEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(name="company_stock")
    private StockEntity companyStock;
    
    protected CompanyEntity() {
}
    




	public CompanyEntity(Long id, String name, StockEntity companyStock) {
		super();
		this.id = id;
		this.name = name;
		this.companyStock = companyStock;
	}




	 @OneToOne(mappedBy="company", cascade=CascadeType.ALL)
	public StockEntity getCompanyStock() {
		return companyStock;
	}





	public void setCompanyStock(StockEntity companyStock) {
		this.companyStock = companyStock;
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

 
}
