package birthdays;

import java.util.ArrayList;
import java.util.List;

public class StubEmailSender implements EmailSender {
    private List<Employee> employees;

    public StubEmailSender() {
        this.employees = new ArrayList<>();
    }

    @Override
    public void sendEmail(Employee employee) {
        this.employees.add(employee);
    }

    public int getSentEmailCount() {
        return this.employees.size();
    }
}
