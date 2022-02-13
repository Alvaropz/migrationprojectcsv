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
#### Performance testing/effect of multithreading/optimal thread number
Multithreading was set up to speed up the reading of the information into the database. This was done by creating a custom object to be threaded(You can find the object names MyThread).
This object took in a start and end position and the list of employees. Then the manager than create an array for the all the threads that need creating and running, and activate them, before waiting for each of them to be complete.

Through testing we found there is an optimal number of threads. Each thread that is added increases the performance up untill a point, then it gets worse again. While this depends on the computer, the 
tests we have done on one computer found that 20 threads was optimal, after that the effort in creation and removal of the threads negated the effects of the threads themselves. 

Below you can find a number of threads and the seconds that they took:

| Threads | Seconds |
|---------|:-------:|
| 1       |   17    |
| 5       |   16    |
| 6       |   14    |
| 10      |   14    |
| 20      |   13    |
| 50      |   15    |
| 100     |   16    |
| 150     |   17    |

As expected, there is an optmimum amount of threads, the computer preformed the tasks faster until 20 threads, after which it was slower.
The information is logged with the amount of the threads and the amount it takes.

#### Testing file reader / SQL data transfer / multithreading / functional programming

#### Logging with Log4j