package pl.spring.demo.service;



import java.util.List;

import pl.spring.demo.model.company.CompanyTo;
import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;

public interface CompanyService {

    List<CompanyTo> findAllCompanies();
    CompanyTo findCompanyByName(String name);
    CompanyTo saveCompany(CompanyTo c);
	List<CompanyTo> findAll();

}
