package pl.spring.demo.to;




import java.io.Serializable;


public class CompanyTo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
  
    private StockTo companyStock;
    



	public CompanyTo(Long id, String name, StockTo companyStock) {
		super();
		this.id = id;
		this.name = name;
		this.companyStock = companyStock;
	}

	public StockTo getCompanyStock() {
		return companyStock;
	}

	public void setCompanyStock(StockTo companyStock) {
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
