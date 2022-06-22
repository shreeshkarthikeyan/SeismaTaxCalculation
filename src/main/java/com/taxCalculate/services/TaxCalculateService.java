package com.taxCalculate.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Service;

import com.taxCalculate.model.Employee;
import com.taxCalculate.model.TaxCalculateModel;
import com.taxCalculate.model.TaxRateValue;

@Service
public class TaxCalculateService {
	
	String startDay, endDay;
	
	public TaxCalculateModel calculator(Employee employee) {
		TaxCalculateModel taxModel = new TaxCalculateModel();	
		taxModel.setEmployee(employee);
		
		payPeriod(employee.getPaymentMonth());
		taxModel.setFromDate(startDay);
		
		taxModel.setToDate(endDay);
		
		taxModel.setGrossIncome(computeGrossIncome(employee.getAnnualSalary()));
		taxModel.setIncomeTax(new TaxRateValue().calculateTax(BigDecimal.valueOf(employee.getAnnualSalary())).intValue());
		taxModel.setNetIncome(taxModel.getGrossIncome() - taxModel.getIncomeTax());
		
		int superFund = Math.round(taxModel.getGrossIncome() * employee.getSuperRate()/100f);
		taxModel.setSuperannuation(superFund);
		
		return taxModel;
	}
	
	public int computeGrossIncome(double annualIncome) {
		BigDecimal grossIncome = BigDecimal.ZERO;
		grossIncome = BigDecimal.valueOf(annualIncome).divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
		return grossIncome.intValue();
	}
	
	public void payPeriod(int month){
		
		Calendar gc = new GregorianCalendar();
        gc.set(Calendar.MONTH, month);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = gc.getTime();
        gc.add(Calendar.MONTH, 1);
        gc.add(Calendar.DAY_OF_MONTH, -1);
        Date monthEnd = gc.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMMM");

        startDay = format.format(monthStart);
        endDay = format.format(monthEnd);
	}

}