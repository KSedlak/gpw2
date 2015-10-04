package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.mapper.CompanyMapper;
import pl.spring.demo.repository.CompanyRepository;
import pl.spring.demo.service.CompanyService;
import pl.spring.demo.to.CompanyTo;

import java.util.List;

@Service
@Transactional(readOnly = true)
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

}

