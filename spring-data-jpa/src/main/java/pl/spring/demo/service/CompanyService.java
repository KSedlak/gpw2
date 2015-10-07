package pl.spring.demo.service;



import java.util.List;

import pl.spring.demo.to.CompanyTo;
import pl.spring.demo.to.StockDailyRecordTo;

public interface CompanyService {

    List<CompanyTo> findAllCompanies();
    CompanyTo findCompanyByName(String name);
    CompanyTo saveCompany(CompanyTo c);
	List<CompanyTo> findAll();

}
