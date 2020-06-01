package Main;

import java.sql.SQLException;

public class Company extends Contact {
    private String companyName;

    public Company(String companyName, String phoneNumber) throws SQLException {
        this.companyName = companyName;
        setPhoneNumber(phoneNumber);
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}