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
import java.util.Scanner;

/**
 * <b>ChequingAccount.java</b> <br>
 * This class acts as a sub class of BankAccount and contains all of account processes relating specifically to the chequing 
 * account type.  This class contains one instance variable called fee which represents the monthly fee deducted from the 
 * specified account's balance.  This class also has methods used to retrieve the specified account's information, add a new 
 * chequing account, complete a monthly update on an existing chequing account, and read data from a file.
 * 
 * @author Justin Henwood
 * @version 1.1
 * @since 52
 */
public class ChequingAccount extends BankAccount{
	private double fee;

	/**
	 * <b>toString</b> <br>
	 * This method overrides the toString method of BankAccount.  This method returns the value of the toString method of 
	 * BankAccount as well as the specific account's monthly fee to create a single, formatted string with all the account 
	 * information.
	 * 
	 * @return Returns the message contained in print.
	 */
	@Override
	public String toString() {
		return super.toString() + "#" + fee;
	}


	/**
	 * <b>addBankAccount</b> <br>
	 * This method accepts the parameters given and uses them to call on the addBankAccount class of BankAccount class 
	 * (parent class).  This method also uses fee parameter to set the value of fee for the current account. This method 
	 * returns true once the data has been processed.
	 * 
	 * @param accNum Account Number
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param phoneNum Phone Number
	 * @param email Email Address
	 * @param bal Account Balance
	 * @param f Fee 
	 * @param interest Interest Rate 
	 * @param minBalance Minimum Balance
	 * @return Returns true when account is successfully added 
	 */
	@Override
	public boolean addBankAccount(int accNum, String firstName, String lastName, long phoneNum, 
			String email, double bal, double f, double interest, double minBalance) {
		super.addBankAccount(accNum, firstName, lastName, phoneNum, email, bal, f, interest, minBalance);
		fee = f;
		return true;

	}

	/**
	 * <b>monthlyAccountUpdate</b> <br>
	 * This method checks to see if subtracting the fee from the current balance will result in the account having a balance 
	 * lower than zero.  If it does not have that result then the fee will be subtracted from account balance.
	 */
	@Override
	public void monthlyAccountUpdate() {
		if (fee <= balance){
			balance -= fee;
			String balString = numberFormat.format(balance);
			balance = Double.parseDouble(balString);
		}
	}

	/**
	 * <b>monthlyAccountUpdate</b> <br>
	 * This method accepts a file and populates a local variable with the fee amount read from the file.  If the file contains 
	 * bad data or if the fee is less than 0, this method returns false.  Otherwise the method returns true.
	 * 
	 * @return True or False depending on whether bad data is encountered
	 */
	public boolean readFile(Scanner bankFile) {
		boolean isOK = super.readFile(bankFile);

		if (!isOK)
			return false;

		if (bankFile.hasNextDouble()) {
			fee = bankFile.nextDouble();
			if (fee < 0) {
				return false;
			}
		}else {
			return false;
		}
		return true;
	}
}
