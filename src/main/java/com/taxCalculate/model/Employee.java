package com.taxCalculate.model;

import org.springframework.stereotype.Service;

@Service
public class Employee {
	private String firstName;
	private String lastName;
	private double annualSalary;
	private int paymentMonth;
	private int superRate;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, double annualSalary, int superRate, int paymentMonth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.annualSalary = annualSalary;
		this.superRate = superRate;
		this.paymentMonth = paymentMonth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}
	public int getSuperRate() {
		return superRate;
	}
	public void setSuperRate(int superRate) {
		this.superRate = superRate;
	}
	public int getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(int paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", annualIncome=" + annualSalary
				+ ", superAnnuation=" + superRate + ", month=" + paymentMonth + "]";
	}
	
	
	
	
}
