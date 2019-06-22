package birthdays;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class BirthdayGreetingsTest {
    private StubEmailSender emailSender;

    @Before
    public void createEmailSender() {
        emailSender = new StubEmailSender();
    }

    @Test
    public void sendGreetingsWithNoEmployeesBirthdays() {
        var today = LocalDate.of(2019, 6, 20);
        var employeeRepository = new EmployeeRepository() {
            @Override
            public List<Employee> getAll() {
                return List.of(makeEmployee(LocalDate.of(1999, 1, 1)));
            }
        };
        var birthdayGreetings = new BirthdayGreetings(employeeRepository, emailSender);

        birthdayGreetings.sendGreetings(today);

        assertEquals(0, emailSender.getSentEmailCount());
    }

    protected Employee makeEmployee(LocalDate birthday) {
        return new Employee("Smith", "John", birthday, "testing@example.com");
    }

    @Test
    public void sendGreetingsWith1EmployeesBirthdays() {
        var today = LocalDate.of(2019, 6, 20);
        var employeeRepository = new EmployeeRepository() {
            @Override
            public List<Employee> getAll() {
                return List.of(makeEmployee(today));
            }
        };
        var birthdayGreetings = new BirthdayGreetings(employeeRepository, emailSender);

        birthdayGreetings.sendGreetings(today);

        assertEquals(1, emailSender.getSentEmailCount());
    }
}
