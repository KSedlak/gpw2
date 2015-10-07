package pl.spring.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.spring.demo.model.company.CompanyEntity;
import pl.spring.demo.model.company.CompanyTo;

public class CompanyMapper {

	public static CompanyTo map(CompanyEntity companyEntity) {
		if (companyEntity != null) {
			return new CompanyTo(companyEntity.getId(),companyEntity.getName());
		}
		return null;
	}

	public static CompanyEntity map(CompanyTo companyTo) {
		if (companyTo != null) {
			return new CompanyEntity(companyTo.getId(),companyTo.getName());
		}
		return null;
	}

	public static List<CompanyTo> map2To(List<CompanyEntity> companyEntities) {
		return companyEntities.stream().map(CompanyMapper::map).collect(Collectors.toList());
	}

	public static List<CompanyEntity> map2Entity(List<CompanyTo> companyEntities) {
		return companyEntities.stream().map(CompanyMapper::map).collect(Collectors.toList());
	}
}
