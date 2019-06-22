package birthdays;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void isBirthday() {
         var employee = new Employee("Harris", "Jake", LocalDate.of(1998, 12, 9), "email@example.com");

         assertTrue(employee.isBirthday(LocalDate.of(2019, 12, 9)));
        assertFalse(employee.isBirthday(LocalDate.of(2019, 11, 9)));
    }
}