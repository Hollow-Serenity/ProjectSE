package UserManagement;

import java.sql.SQLException;

public class Person extends Contact {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName, String phoneNumber) throws SQLException {
        this.firstName = firstName;
        this.lastName = lastName;
        setPhoneNumber(phoneNumber);
    }

    public Person() throws SQLException {
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
