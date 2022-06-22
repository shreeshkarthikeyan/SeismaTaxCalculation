package com.taxCalculate.services;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxCalculate.model.TaxRateValue;

@Service
public class LoadDataBaseService {
	
	public static List<TaxRateValue> dbContents;
	
	public LoadDataBaseService() {
		dbContents = new ArrayList<TaxRateValue>();
	}
	
	@Autowired
	DataSource dataSource;

	public void fetchAllDBContents() {
			
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement pStmnt = null;
		
		try {
			connection = dataSource.getConnection();
			String preQueryStatement = "SELECT * FROM TAX ORDER BY FROM_AMOUNT ASC";    
		    pStmnt = connection.prepareStatement(preQueryStatement);
		    resultSet = pStmnt.executeQuery();
            
			while(resultSet.next()) {
				TaxRateValue taxValue = new TaxRateValue();
				
				taxValue.setFromAmount(BigDecimal.valueOf(resultSet.getInt("FROM_AMOUNT")));
				taxValue.setToAmount(BigDecimal.valueOf(resultSet.getInt("TO_AMOUNT")));
				taxValue.setTaxExemptionAddValue(BigDecimal.valueOf(resultSet.getInt("TAX_EXEMPTION_ADD_VALUE")));
				taxValue.setTaxExemptionReductionValue(BigDecimal.valueOf(resultSet.getInt("TAX_EXEMTION_REDUCTION_VALUE")));
				taxValue.setTaxExemptionRate(BigDecimal.valueOf(resultSet.getFloat("TAX_EXEMTION_RATE")));
				taxValue.setTaxIsApplicable(BigDecimal.valueOf(resultSet.getInt("IS_APPLICABLE")));
				
				dbContents.add(taxValue);
			}
			resultSet.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
