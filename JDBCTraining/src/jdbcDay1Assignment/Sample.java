package jdbcDay1Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sample {
		
		ArrayList<AccountDetails> accounts = new ArrayList<AccountDetails>();
		Connection conn = null;
		Scanner scanner;
		
		void Account() throws ClassNotFoundException, SQLException {
			accounts = new ArrayList<AccountDetails>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcTraining","root","admin@123");
			scanner = new Scanner(System.in);
			System.out.println("Done");
		}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Sample sample = new Sample();
		sample.Account();

	}

}
