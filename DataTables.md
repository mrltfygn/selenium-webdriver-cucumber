# Different methods to implement Cucumber Data tables in Java

There are different ways to handle data tables in cucumber scenarios. Which method of data table you use depends on your project and complexity of your test. In this article I will show some methods with their examples. I will also discuss the advantages and disadvantages of each method. In the end, it is up to you to decide which method best suits your needs.

### Method 1: List
You can add data to your Gherkin as a list. 
This method is best used when you have a small data set of the same data type and the order of the data is unlikely to change or not important (for example: a list of numbers that you want to loop through).
For example:
```gherkin
Given some Strings that I want to pass on
  | Lists   |
  | In      |
  | Gherkin |
```

In your step file, this data can be read as:
```java
@Given("^some Strings that I want to pass on$")
public void myMethodToDoSomethingWithTheList(List<String> myList) {
    // Here I can access the different list elements and for example print them
    System.out.println(myList.get(0));
    System.out.println(myList.get(1));
    System.out.println(myList.get(2));
}
```

This is probably the simplest method to implement Data tables. In this case we have 3 string values. Instead of strings you can switch the data type of the list to any data type that you want. However, the disadvantage of this method is that you can only use a *single data type*, so *only* Strings or *only* integers, etc. You cannot mix the different data types into the same list. Furthermore, you are dependent on the order of your data in your list. If you want to add an item to a position other then the end of the list, you will need to update your indexes of the rest if each data point has its own function.

**Summary:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Simple to implement  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Limited to single data type in list  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; You have to keep track of the order of your data in the list  

### Method 2: Map
Instead of a list, we can also use a Map. This solves the problem of keeping track of the order of the data in your list. 
This method is best used when you have a small/medium data set of the same data type and each data value has it's own function.
In Gherkin this would look like this:
```gherkin
When I fill in some personal data
  | Name | Mazin Inaad              |
  | Job  | Test Automation Engineer |
```

In your step file, this data can be read as:
```java
@When("^I fill in some personal data$")
public void myMethodToDoSomethingWithTheMap(Map<String, String> myMap) {
    // Here I can access the different map elements and for example print them
    System.out.println(myMap.get("Job"));
    System.out.println(myMap.get("Name"));
}
```
Because we are now using a map, the order of the data is no longer an issue. We can access the data by simply calling the key which is the first column of the data. However, we can still only have the same data type in the entire map. In this case `<String, String>`.

**Summary:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Fairly simple to implement  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; The order of your data is not an issue  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Limited to single data type in list  

### Method 3: Custom object
You can also create a custom object for your Data table. A great advantage of using this method is that you can handle different data types in your data table. For example: if we want to create data for a product in a store we can create an object called Product.
```java
public class Product {
    private String name;
    private int amountInStock;
    private double price;
}
```
In Gherkin we can give values to each field of the `Product` object:
```gherkin
Given a product
  | Name    | AmountInStock | Price |
  | Cookies | 200           | 1.42  |
```
> It is important that the names in the top row are an exact match with your field names of your custom object. Cucumber is only forgiving in capitalization of the first letter. 
> Also note that the topmost row now has the "keys" and the second row contains the data where in Method 2 the first column was the key and the second column the value.

In your step file, this data can be read as follows:
```java
@Given("^a product$")
public void methodToDoSomethingWithCustomObject(List<Product> myData) {
    //Since myData is a list of products, you need to get the first product in order to access it
    Product myProduct = myData.get(0);
    // Now you can do something with your product
}
```
In order to access the fields we need to add getters to the `Product` Class. So the final java file looks like:
```java
public class Product {
    private String name;
    private int amountInStock;
    private double price;

    public String getName() {
        return name;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public double getPrice() {
        return price;
    }
}
```
With the getters in place, we can now access the fields in our steps. For example:
```java
@Given("^a product$")
public void methodToDoSomethingWithCustomObject(List<Product> myData) {
    //Since myData is a list of products, you need to get the first product in order to access it
    Product myProduct = myData.get(0);
    
    System.out.println(myProduct.getName());
}
```

As you can see we can now use multiple data types in our data input and the order is not a problem. Another advantage is that you can work with multiple data objects like this:
```gherkin
Given a product
  | Name    | AmountInStock | Price |
  | Cookies | 200           | 1.42  |
  | Milk    | 150           | 0.66  |
  | Nuts    | 70            | 5.99  |
```
And access them all in the steps file with for example a loop:
```java
@Given("^a product$")
public void methodToDoSomethingWithCustomObject(List<Product> allProducts) {
    for (Product singleProduct : allProducts) {
        System.out.println(singleProduct.getName());    
    }
}
```
**Summary:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Multiple data types possible  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; The order of your data is not an issue  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Multiple data objects in a single step  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Bit more complex to implement  