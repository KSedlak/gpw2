package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.mapper.CompanyMapper;
import pl.spring.demo.model.company.CompanyTo;
import pl.spring.demo.repository.CompanyRepository;
import pl.spring.demo.service.CompanyService;

import java.util.List;

@Service
@Transactional()
public class CompanyServiceImpl implements CompanyService {

	
    @Autowired
    private CompanyRepository companyRepository;

	@Override
	public List<CompanyTo> findAllCompanies() {
		return CompanyMapper.map2To(companyRepository.findAll());
	}

	@Override
	public CompanyTo findCompanyByName(String name) {
		return CompanyMapper.map(companyRepository.findCompanyByName(name));
		}

	@Override
	public CompanyTo saveCompany(CompanyTo c) {
		return CompanyMapper.map(companyRepository.save(CompanyMapper.map(c)));
	}

	@Override
	public List<CompanyTo> findAll() {
		return CompanyMapper.map2To(companyRepository.findAll());
	}

}

