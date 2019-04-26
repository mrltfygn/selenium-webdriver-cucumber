# Different methods to implement Cucumber Data tables in Java

There are different ways to handle data tables in cucumber scenarios. Which method of data table you use depends on your project and complexity of your test. In this article I will show some methods with their examples. I will also discuss the advantages and disadvantages of each method. In the end, it is up to you to decide which method best suits your needs.

## Method 1: List
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

## Method 2: Map
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
Because we are now using a map, the order of the data is no longer an issue. We can access the data by simply calling the key which is the first column of the data. However, we can still only have the same data type in the entire map. In this case `<String, String>`. Keep in mind that Keys in the Map need to be unique[^1].
[^1]: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html

**Summary:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Fairly simple to implement  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; The order of your data is not an issue  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Limited to single data type in list  

## Method 3: Custom object
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

As you can see we can now use multiple data types in our data input (Strings, Integers and Doubles in our case). Another advantage is that you can work with multiple data objects like this:
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

## Method 4: Custom objects with fake data
This is a more complex usage of Data tables where I no longer wanted to create new test data every time I ran my test. It is actually an extension of Method 3. This method is highly recommended when working in a test environment where your testdata has to be unique each time you run your test (for example when filling in forms) or when working with large data sets. It allows you to control the values that you want and randomize other values.

Like we did in Method 3, we need to create an object. But we now add a Constructor which generates our fake data. I use the [java faker package](https://github.com/DiUS/java-faker) to generate the fake data. For example:
```java
public class ContactFormData {
    private String name;
    private String email;
    private String message;
    
    public ContactFormData() {
        Faker faker = new Faker();
        
        name = faker.name().fullName();
        email = faker.internet().safeEmailAddress();
        message = faker.gameOfThrones().quote();
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getMessage() {
        return message;
    }
}
```
In Gherkin we give values only to the fields that we want to control the values of:
```gherkin
When I fill out the contact form
  | Name        |
  | Mazin Inaad |
```
And then in our steps file, we generate the rest of the data:
```java
@When("^I fill out the contact form$")
public void methodToDoSomethingWithCustomObject(List<ContactFormData> formDataList) {
    ContactFormData initialData = formDataList.get(0);
    ContactFormData finalData = ContactFormData.generateRemainingData(initialData);
    // Now we can do something with the final data
}
```
As you can see I am calling the method `generateRemainingData` in ContactFormData. You can add this method to the `ContactFormData` class with the following code:
```java
public static ContactFormData generateRemainingData(ContactFormData initialData) {
    return DataHelper.generateRemainingData(initialData, () -> new ContactFormData());
}
```
What this method does is it calls the more generic function in my `DataHelper` class (which we have not yet created). In calling this function it passes on the initialData that I filled in in my Gherkin and it passes on a new data object using the constructor that generates the fake data. So all we need now is the generic `generateRemainingData` method somewhere (In my case it is located in a class called DataHelper. You can place this method anywhere you want)
```java
/**
* This function fills data for the fields that have not been defined in the initialdata at the gherkin level.
* The data is not generated in this function itself, but by the supplier which should supply the new object with
* generated faker data.
*
* Created by: Mazin Inaad
* 
* @param initialData the initial data.
* @param newDataSupplier the new daya supplier.
* @param <T> the type.
* @return object with generated fake data.
*/
public static <T> T generateRemainingData(final T initialData, final Supplier<T> newDataSupplier) {
   final T result = newDataSupplier.get();
   final Field[] fields = initialData.getClass().getDeclaredFields();
   try {
       for (Field f : fields) {
           f.setAccessible(true);
           if (f.get(initialData) != null) {
               f.set(result, f.get(initialData));
           }
       }
   } catch (IllegalAccessException e) {
       Assert.fail("Error occured in generateRemainingData while trying to copy data from initial data to generated data");
   }
   return result;
}
```
This method for Data tables in Cucumber is also very handy when you have large data sets. Since you only fill in some fields in your Gherkin and generate data for the rest of the fields, your feature file is not cluttered with unnecessary information.

**Summary:**  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Possibility to generate fake data  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Multiple data types possible  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; The order of your data is not an issue  
&nbsp;&nbsp;&nbsp;&nbsp;**\+**&nbsp; Multiple data objects in a single step  
&nbsp;&nbsp;&nbsp;&nbsp;**\-**&nbsp; Complexity of code    


