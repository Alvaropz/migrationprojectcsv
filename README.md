# migrationprojectcsv
 
### Sections
#### Summary
This project will:
- Read data from a CSV file, parse it, populate objects and add to a collection. Moreover, this program must filter the duplicate data from the CSV file. In this scenario, a List populated with arrays of Strings for every record in the CSV file.
- Efficiently write the data from the objects to a relational database using JDBC.
- Expect to demonstrate good programming practices in OOP, SOLID, design patterns (DAO), testing and logging.
#### Pom Dependencies
The dependencies used for this project were:
* org.junit.jupiter:5.8.2 - For testing.
* org.apache.logging.log4j:2.17.1 - For logging.
* mysql:8.0.25 - For database connection and update.
#### SQL Set up / Properties file / Connecting to localhost
To set up the SQL database we followed the Data Access Object ([DAO](https://www.oracle.com/java/technologies/dataaccessobject.html)) design pattern to abstract and encapsulate all access to the data source. This design pattern manages the connection with the data source to obtain and store data. It implements the access mechanism required to work with the data source.

![DAOPattern](https://user-images.githubusercontent.com/63067669/153708510-bd39862b-3fc2-4c08-8252-ab804d9d527e.png)  
*Factory for Data Access Objects Strategy*

In order to have access to the database, we had to create it first. For simplicity, we created it first in MySQL as shown:

```sql
CREATE DATABASE IF NOT EXISTS employeesdb;
USE employeesdb;
```

To allow IntelliJ connecting with MySQL. We had to use the mySQL dependency with all its associated methods:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.25</version>
</dependency>
```

In this project we were using just one factory, so there is only one connection. The *CSVDAOFactory* creates the connection between the IntelliJ and MySQL after passing the information from the *mysql.properties* file:

````properties
dburl=jdbc:mysql://localhost:3306/employeesdb
dbuserid=root
dbpassword=password1
````
<span style="font-size: xx-small; "> *Disclaimer: The information is separated in a different file to avoid information leaks. Be aware always of not sharing this information in public spaces such as GitHub.*</span> 


Once the connection is set, the program will create the *CSVEmployeeDAO* instance that will make possible for having access to the methods that creates and updates the table, and retrieves the data from it.

#### How to use the program
#### Creation phases

This project goes through 4 phases:

* Phase 1 - Initial Reading and Cleaning
    * The program reads the information from a csv file.
    * By reading a file and inserting the data into a list of string arrays. Every element in each array is one of the columns' information in the CSV file. Then, the list is returned.
    * The information is filtered so there are no duplicates. The duplicate data is added to a list for further analysis.

* Phase 2 - Persist to Database
  * The connection between the program and MySQL is properly handled by the script. So, the methods can be called when needed it.
  * The data is migrated into a table after being filtered to its corresponding columns. 
  * The information can be retrieved individually by providing an *Employee ID* or just retrieve all the data.

* Phase 3 - Add Multithreading
  * Using multithreading allows the program to increase its performance substantially by splitting the reading file into different threads.
  * The user can choose how many threads wants to use every time they run the program, so they can compare times between different number of threats.

* Phase 4 - Add Streams and Lambdas
  * To increase performance, there is a second approach to read the CSV file.
  * This reads the file via the *streams* collections, creates an *Employee* instance for every row.
  * By using the method in phase one and *streams*, we can compare the time it takes for each approach to run the code.

#### Performance testing/effect of multithreading/optimal thread number

#### Testing file reader / SQL data transfer / multithreading / functional programming

#### Logging with Log4j
To check if everything is working accordingly to the expected results. We can log the actions of the program, throwing possible *warning* and *errors* that the program might not be able to handle.

<font color="red">INSERT LOG PICTURE OF THE PROGRAM AFTER RUNNING COMPLETELY</font>

