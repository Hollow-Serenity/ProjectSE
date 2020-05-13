import java.sql.*;

public class User {
    private String host = "jdbc:mysql://localhost:3306/zeroxess";
    private String UN = "root";
    private String PW = "DbPassword123";

    //Inserts contact in table "contacts"
    public void addContact(String firstName, String lastName, String phoneNumber) throws SQLException {
        Connection connection = DriverManager.getConnection(host, UN, PW);
        System.out.println("Connected!");

        String query = "INSERT INTO contacts(idcontact, contactFirstName, contactLastName, phoneNumber) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        String query1 = "SELECT * FROM contacts";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query1);

        //Increment the id of the contact by 1
        int idNumber = 0;
        while (resultSet.next()) {
            idNumber++;
        }

        preparedStatement.setInt(1, idNumber);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, phoneNumber);

        preparedStatement.executeUpdate();
    }

    //Deletes a contact with a specific contactId from table "contacts"
    public void deleteContact(Integer idContact) throws SQLException {
        Connection connection = DriverManager.getConnection(host, UN, PW);

        String query = "DELETE FROM contacts WHERE idcontact = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idContact);

        preparedStatement.executeUpdate();
    }

    //Returns a string with all contacts
    public String allContacts() throws SQLException {
        Connection connection = DriverManager.getConnection(host, UN, PW);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");

        StringBuilder stringBuilder = new StringBuilder();
        while(resultSet.next()) {
            String string = resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4) + "\n";
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}