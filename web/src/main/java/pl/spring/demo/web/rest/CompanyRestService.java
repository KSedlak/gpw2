package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.spring.demo.service.CompanyService;
import pl.spring.demo.to.CompanyTo;
import java.util.List;




@Controller
@RequestMapping(value = "/Company")
public class CompanyRestService {



	@Autowired
	private CompanyService service;
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<CompanyTo> findAll() {
		System.out.println(service.findAll().size());
		return service.findAll();
	}


}
