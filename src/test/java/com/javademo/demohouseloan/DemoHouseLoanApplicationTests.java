package com.javademo.demohouseloan;

import com.javademo.demohouseloan.api.model.Mortgage;
import com.javademo.demohouseloan.api.model.Person;
import com.javademo.demohouseloan.services.HouseMortgageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoHouseLoanApplicationTests {

	@Autowired
	private HouseMortgageService houseMortgageService;

	@Test
	public void HouseLoan_Amount_IsNotZero(){
		//Arrange
		Mortgage mortgage = new Mortgage(new Person[]{
				new Person("05073826629", "TestPer", "Test")
		}, 10000, "test lånsøknad");

		//Act
		var result = houseMortgageService.createMortgage(mortgage);

		//Assert
		Assertions.assertNotNull(result);
	}

	@Test
	public void HouseLoan_No_Mortgage_Return_False(){
		//Arrange
		//Act
		//Assert
	}
}
