package com.spartaglobal.migrationproject;
import java.util.Objects;

public class Employee {
    private String employeeID;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String dateOfJoin;
    private String salary;

    public Employee(String employeeID, String namePrefix, String firstName,
                    String middleInitial, String lastName, String gender,
                    String email, String dateOfBirth, String dateOfJoin, String salary) {

        this.employeeID = employeeID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfJoin() {
        return dateOfJoin;
    }

    public String getSalary() {
        return salary;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfJoin(String dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[" + employeeID + " " + namePrefix + " " + firstName + " " + middleInitial +
                " " + lastName + " " + gender + " " + email + " " + dateOfBirth + " " + dateOfJoin +
                " " + salary + "]\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID.equals(employee.employeeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
}