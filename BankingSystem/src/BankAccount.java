/**
 * <b>Assignment 2</b> <br>
 * This program simulates a banking system designed to be used by bank clerks in order to add, alter and update accounts
 * in their banking system.  In addition to adding accounts within the program, the user can also choose to load account 
 * data from a file.  This program uses a GUI in order to receive data and display it to the user.  The program creates an
 * ArrayList with a default max size of 1000 which acts as a "database" holding the information of all accounts within the 
 * system.  The user can than select from a list of possible options relating to their bank which include: Adding an account, 
 * Updating an account (Withdrawal or Deposit), Displaying an account and its information, Print all accounts ad their 
 * information, Run monthly update on all accounts, and Reading account data from a text file.  This program uses polymorphism 
 * to allow for the creation of two different bank account types: Savings and Chequing.  The savings account has unique 
 * minimum balance and interest rate fields which allow the account to gain interest every month so long as account balance 
 * is over the minimum balance.  The chequing account has a unique fee filed which takes a fee from the account balance 
 * every month as long as there is enough money in the account. All accounts must have valid data and unique account numbers.  
 * 
 * @author Justin Henwoood, 040-900-406 <br>
 * <b>Course: </b> CST8132-OOP <br>
 * <b>Assignment: </b> 2 <br>
 * <b>Date: </b> 2018-04-19 <br>
 * <b>Professor: </b> Dr Anu Thomas <br>
 */
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * <b>BankAccount.java</b> <br>
 * This abstract class contains all the information relating to bank account for the system.  This class has four instance 
 * variables: accNumber which is the account number for the current bank account, accHolder which is an object of Person
 * class, and balance which is the current accounts account balance, numberFormat which is used to round number to 2 decimal 
 * places.  This account has methods used to add an account and its subsequent information, print account information and 
 * update the accounts balance.  This class is abstract and acts as a super class with ChequingAccount class and SavingsAccount 
 * class both acting as sub classes.
 * 
 * @author Justin Henwood
 * @version 1.1
 * @since 52
 */
public abstract class BankAccount {
	protected int accNumber;
	protected Person accHolder;
	protected double balance;
	protected DecimalFormat numberFormat = new DecimalFormat("#.00");

	/**
	 * <b>BankAccount constructor</b> <br>
	 * Initial Constructor for BankAccount class
	 */
	BankAccount() {
	}

	/**
	 * <b>toString</b> <br>
	 * This method is called upon by the toString methods of ChequingAccount and SavingsAccount respectively.  By
	 * calling the get methods of accHolder, a String print is populated by a formatted message containing all the 
	 * non account type information for the current bank account.  This string is then returned.
	 */
	public String toString() {
		return accNumber + "#" + accHolder.getName() + "#" + accHolder.getPhoneNumber() + "#" + accHolder.getEmailAdress() + "#" + balance;
	}

	/**
	 * <b>addBankAccount</b> <br>
	 * This method accepts the parameters given and uses them to set the account number, account balance, and account holder 
	 * (object of person class).  This method returns true once the data has been processed.
	 * 
	 * @param accNum Account Number
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param phoneNum Phone Number
	 * @param emailAddress Email Address
	 * @param bal Account Balance
	 * @param fee Fee 
	 * @param interestRate Interest Rate 
	 * @param minBalText Minimum Balance
	 * @return Returns true when account is successfully added 
	 */
	public boolean addBankAccount(int accNum, String firstName, String lastName, long phoneNum, 
			String emailAddress, double bal, double fee, double interestRate, double minBalText) {
		accNumber = accNum;
		String balString = numberFormat.format(bal);
		balance = Double.parseDouble(balString);
		accHolder = new Person(firstName, lastName, phoneNum, emailAddress);
		return true;
	}

	/**
	 * <b>updateBalance</b> <br>
	 * This method accepts a double amount specified by the user and checks it against the current account balance.  
	 * If the balance after the transaction is greater than zero then the transaction is completed.  Otherwise an 
	 * error message occurs.
	 * 
	 * @param bal Amount of which will be deposited or withdrawn as specified by the user.
	 * @return True or False depending on whether update amount is valid
	 */
	public boolean updateBalance(double bal) {
		String balString = numberFormat.format(bal);
		if (balance + Double.parseDouble(balString) >= 0) {
			balance += Double.parseDouble(balString);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <b>getAccNumber</b> <br>
	 * Getter method which when called upon by Bank class returns the account number of the current bank account
	 * 
	 * @return accNumber account number of current bank account
	 */
	public int getAccNumber() {
		return accNumber;
	}

	/**
	 * <b>readFile</b> <br>
	 * This method accepts a file and populates local variables with the account data read from the file.  The 
	 * data gathered is used to make an instance of Person class.  Returns false is bad data is encountered, 
	 * otherwise returns true.
	 * 
	 * @param bankFile file used to obtain data from
	 * @return True or False depending on whether file has good data or bad data
	 */
	public boolean readFile(Scanner bankFile) {
		if (bankFile.hasNextInt()) {
			accNumber = bankFile.nextInt();
		}else {
			return false;
		}

		String fName = bankFile.next();
		String lName = bankFile.next();
		long ph = 0;
		if (bankFile.hasNextLong()) {
			ph = bankFile.nextLong();
		}else {
			return false;
		}
		String email = bankFile.next();
		accHolder = new Person(fName, lName, ph, email);

		if (bankFile.hasNextDouble()) {
			balance = bankFile.nextDouble();
		}else {
			return false;
		}
		return true;
	}

	/**
	 * <b>monthlyAccountUpdate</b> <br>
	 * Abstract method for monthly account update.  This method is meant to be overridden by ChequingAccount or
	 * SavingsAccount depending on what type of account is passed through.
	 */
	public abstract void monthlyAccountUpdate();
}
