package birthdays;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class BirthdayGreetings {
    private final EmployeeRepository repository;
    private final EmailSender emailSender;

    public BirthdayGreetings(EmployeeRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    public void sendGreetings(LocalDate today) {
        for (Employee employee : this.repository.getAll()) {
            if (employee.isBirthday(today)) {
                emailSender.sendEmail(employee);
            }
        }
    }

}
