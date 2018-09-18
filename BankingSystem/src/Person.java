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

/**
 * <b>Person.java</b> <br>
 * This class contains all of the information relating to the owner of a bank account including their full name, phone number
 * and email address.  The methods of this class are called on by the toString method of BankAccount class in order to print
 * out the account owners information.
 * 
 * @author Justin Henwood
 * @version 1.1
 * @since 52
 */
public class Person {
	private String firstName;
	private String lastName;
	private long phone;
	private String email;

	/**
	 * <b>Person class Constructor</b> <br>
	 * This constructor accepts to three string and a long specified by the user in order to initialize this classes instance 
	 * variables.  These instance variables are first name, last name, phone number, and email address.
	 * 
	 * @param fName first name
	 * @param lName last name
	 * @param phone phone number
	 * @param email email address
	 */
	Person (String fName, String lName, long phone, String email) {
		firstName = fName;
		lastName = lName;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * <b>getName</b> <br>
	 * This method returns the first and last name of the current account's account holder.
	 * 
	 * @return Persons whole name
	 */
	public String getName() {
		return (firstName + " " + lastName);
	}

	/**
	 * <b>getPhoneNumber</b> <br>
	 * This method returns the phone number of the current account's account holder.
	 * 
	 * @return Persons phone number 
	 */
	public long getPhoneNumber(){
		return phone;
	}

	/**
	 * <b>getEmailAddress</b> <br>
	 * This method returns the email address of the current account's account holder.
	 * 
	 * @return email address
	 */
	public String getEmailAdress() {
		return email;
	}
}
