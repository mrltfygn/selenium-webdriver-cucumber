# Assignments

> All the files which you will need to edit in these assignments are located in **src/test/java/com/capgemini/steps** or **src/test/resources/features**.

## Getting started

1. In IntelliJ, navigate to **src/main/java/com.capgemini/App** and run App.Java.
2. In a chrome browser navigate to "localhost:8080".

### Exercise 1

 1. In **loan.feature**, Right-click on the scenario name 'request a car loan' and choose 'Run Scenario...'
 2. As you can see the webpage gives an error message because the desired amount to borrow is lower than the minimum. 
    Update the step "**And** the amount I want to borrow is '1000'" so that this error message is no longer given. Run the scenario to check that it works.
> The steps are located in **steps/loan_Steps**
 3. Create the step "**And** I continue to explanation" (just remove the # in the feature file). The background color of the step will turn into brown box, this means that there is no java connected to this step. 
    Use Alt + Enter, choose 'Create step definition', choose loan_steps (com.capgemini.steps).
 4. Depending on the version of IntelliJ you have, there will be generated java code `throw new PendingException();`. Remove this code if generated. This exception is generated to make sure that you understand java code a bit before you can run a successful execution.
 5. Inside the body of the newly generated method `public void iContinueToExplanation()`, copy paste the code inside the `iSelectLoanType()` method. You will need to change the CSS selector to find the proper element. This is explained in the next steps.
 6. Switch to your Chrome browser (where you have opened "localhost:8080")
    In Chrome right-click the "Continue to explanation" button and select "Inspect" to identify the selector for this button.
 7. Copy the CSS selector and replace the CSS selector in IntelliJ (see step 5).
 8. Is `.click()` the right method to use here?
 9. Run the scenario

### Exercise 2

On page 2, we want to check if the 'Chosen loan type' and 'Amount to borrow' values are as expected.

1. Create a new step in your feature file in which you define what you expect to see for chosen loan type and amount to borrow.
2. Use the autocomplete function in IntelliJ to help create the cucumber code and the method for this step.
3. In the step, create variables to store the expected values (e.g. expectedLoanType, expectedAmountToBorrow).
4. Retrieve the actual values for chosen loan type and amount to borrow from the website.
5. Use the `Assert.assertEquals()` method to verify if the shown values are correct.
6. Run the scenario. Is your assertion working? How can you test this?
7. Create steps that allow you to continue to the personal data page.

### Exercise 3

1. Create new steps to fill in every field on the personal data page. For every step, change the selector and use the correct interaction.
2. Can you select an option from the dropdown menus?
3. Can you upload a file?

### Exercise 4

1. Parameterize the input data in the step "**And** the amount I want to borrow is '1000'".
2. Can you parameterize other data as well?

### Exercise 5

1. Create a new scenario in the same feature file.
2. Add the already existing step "**Given** I have opened the loan request page".
3. Create a new step after "**Given** I have opened the loan request page" to click an option under "Why do you need a loan?".
> In some cases it is just not possible to find a unique selector for the element you want to interact with. In these cases, the element text can be used to identify the element. Use the Cheatsheet.docx for help on how to click the button with the desired text.
4. Create a step to close the pop-up. What issues do you face?
> Use smart waits if elements are not immediately available.
5. Parameterize the step so that you can click different options using the same step.
6. Use Scenario Outlines to execute this scenario several times with different data.

### Exercise 6

1. Create a new scenario in the same feature file.
2. Reuse the steps from the first scenario, but choose different options and values where you can.
3. Execute the new scenario.

### Exercise 7

1. In Browser.properties you can choose in which webbrowser you want to run your tests.  
   Make sure your scenarios work on Firefox (firefox), Chrome (chrome) using the same code.
> You can also try if it works on Internet Explorer (ie). Most likely you will see some error messages. Try to understand the error message and change your internet explorer settings to get your auto test running here as well.


## Turn it up a notch

### Exercise 8

Random id's are occuring more often in websites, so is your test ready for this?

1. Open **src/main/resources/index.html** and search for below code (near line 361);
```javascript
$scope.rid = '';
$scope.rid2 = '';
```
2. Replace the code to;
```javascript
$scope.rid = Math.random().toString(36).replace(/[^a-z]+/g, '').substr(2, 10) + ':';
$scope.rid2 = Math.random().toString(36).replace(/[^a-z]+/g, '').substr(2, 10) + ':';
```
3. Run your scenario's again, fix what you need to fix.

### Exercise 9

1. Create a new scenario with steps to open the personal data page. Use your existing previous steps for this.
2. Create a new step that allows you to fill in your personal data with a single step. Use the first method explained in the [DataTables](DataTables.md) document to pass your test data to the steps file.

### Exercise 10

1. Create a new scenario in the feature file. 
2. Create a new step that allows you to fill in everything you need to reach the personal data page. Use the second method explained in the [DataTables](DataTables.md) document to pass your test data to the steps file.


### Exercise 11

1. Read method 3 explained in the [DataTables](DataTables.md) document.
2. In your project structure, create a new package in **src/test/java/com/capgemini/** called **helpers** (a package is a folder).
3. In this package create a new Java class and give it the name **PersonalData**.
4. Add all the fields that you need to fill in the PersonalData page as private variables to the class `PersonalData`.
5. Create a new step in your feature file to pass on PersonalData to your steps file. The Gherkin example is given in method 3 of the [DataTables](DataTables.md) document.

### Exercise 12

When filling in large amounts of data (like personal data) your feature file can become quite cluttered with unnecessary information. It can then be an option to use method 4 in the [DataTables](DataTables.md) document.

1. Create a new scenario.
2. Use the step you created before to bring you to the personal data page
3. Create a new step as explained in method 4 of the [DataTables](DataTables.md) document in which you only fill in 'Marital status' and 'Income type'.
4. Why would we only fill in these two fields in Gherkin?
5. Add the following method to the `PersonalData` class:
```java
public static PersonalData generateRemainingData(PersonalData initialData) {
    return DataHelper.generateRemainingData(initialData, () -> new PersonalData());
}
```
5. Now you can generate the remaining data as explained in method 4. Note that the generic DataHelper.generateRemainingData method already exists in this framework.
6. Fill in these values

## Extra exercises

### Exercise 13

1. The website is responding too fast for your eyes to follow everything. 
   We can add screenshots at key points to see what is happening on each page. 
   Try adding screenshots at key points of your scenario and save these to view later on.
```java 
//Store a screenshot in my temp folder
File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(source, new File("C:\\temp\\screenshot" + System.currentTimeMillis() + ".jpg"));
```

### Exercise 14

1. Create a BasePage as explained in [TAGuidelines.md](TAGuidelines.md)
2. Create 4 different pages, 1 for each html page in the assignment and fill it with the selectors as shown in the coding guidelines of [TAGuidelines.md](TAGuidelines.md). 
   Make sure your new pages extend the BasePage.
3. In your steps file, use functions on the pages to interact with the elements.
