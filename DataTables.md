# Data input with Cucumber

There are different ways to handle data in cucumber scenarios. In this article I will show some methods with their examples. I will also discuss the advantages and disadvantages of each method. In the end, it is up to you to decide which method best suits your needs.

### Method 1: List
It is possible to pass a list of data in cucumber to your steps. Lets start by looking at an example in Gherkin
```gherkin
Given some Strings that I want to pass on
  | Lists   |
  | In      |
  | Gherkin |
```

The corresponding step would then be:
```java
@Given("^some Strings that I want to pass on$")
public void myMethodToDoSomethingWithTheList(List<String> myList) {
    // Here I can access the different list elements and for example print them
    System.out.println(myList.get(0));
    System.out.println(myList.get(1));
    System.out.println(myList.get(2));
}
```
Instead of strings you can ofcourse switch the data type of the list to any data type that you want.

This is probably the simplest method. However, the disadvantage of this method is that you can only use one data type. So only Strings, integers, booleans, etc. You cannot mix the different data types into the same list. Furthermore, you are dependent on the order of your data in your list. If you want to add an item to a position other then the end of the list, you will need to update your indexes of the rest.

**To summurize:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Simple to implement  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Limited to single data type in list  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; You have to keep track of the order of your data in the list  

### Method 2: Map
Instead of a list, we can also you a Map. This solves the problem of keeping track of the order of the data in your list. In Gherkin this would look like this:
```gherkin
When I fill in some personal data
  | Name | Mazin Inaad              |
  | Job  | Test Automation Engineer |
```

The corresponding step would then be:
```java
@When("^I fill in some personal data$")
public void myMethodToDoSomethingWithTheMap(Map<String, String> myMap) {
    // Here I can access the different map elements and for example print them
    System.out.println(myMap.get("Job"));
    System.out.println(myMap.get("Name"));
}
```
Because we are now using a map, the order of the data is no longer an issue. We can access the data by simply calling the key which is the first column of the data. However, we can still only have the same data type in the entire map. In this case `<String, String>`.

**To summurize:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Fairly simple to implement  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; The order of your data is not an issue  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Limited to single data type in list  
