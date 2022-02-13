# migrationprojectcsv
 
### Sections
#### Summary
#### Pom Dependencies
#### SQL Set up / Properties file / Connecting to localhost
#### How to use the program
#### Creation phases

##### Phase 1
Phase 1 started by reading the data from the CSV file. This was done by creating a Java class
that would read the CSV and was controlled by another class called the ReadDriver. The second
part of Phase 1 was finding duplicate data in the data, to do this a duplicated handler class was made
that looped through the data collecting a list of employee id's that were found more than once. 
To finish off phase 1 and ensure that no data has been corrupted a series fo checks were added to the
Duplicates Handler class, these took the shape of ensuring that the Data was the correct data type and checking that the emails conformed to standards.

#### Performance testing/effect of multithreading/optimal thread number
#### Testing file reader / SQL data transfer / multithreading / functional programming

#####Multithreading 
The Multithreading was tested to ensure that the correct number of threads were created by the
MultiThreading Manager class
#### Logging with Log4j