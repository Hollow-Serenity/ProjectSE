package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	public Connection Connect;
	public PreparedStatement prestatement;
	public ResultSet resultSet;
		private final static String CONNECTION_URL = "jdbc:mysql://localhost/zeroxess";
		public Database() {
			try {
				Connect = DriverManager.getConnection(CONNECTION_URL,"root","Vlekkie15");
			} catch (SQLException e) {
				System.out.println("Cannot Connect to the Database:  " + e.getMessage());
			}
		}
}
