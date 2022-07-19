package com.taxCalculate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taxCalculate.model.Employee;
import com.taxCalculate.model.TaxCalculateModel;
import com.taxCalculate.services.LoadDataBaseService;
import com.taxCalculate.services.TaxCalculateService;


@Controller
public class TaxCalculateController {

	@Autowired
	Employee inputDetails;
	
	@Autowired
	TaxCalculateService service;
	
	@Autowired
	LoadDataBaseService loadDataBase;

	@RequestMapping(value = "/taxCalculate" , method = RequestMethod.GET)
	public String showTaxCalculate() {
		
		if(LoadDataBaseService.dbContents.size() == 0) {
			loadDataBase.fetchAllDBContents();
		}
		return "taxCalculate";
		
	}
	
	@RequestMapping(value = "/taxCalculate" , method = RequestMethod.POST)
	public String showTaxCalculateWithResponse(@RequestParam String firstName,@RequestParam String lastName, 
			@RequestParam int month, @RequestParam double annualIncome, @RequestParam int superAnnuation,
			ModelMap model) {
		
		inputDetails = new Employee(firstName, lastName, annualIncome, superAnnuation, month);

		TaxCalculateModel taxModel = service.calculator(inputDetails);
		if(taxModel == null) {
			model.put("errorMessage","Error occured");
		}
		model.put("taxDetails", taxModel);		
		return "taxCalculate";
	}
}
