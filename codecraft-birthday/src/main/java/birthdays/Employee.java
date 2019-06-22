package birthdays;

import java.time.LocalDate;

public class Employee {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String email;

    public Employee(String lastName, String firstName, LocalDate dateOfBirth, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public boolean isBirthday(LocalDate date ){
        if(date.getMonthValue()== dateOfBirth.getMonthValue() && date.getDayOfMonth()== dateOfBirth.getDayOfMonth()){
            return true;
        }
        return  false;
    }
}
