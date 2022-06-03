package com.academy.steps;

import com.academy.ourWebdriver.BrowserFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loan_Steps {
    private final WebDriver browser;

    public loan_Steps(){
        browser = BrowserFactory.getWebDriver();
    }

    @Given("^I have opened the loan request page$")
    public void iHaveOpenedTheLoanRequestPage() throws Throwable {
        browser.get("http://localhost:8080/");
    }


    @And("^I select loan type 'Car-loan'$")
    public void iSelectLoanType() {
        browser.findElement(By.cssSelector("input[value='Car-loan']")).click();
    }

    @And("^the amount I want to borrow is '1000'$")
    public void theAmountIWantToBorrowIs() {
        browser.findElement(By.cssSelector("input[name='amount']")).sendKeys("1000");
    }
}
