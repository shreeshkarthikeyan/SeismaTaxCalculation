package com.taxCalculate.restApi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taxCalculate.model.Employee;
import com.taxCalculate.model.TaxCalculateModel;
import com.taxCalculate.services.TaxCalculateService;

@RestController
public class TaxRestController {
	
	@Autowired
	TaxCalculateService service;
	
	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public List<TaxCalculateModel> retrieveTaxDetails(@RequestBody List<Employee> employees) {
		
		List<TaxCalculateModel> result = new ArrayList<TaxCalculateModel>();		
		for(Employee employee : employees) {
			TaxCalculateModel taxDetails = service.calculator(employee);
			result.add(taxDetails);
		}
		return result;
	}
}
