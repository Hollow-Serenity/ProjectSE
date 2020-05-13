package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	public Connection Connect = null;
	public PreparedStatement prestatement = null;
	public ResultSet resultSet = null;
		private final static String CONNECTION_URL = "jdbc:mysql://localhost/StoreManagement";
		public Database() {
			try {
				Connect = DriverManager.getConnection(CONNECTION_URL,"root","d1i2n3o4");
			} catch (SQLException e) {
				System.out.println("Cannot Connect to the Database:  " + e.getMessage());
			}
		}
}
