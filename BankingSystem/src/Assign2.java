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
import javax.swing.JFrame;
/**
 * <b>Test.java</b> <br>
 * This class houses method main and is the starting point for this program.  This class first creates an object of Bank class.
 * After the array is specified this class creates a new JFrame which will house the GUI for this program.  From there, 
 * the user can decide whether they want to add a savings or chequing account, update an account's balance, display a single 
 * account's details, display the details of every account in the banking system, simulate a monthly update on every account
 * in the banking system, or read account data from a file.  In addition, the user can opt to exit the program by clicking the 
 * "X" in the top right of the program.
 * 
 * @author Justin Henwood
 * @version 1.1
 * @since 52
 */
public class Assign2 {
	/**
	 * <b>main</b> <br>
	 * This is the main method an is there for the starting point for this program.  This method creates an object of Bank class
	 * called "bank".  By default the Bank class object is created with no parameters which gives the banking system a limit of 
	 * 1000 bank accounts by default.  If an integer is provided when creating the Bank class object, that integer will be the 
	 * maxmimum number of accounts allowed to be created within the program.  Since Bank class extends JFrame, the frame of bank
	 * is set to exit when the user selects the exit button. Next, the default size of the frame is set to 900 x 400 and finally, 
	 * the JFrame is set to visible.
	 * @param args method main array of type String
	 */
	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bank.setSize(900, 400); 
		bank.setVisible(true);
	}

}
