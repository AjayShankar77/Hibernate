package jdbcDay1Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

	class AccountDetails {
	
		private int accNumber;
		private String name;
		private double balance;
	
		public int getAccNumber() {
			return accNumber;
		}
		public void setAccNumber(int accNumber) {
			this.accNumber = accNumber;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
	}

public class Account {
	
	ArrayList<AccountDetails> accounts;
	Connection conn = null;
	Scanner scanner;
	
	Account() throws ClassNotFoundException, SQLException {
		accounts = new ArrayList<AccountDetails>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcTraining","root","admin@123");
		scanner = new Scanner(System.in);
	}
	
	@Override
	protected void finalize() throws Throwable {
		conn.close();
	}
	
	
	void getAccountDetailsFromDatabase() throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("Select * from AccountDetails");
		AccountDetails account;
		while(rs.next()) {
			account = new AccountDetails();
			account.setAccNumber(rs.getInt(1));
			account.setName(rs.getString(2));
			account.setBalance(rs.getDouble(3));
			accounts.add(account);
		}
	}
	
	void deleteAccountDetails() throws SQLException {
		AccountDetails account = new AccountDetails();
		System.out.println("Enter the Account Number to delete");
		PreparedStatement st1 = conn.prepareStatement("delete from AccountDetails where AccountNumber = ?");
		st1.setInt(1, account.getAccNumber());
		st1.executeUpdate();
		System.out.println("Account deleted");
	}
	
	void showAccountDetails() {
		for (AccountDetails account : accounts) {
			System.out.println(account);
		}
	}
	
	
	void getAccountDetailsFromUser() throws SQLException {
		int accNumber = 0;
		double balance = 0;
		String name = null;
		System.out.println("Please enter the Account Number");
		accNumber = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Please enter the Customer name");
		name = scanner.nextLine();
		System.out.println("Please enter the Account balance");
		balance = scanner.nextDouble();
		scanner.nextLine();
		AccountDetails account = new AccountDetails();
		account.setAccNumber(accNumber);
		account.setName(name);
		account.setBalance(balance);
		insertAccountDetailsToDatabase(account);
	}
	
	void insertAccountDetailsToDatabase(AccountDetails account) throws SQLException {
		PreparedStatement st = conn.prepareStatement("insert into AccountDetails values(?,?,?)");
		st.setInt(1, account.getAccNumber());
		st.setString(2, account.getName());
		st.setDouble(3, account.getBalance());
		st.executeUpdate();
		System.out.println("Data Inserted");
	}

	
	void printMenu() throws SQLException {
		int userChoice = 0;
		do {
			System.out.println("--------------------");
			System.out.println("1. Inserting into Databse");
			System.out.println("2. Showing all account details");
			System.out.println("3. Deleting Account details from Database");
			System.out.println("4. Exit");
			System.out.println("--------------------");
			userChoice  = scanner.nextInt();
			switch (userChoice) {
			case 1:
				getAccountDetailsFromUser();				
				break;
			case 2:
				getAccountDetailsFromDatabase();
				break;
			case 3:
				deleteAccountDetails();
			case 4:
				System.out.println("exiting.....");
				break;
			default:
				System.out.println("Invalid option.. Try again");
				break;
			}
			
		} while (userChoice!=4);
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Account account = new Account();
		account.printMenu();

	}

}
