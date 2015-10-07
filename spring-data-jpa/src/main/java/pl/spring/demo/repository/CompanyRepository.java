package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.spring.demo.model.company.CompanyEntity;


public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("select com from CompanyEntity com where com.name like :name%")
    public CompanyEntity findCompanyByName(@Param("name") String name);

}