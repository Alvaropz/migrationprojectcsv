package com.spartaglobal.migrationprojecttests;

import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.ObjectIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Example {

    @Test
    @DisplayName("Check that the object is properly created based on the information")
    void testObjectiscreatedProperly()
    {
        Object obj = new Object(1, "First Name", "Middle Name", "Last Name", "M", "Email", "DOB", "Doj", 10);

        Assertions.assertEquals(obj.ID = 1, obj.namePrefix = "Mrs.", obj.FirstName = "First Name", obj.middleName = "Middle Name", obj.lastName = "lastName", obj.Gender = "M", obj.email = "Email", obj.DOB = "DOB" obj.DOJ = "DOJ", obj.Salary = 10);
    }

    @Test
    @DisplayName("Check that the object is properly created based on the information")
    void testObjectiscreatedProperly()
    {
        Object obj = new Object(1, "First Name", "Middle Name", "Last Name", "M", "Email", "DOB", "Doj", 10);

        Assertions.assertEquals(obj.ID = 1, obj.namePrefix = "Mrs.", obj.FirstName = "First Name", obj.middleName = "Middle Name", obj.lastName = "lastName", obj.Gender = "M", obj.email = "Email", obj.DOB = "DOB" obj.DOJ = "DOJ", obj.Salary = 10);
    }

    @Test
    @DisplayName("Check that the object is properly created with null values")
    void testObjectiscreatedProperlywithNullValues()
    {
        Object obj = new Object(1, null, "Middle Name", "Last Name", "M", "Email", "DOB", "Doj", 10);

        Assertions.assertEquals(obj.ID = 1, obj.namePrefix = null, obj.FirstName = "First Name", obj.middleName = "Middle Name", obj.lastName = "lastName", obj.Gender = "M", obj.email = "Email", obj.DOB = "DOB" obj.DOJ = "DOJ", obj.Salary = 10);
    }
}
