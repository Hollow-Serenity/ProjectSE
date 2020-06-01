import java.sql.SQLException;

abstract class Contact {
    private Integer id = 0;
    private String phoneNumber;

    public Contact() throws SQLException {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}