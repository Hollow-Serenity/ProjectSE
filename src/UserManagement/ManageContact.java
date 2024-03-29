package UserManagement;

import Main.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageContact {
    public ManageContact() throws SQLException {
    }

    private Boolean userExists(String userName) throws SQLException {
        //Check if the username already exists
        boolean userExists = false;
        ResultSet resultSet = DbUtil.dbExecute("SELECT userName FROM users");
        while(resultSet.next()) {
            String username = resultSet.getString("userName");
            if(username.equals(userName)) {
                userExists = true;
            }
        }
        return userExists;
    }
    private Boolean contactIdExists(Contact contact) throws SQLException {
        //Check if contactId already exists
        boolean contactIdExists = false;
        ResultSet resultSet = DbUtil.dbExecute("SELECT idContact FROM contacts");
        while(resultSet.next()) {
            int contactId = resultSet.getInt("idContact");
            if(contact.getId() == contactId) {
                contactIdExists = true;
            }
        }
        return contactIdExists;
    }

    private void insertData() {

    }

    //Inserts contact in table "contacts"
    public void addContactPersonTo(String userName, Person person) throws SQLException {
        if(userExists(userName)) {
            if(!contactIdExists(person)) {
                //Insert contact into contacts
                PreparedStatement preparedStatement1 = DbUtil.getConnection().prepareStatement("INSERT INTO contacts(businessCheck, contactFirstName, contactLastName, phoneNumber) VALUES(?, ?, ?, ?)");
                preparedStatement1.setInt(1, 0);
                preparedStatement1.setString(2, person.getFirstName());
                preparedStatement1.setString(3, person.getLastName());
                preparedStatement1.setString(4, person.getPhoneNumber());
                preparedStatement1.executeUpdate();

                ResultSet resultSet = DbUtil.dbExecute("SELECT MAX(idContact) FROM contacts");
                while(resultSet.next()) {
                    person.setId(resultSet.getInt(1));
                }

                //Insert idContact and userName into user_contacts
                PreparedStatement preparedStatement2 = DbUtil.getConnection().prepareStatement("INSERT INTO user_contacts(idContact, userName) VALUES(?, ?)");
                preparedStatement2.setInt(1, person.getId());
                preparedStatement2.setString(2, userName);
                preparedStatement2.executeUpdate();
            }
        }
    }

    //Inserts contact in table "contacts"
    public void addContactCompanyTo(String userName, Company company) throws SQLException {
        if(userExists(userName)) {
            if(!contactIdExists(company)) {
                //Insert contact into contacts
                PreparedStatement preparedStatement1 = DbUtil.getConnection().prepareStatement("INSERT INTO contacts(businessCheck, contactFirstName, contactLastName, phoneNumber) VALUES(?, ?, ?, ?)");
                preparedStatement1.setInt(1, 1);
                preparedStatement1.setString(2, company.getCompanyName());
                preparedStatement1.setString(3, "");
                preparedStatement1.setString(4, company.getPhoneNumber());
                preparedStatement1.executeUpdate();

                ResultSet resultSet = DbUtil.dbExecute("SELECT MAX(idContact) FROM contacts");
                while(resultSet.next()) {
                    company.setId(resultSet.getInt(1));
                }

                //Insert idContact and userName into user_contacts
                PreparedStatement preparedStatement2 = DbUtil.getConnection().prepareStatement("INSERT INTO user_contacts(idContact, userName) VALUES(?, ?)");
                preparedStatement2.setInt(1, company.getId());
                preparedStatement2.setString(2, userName);
                preparedStatement2.executeUpdate();
            }
        }
    }

    //Delete contact by contact id
    public void deleteContact(Integer idContact) throws SQLException {
        PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement("DELETE FROM contacts WHERE idContact = ?");
        preparedStatement.setInt(1, idContact);
        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement1 = DbUtil.getConnection().prepareStatement("DELETE FROM user_contacts WHERE idContact = ?");
        preparedStatement1.setInt(1, idContact);
        preparedStatement1.executeUpdate();
    }
}
