package com.academy.steps;

import com.academy.driver.Browser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;

public class LoanSteps {
    private final Browser browser = Browser.getInstance();

    @Given("I have opened the loan request page")
    public void iHaveOpenedTheLoanRequestPage() {
        browser.get("http://localhost:8080/");
    }

    @And("I select loan type Car-loan")
    public void iSelectLoanTypeCarLoan() {
        browser.findElement(By.cssSelector("input[value='Car-loan']")).click();
    }

    @And("the amount I want to borrow is 1000")
    public void theAmountIWantToBorrowIs() {
        browser.findElement(By.cssSelector("input[name='amount']")).sendKeys("1000");
    }

}
