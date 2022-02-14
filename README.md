# Migraction CSV project
 
### Sections
#### Summary
This project will:
- Read data from a CSV file, parse it, populate objects and add to a collection. Moreover, this program must filter the duplicate data from the CSV file. In this scenario, a List populated with arrays of Strings for every record in the CSV file.
- Efficiently write the data from the objects to a relational database using JDBC.
- Expect to demonstrate good programming practices in OOP, SOLID, design patterns (DAO), testing and logging.
#### Pom Dependencies
The dependencies used for this project are:
* org.junit.jupiter:5.8.2 - For testing.
* org.apache.logging.log4j:2.17.1 - For logging.
* mysql:8.0.25 - For database connection and update.
#### MySQL Set up
The approach for this project was to follow the Data Access Object ([DAO](https://www.oracle.com/java/technologies/dataaccessobject.html)) design pattern to abstract and encapsulate all access to the data source. This design pattern manages the connection with the data source to obtain and store data. It implements the access mechanism required to work with the data source.

![DAOPattern](https://user-images.githubusercontent.com/63067669/153708510-bd39862b-3fc2-4c08-8252-ab804d9d527e.png)  
*Factory for Data Access Objects Strategy*

In order to have access to the database, the database has to be created first. For simplicity, it has to be created first in MySQL as shown:

```sql
CREATE DATABASE IF NOT EXISTS employeesdb;
USE employeesdb;
```
#### Properties file & Connecting to localhost

The mySQL dependency is used with all its associated methods to work on *IntelliJ*:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.25</version>
</dependency>
```

In this project one factory is being used, so there is only one possible connection. The *CSVDAOFactory* creates the connection between the IntelliJ and MySQL after passing the information from the *mysql.properties* file:

````properties
dburl=jdbc:mysql://localhost:3306/employeesdb
dbuserid=root
dbpassword=[MYSQL_PASSWORD]
````
<span style="font-size: xx-small; "> *Disclaimer: The information is separated in a different file to avoid information leaks. Be <u>aware always</u> of not sharing this information in public spaces such as GitHub.*</span>

Once the connection is set, the program will create the *CSVEmployeeDAO* instance that will make possible for having access to the methods that creates and updates the table, and retrieves the data from it.

#### How to use the program
* Project download & set-up

The first thing the user has to do is download the compressed project on GitHub as shown:

  ![HowToUse1](https://user-images.githubusercontent.com/63067669/153816554-8c4bf8d7-7596-4441-a226-3f5e5ae97545.png)

Once is in the local machine, it has to be extracted to be used:

![HowToUse2](https://user-images.githubusercontent.com/63067669/153816555-5f64a520-d88a-489b-bb1f-bf9fb8d4de78.png)

Then, the user has to open the file in their local program. In this case, it's being used *IntelliJ* to run the program:

![HowToUse3](https://user-images.githubusercontent.com/63067669/153816556-dcbdc43f-7075-47c3-9a91-d1379a2afe16.png)

After that, the user must find the project folder in the right directory so, it can be loaded:

![HowToUse4](https://user-images.githubusercontent.com/63067669/153816557-2c624191-e125-4bbd-9929-ed9a399bce59.png)

* Using the program

Now, the user can run it. After reading the CSV file chosen. The program will ask if the user wants to print the duplicate data.
In this scenario, it will print a list will all the data of each record and the total duplicate records in the file:

![HowToUse5](https://user-images.githubusercontent.com/63067669/153817053-bb5208fa-1ae2-4a6c-8014-10e9824fa608.png)

After that, the program will ask the user how many threads they want to use to work with SQL:

![HowToUse6](https://user-images.githubusercontent.com/63067669/153817056-d6c788d7-cd9d-4945-998f-7987b6634b3d.png)

Then, it will ask the user if they want to retrieve a particular data from a specific *Employee's ID*. If so, it will retrieve the data as shown:

![HowToUse7](https://user-images.githubusercontent.com/63067669/153817058-2f43aeca-f5b5-4a79-bbbe-3f69f384fd8b.png)

Lastly, the program will ask the user if they want to retrieve all the data. If so, all records retrieved from the table will be printed in the console.
To reduce the image size, the image has been manipulated to show only a few records:
![HowToUse8](https://user-images.githubusercontent.com/63067669/153817059-2a08d558-94a1-47f7-abe5-e5ddef9b87f5.png)


#### Creation phases

This project goes through 4 phases:

* Phase 1 - Initial Reading and Cleaning
  * The program reads the information from a csv file.
  * This is done by creating a Java class that reads the CSV and was controlled by another class called the ReadDriver. Every element in each array is one of the columns' information in the CSV file. Then, the list is returned.
  * Then, the program finds duplicate data in the original data, to do this a duplicated handler class is used to loop through the data collecting a list of employee id's that is found more than once. The duplicate data is added to a list for further analysis.

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

#### Effect of multithreading

Multithreading was set up to speed up the reading of the information into the database. This was done by creating a custom object to be threaded(You can find the object names MyThread).
This object took in a start and end position and the list of employees. Then the manager than create an array for the all the threads that need creating and running, and activate them, before waiting for each of them to be complete.

Through testing we found there is an optimal number of threads. Each thread that is added increases the performance up untill a point, then it gets worse again. While this depends on the computer, the
tests we have done on one computer found that 20 threads was optimal, after that the effort in creation and removal of the threads negated the effects of the threads themselves.

Below you can find a number of threads and the seconds that they took:

| Threads | Seconds |
|---------|:-------:|
| 1       |  8.02   |
| 2       |  7.99   |
| 4       |  8.01   |
| 5       |  8.24   |
| 8       |  8.03   |
| 10      |  8.06   |
| 20      |  8.25   |
| 50      |  8.21   |
| 100     |  8.45   |
| 150     |  8.47   |

As expected, there is an optimum amount of threads, the computer preformed the tasks faster if 10 or fewer threads are used (except with 5). After that, the program runs slower.
The information is logged with the amount of the threads and the amount it takes.

#### Testing file reader & SQL data transfer

Tests covered two main areas, the method that read the CSV file and the methods that handle the connection between the program and SQL and its methods.

| Test Type        |                                                         Test Results                                                          |
|------------------|:-----------------------------------------------------------------------------------------------------------------------------:|
| Read file method |    ![TestingReader](https://user-images.githubusercontent.com/63067669/153783107-10153db3-10fa-4010-a84f-f6a8b7a6ddfe.png)    |
| MySQL            | ![TestomgSQLReceiving](https://user-images.githubusercontent.com/63067669/153783108-2d285142-1141-44b1-a5bf-9bc6dafb6cf7.png) |

#### Reading with Streams
To improve upon the file reading system, streams were used to read the csv file and remove the duplicate data.
To enable this method an Employees class was created, containing getters and setters and a toString method.
The stream returned a list of employees and made use of the distinct keyword and a map to split the csv data into
rows of employees with the duplicate data removed. A separate method was also created to provide a list of duplicate
data if the user required it.

The stream improved the file reading speed massively. The image below shows the use of StreamsCompareMain class
to compare the two methods.
######
![StreamsAgainstBuffered](https://user-images.githubusercontent.com/63067669/153814682-130f37cf-23ad-4d45-b23c-0fae49228c0f.png)
######
The stream method was over 400 times quicker to read the data, this is largely because the raw data is only looped through
once, and only uses one other class (Employees) when ran.

Log4j was used to log this comparison to the logfile, below shows the output for this comparison. The logger will also
warn the user if the try catch block in the Stream method throws an IOException.
######
![img_2.png](Images/img_2.png)
######

#### Logging with Log4j
To check if everything is working accordingly to the expected results. We can log the actions of the program; throwing possible *warning* and *errors* that the program might not handle properly.

To reduce the image size, multiple *Database connection created* logs were removed from it.

![logExample](https://user-images.githubusercontent.com/63067669/153783101-84bbef7c-a87d-439e-95f5-0c84581877b3.png)

#### Project Management: Scrum
A scrum master was nominated for the project and the week split into 4 sprints. Each sprint fit nicely into the
4 phase format of the project and so in each sprint meeting the next phase was discussed. Each person
was assigned a responsibility for the phase and progress discussed in the next sprint.
In each sprint, meeting minutes were taken by the scrum master and uploaded into the repository. As an example
phase 1's minutes are shown below.
######
![img_3.png](Images/img_3.png)
######
A trello board was also created to keep track of each sprint's backlog, to which the minutes were also uploaded.
The final board is shown below after the project's completion.
######
![img_4.png](Images/img_4.png)
######

