package pl.spring.demo.entity;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "company")
public class CompanyEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPANY_ID")
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    
    protected CompanyEntity() {
}
    




	public CompanyEntity(Long id, String name) {
		super();
		this.id = id;
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

 
}
