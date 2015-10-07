package pl.spring.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordTo;
import pl.spring.demo.service.StockDailyRecordService;
@Controller
@RequestMapping(value = "/StockDaily")
public class StockDailyRecordsRestService {

		@Autowired
		private StockDailyRecordService service;
		
		@ResponseBody
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public List<StockDailyRecordTo> findAll() {
			return service.findAll();
		}



	}
