package com.taxCalculate.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taxCalculate.services.LoadDataBaseService;

@Service
public class TaxRateValue {

	private BigDecimal fromAmount;
	private BigDecimal toAmount;
	private BigDecimal taxExemptionAddValue;
	private BigDecimal taxExemptionReductionValue;
	private BigDecimal taxExemptionRate;
	private BigDecimal taxIsApplicable;
	
	public TaxRateValue() {	
	}

	public TaxRateValue(BigDecimal fromAmount, BigDecimal toAmount, BigDecimal taxExemptionAddValue, BigDecimal taxExemptionReductionValue,
			BigDecimal taxExemptionRate, BigDecimal taxIsApplicable) {
		super();
		this.fromAmount = fromAmount;
		this.toAmount = toAmount;
		this.taxExemptionAddValue = taxExemptionAddValue;
		this.taxExemptionReductionValue = taxExemptionReductionValue;
		this.taxExemptionRate = taxExemptionRate;
		this.taxIsApplicable = taxIsApplicable;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public BigDecimal getTaxExemptionAddValue() {
		return taxExemptionAddValue;
	}
	
	public void setTaxExemptionAddValue(BigDecimal taxExemptionAddValue) {
		this.taxExemptionAddValue = taxExemptionAddValue;
	}

	public BigDecimal getTaxExemptionReductionValue() {
		return taxExemptionReductionValue;
	}
	
	public void setTaxExemptionReductionValue(BigDecimal taxExemptionReductionValue) {
		this.taxExemptionReductionValue = taxExemptionReductionValue;
	}

	public BigDecimal getTaxExemptionRate() {
		return taxExemptionRate;
	}

	public void setTaxExemptionRate(BigDecimal taxExemptionRate) {
		this.taxExemptionRate = taxExemptionRate;
	}
	
	public BigDecimal getTaxIsApplicable() {
		return taxIsApplicable;
	}

	public void setTaxIsApplicable(BigDecimal taxIsApplicable) {
		this.taxIsApplicable = taxIsApplicable;
	}
	
	public BigDecimal calculateTax(BigDecimal annualIncome) {
		
		List<TaxRateValue> taxValues = new ArrayList<TaxRateValue>();
		BigDecimal tax = BigDecimal.ZERO;
		try {
			for(TaxRateValue taxRateValue: LoadDataBaseService.dbContents) {
				int result = annualIncome.compareTo(taxRateValue.getFromAmount());
				if(result == 1) {
					taxValues.add(taxRateValue);
				}
			}
			
			//Fetching the right the TaxRateValue for calculating the Income tax.
			TaxRateValue taxValue = taxValues.get(taxValues.size() - 1);
			
			if(taxValue.getTaxIsApplicable() != BigDecimal.ZERO) {
				tax = (((annualIncome.subtract(taxValue.getTaxExemptionReductionValue()))
						.multiply(taxValue.getTaxExemptionRate()))
						.add(taxValue.getTaxExemptionAddValue()))
						.divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
				//System.out.println(tax);
			}
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return tax;
	}

}
