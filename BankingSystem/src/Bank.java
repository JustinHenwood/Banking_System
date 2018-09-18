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
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * <b>Bank.java</b> <br>
 * This class contains the logic for all of the menu processing options in Assing2.  In addition, this class extends JFrame and
 * houses the GUI and all of it's components.  This class is populated with instance variables of various JFrame components 
 * including JPanels, JLabels, JButtons, JRadioButtons, JTextFields, an Array of JButtons (which act as the main menu buttons 
 * for the program), TableModels, and a CardLayout which is essential to the functionality of the GUI.  This class also has 
 * three instance variables which are related to the banking system itself. These are: accounts which is an ArrayList of 
 * instances of BankAccount class which acts as a "database" that contains information on all accounts in the system.  numAccount
 * which is a constantly changing int variable that represent the number of accounts in the banking system and makes sure the 
 * number of accounts doesn't exceed the amount specified by the user.  sizeBank refers to the arrayList size and is the maximum 
 * number of account allowed within the system.
 * 
 * @author Justin Henwood
 * @version 1.1
 * @since 52
 */
public class Bank extends JFrame{
	private static final long serialVersionUID = 1935981829614943143L;
	private ArrayList <BankAccount> accounts;
	private int numAccounts;
	private int sizeBank;

	private JPanel cardLayoutPanel, card1, card2, card3, card4, card5, card6, card7, menuButtonsPanel;
	private JLabel welcomeLabel, addAccStatusLabel, fNameLabel, lNameLabel, phoneLabel, emailLabel, 
	accNumLabel, opBalLabel, monFeeLabel, interRateLabel, minBalLabel, updateAccNumLabel, 
	updateAmountLabel, updateStatusLabel, displayAccNumLabel, displayStatusLabel, monthlyUpdateStatusLabel;
	private JButton createAccButton, updateAccButton, displayAccButton, monthlyUpdateButton, readFileButton;
	private JRadioButton chequingRadioButton, savingsRadioButton;
	private JTextField fNameText, lNameText, phoneText, emailText, accNumText, opBalText, monFeeText, 
	interRateText, minBalText, updateAccNumText, updateAmountText, displayAccNumText;
	private JButton[] menuButtons;
	JScrollPane scrollPane;
	private CardLayout cardLayout = new CardLayout();
	DefaultTableModel displayModel = new DefaultTableModel(); 
	DefaultTableModel printModel = new DefaultTableModel();

	/**
	 * <b>Bank class constructor</b> <br>
	 * Default no-args constructor.  When no bank size is specified in Assign2, an ArrayList of instances of BankAccount class 
	 * is created with a default length of 1000 allowing for 1000 unique bank account within the system. Constructor sets the 
	 * name of the frame to "Justin's Banking System".  Finally, the method "guiInitialization" is called which initializes all 
	 * of the frame's components so the user can interact with them within the GUI 
	 */
	Bank() {
		super("Justin's Banking System");
		sizeBank = 1000;
		accounts = new ArrayList<BankAccount>(sizeBank);
		guiInitialization();
	}

	/**
	 * <b>Bank class constructor</b> <br>
	 * Default args constructor.  When a bank size is specified in Assign2, an ArrayList of instances of BankAccount class 
	 * is created with a length of the specified amount, allowing for that amount of unique bank account within the system. 
	 * Constructor sets the name of the frame to "Justin's Banking System".  Finally, the method "guiInitialization" is called 
	 * which initializes all of the frame's components so the user can interact with them within the GUI 
	 * @param size integer specified by user in Assign2 to be used as the length of ArrayList accounts.
	 */
	Bank(int size) {
		super("Justin's Banking System");
		sizeBank = size;
		accounts = new ArrayList<BankAccount>(sizeBank);
		guiInitialization();
	}

	/**
	 * <b>guiInitialization</b> <br>
	 * This method initializes all of the components within the bank frame.  This frame contains two main JPanels.  The first 
	 * JPanel is a gridlayout which contains an array of buttons which act as the program menu.  The second JPanel is a cardLayout 
	 * which display the sub options for each of the menu options.  Everytime a menu button is selected, the corresponding card 
	 * is selected. For example, when the add account menu button is selected, card layout displaying the account information 
	 * fields is shown.  The "addToCard" methods for each card are alos invoked.  There job is similar to this method as they 
	 * initialize all of the components for their specefic card.  Finally, an action listener is implemented for each of the menu 
	 * buttons so that when a menu button is selected, it's respective card is shown and the method responsible for it's processing 
	 * is invoked. 
	 */
	public void guiInitialization() {
		menuButtonsPanel = new JPanel();
		menuButtonsPanel.setLayout(new GridLayout(1, 6));
		cardLayoutPanel = new JPanel();
		cardLayoutPanel.setLayout(cardLayout);
		card1 = new JPanel();
		card2 = new JPanel();
		card2.setLayout(new GridLayout(12, 2));
		card3 = new JPanel();
		card3.setLayout(new GridLayout(12, 2));
		card4 = new JPanel();
		card4.setLayout(new GridLayout(2, 1));
		card5 = new JPanel();
		card5.setLayout(new GridLayout(0, 1));
		card6 = new JPanel();
		card6.setLayout(new GridLayout(12, 2));
		card7 = new JPanel();
		card7.setLayout(new GridLayout(12, 2));

		welcomeLabel = new JLabel("Welcome To Justin's Banking System");
		card1.add(welcomeLabel);

		addToCard2();
		addToCard3();
		addToCard4();
		addToCard5();
		addToCard6();
		addToCard7();

		cardLayoutPanel.add(card1, "1");
		cardLayoutPanel.add(card2, "2");
		cardLayoutPanel.add(card3, "3");
		cardLayoutPanel.add(card4, "4");
		cardLayoutPanel.add(card5, "5");
		cardLayoutPanel.add(card6, "6");
		cardLayoutPanel.add(card7, "7");

		menuButtons = new JButton[6];
		menuButtons[0] = new JButton("Add Account");
		menuButtons[1] = new JButton("Update Account");
		menuButtons[2] = new JButton("Display Account");
		menuButtons[3] = new JButton("Print All Accounts");
		menuButtons[4] = new JButton("Run Monthly Update");
		menuButtons[5] = new JButton("Read Data From File");

		for (int i = 0; i < menuButtons.length; i++)
			menuButtonsPanel.add(menuButtons[i]);

		add(cardLayoutPanel, BorderLayout.CENTER);
		add(menuButtonsPanel, BorderLayout.NORTH);

		menuButtons[0].addActionListener(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent e )
					{
						cardLayout.show(cardLayoutPanel, "2");
						addAccount();
					} 
				}
				);

		menuButtons[1].addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent e )
					{
						cardLayout.show(cardLayoutPanel, "3");
						updateAccount();
					} 
				}
				);

		menuButtons[2].addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent e )
					{
						cardLayout.show(cardLayoutPanel, "4");
						displayAccount();
					} 
				}
				);

		menuButtons[3].addActionListener(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent e )
					{
						cardLayout.show(cardLayoutPanel, "5");
						printAccountDetails();
					} 
				}
				);

		menuButtons[4].addActionListener(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent e )
					{
						cardLayout.show(cardLayoutPanel, "6");
						monthlyUpdate();
					} 
				}
				);

		menuButtons[5].addActionListener(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent e )
					{
						cardLayout.show(cardLayoutPanel, "7");
						readDataListener();
					} 
				}
				);
	}


	/**
	 * <b>addAccount</b> <br>
	 * This method contains the main logic used to create a new bank account.  If the maximum number of accounts is reached 
	 * the method will display an error message and the add account button will be disabled.  If there is room in the
	 * banking system then the user will be prompted to enter the new account information into a series of Text Fields.  When 
	 * the user has entered all the account data they can select the add account button.  If they have entered bad data, they 
	 * will receive an error message, otherwise the method temporary instance of the selected account type and then retrieves 
	 * the data from the text fields and inserts them into temporary local variables.  So long as the integer values meet the 
	 * Requirements and the account number isn't already in use (this is checked by calling the findAccount method using the 
	 * given account number as the parameter.  If anything other than -1 is returned, the account is not created and an error 
	 * message is displayed), the new account is added to accounts and the value of numAccount is increased by one in order 
	 * to signify that an account has been successfully added to the banking system.
	 *
	 * @return Returns a value of true or false depending on whether a new account could be created.
	 */
	public boolean addAccount() {
		if(numAccounts >= sizeBank) {
			addAccStatusLabel.setText("Bank is full.... cannot add account!!!");
			createAccButton.setEnabled(false);
			return false;
		} else addAccStatusLabel.setText("Account has not been submitted");

		createAccButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent e )
					{
						if (chequingRadioButton.isSelected()) {

							fNameText.getText();

							BankAccount tempObj = new ChequingAccount();

							String accNumberString = accNumText.getText();
							int accNumber = 0;
							String firstName = fNameText.getText();
							String lastName = lNameText.getText();
							String phoneNumString = phoneText.getText();
							long phoneNum = 0;
							String emailAddress = emailText.getText();
							String balanceString = opBalText.getText();
							double balance = 0;
							String feeString = monFeeText.getText();
							double fee = 0;
							double interestRate = 0;
							double minimumBalance = 0;

							try {
								accNumber = Integer.parseInt(accNumberString);
								phoneNum = Long.parseLong(phoneNumString);
								balance = Double.parseDouble(balanceString);
								fee = Double.parseDouble(feeString);

								if (findAccount(accNumber) == -1) {

									if (accNumber > 0 && phoneNum > 0 && balance > 0 && fee > 0) {
										if(tempObj.addBankAccount(accNumber, firstName, lastName, phoneNum, emailAddress, balance, fee, interestRate, minimumBalance))
											accounts.add(numAccounts, tempObj);
										numAccounts++;

										fNameText.setText("");
										lNameText.setText("");
										phoneText.setText("");
										emailText.setText("");
										accNumText.setText("");
										opBalText.setText("");
										monFeeText.setText("");
										interRateText.setText("");
										minBalText.setText("");
										addAccStatusLabel.setText("Account has not been submitted");

										cardLayout.show(cardLayoutPanel, "1");
										welcomeLabel.setText("Chequing Account Successfully Added!");

									}else {
										addAccStatusLabel.setText("Status: Invalid Input");
									}

								} else {
									addAccStatusLabel.setText("Account number is already in use!");
								}

							} catch (Exception addAccException){
								addAccStatusLabel.setText("Status: Invalid Input");
							}



						}else if (savingsRadioButton.isSelected()) {

							fNameText.getText();

							BankAccount tempObj = new SavingsAccount();

							String accNumberString = accNumText.getText();
							int accNumber = 0;
							String firstName = fNameText.getText();
							String lastName = lNameText.getText();
							String phoneNumString = phoneText.getText();
							long phoneNum = 0;
							String emailAddress = emailText.getText();
							String balanceString = opBalText.getText();
							double balance = 0;
							double fee = 0;
							String interestRateString = interRateText.getText();
							double interestRate = 0;
							String minimumBalanceString = minBalText.getText();
							double minimumBalance = 0;

							try {
								accNumber = Integer.parseInt(accNumberString);
								phoneNum = Long.parseLong(phoneNumString);
								balance = Double.parseDouble(balanceString);
								interestRate = Double.parseDouble(interestRateString);
								minimumBalance = Double.parseDouble(minimumBalanceString);

								if (findAccount(accNumber) == -1) {

									if (accNumber > 0 && phoneNum > 0 && balance > 0 && interestRate > 0 && interestRate < 1 && minimumBalance > 0) {
										//System.out.println(accNumber);
										if(tempObj.addBankAccount(accNumber, firstName, lastName, phoneNum, emailAddress, balance, fee, interestRate, minimumBalance))
											accounts.add(numAccounts, tempObj);
										numAccounts++;

										fNameText.setText("");
										lNameText.setText("");
										phoneText.setText("");
										emailText.setText("");
										accNumText.setText("");
										opBalText.setText("");
										monFeeText.setText("");
										interRateText.setText("");
										minBalText.setText("");
										addAccStatusLabel.setText("Account has not been submitted");

										cardLayout.show(cardLayoutPanel, "1");
										welcomeLabel.setText("Savings Account Successfully Added!");


									}else {
										addAccStatusLabel.setText("Status: Invalid Input");
									}

								} else {
									addAccStatusLabel.setText("Status: Account number is already in use!");
								}

							} catch (Exception excep){
								addAccStatusLabel.setText("Status: Invalid Input");
							}


						}else addAccStatusLabel.setText("Status: Account type has not been selected.");
					} 
				}
				);

		chequingRadioButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed( ActionEvent e )
			{
				interRateText.setEditable(false);
				interRateText.setText("");
				minBalText.setEditable(false);
				minBalText.setText("");
				monFeeText.setEditable(true);
			} 
		}
				);
		savingsRadioButton.addActionListener(new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				interRateText.setEditable(true);
				minBalText.setEditable(true);
				monFeeText.setEditable(false);
				monFeeText.setText("");
			} 
		}
				);

		return true;
	}

	public void monthlyUpdate() {


		if (numAccounts == 0) {
			monthlyUpdateButton.setEnabled(false);
			monthlyUpdateStatusLabel.setText("There are now accounts to update.");
		}else {
			monthlyUpdateStatusLabel.setText("");
			monthlyUpdateButton.setEnabled(true);
			monthlyUpdateButton.addActionListener(
					new ActionListener() // anonymous inner class
					{
						public void actionPerformed( ActionEvent e )
						{
							for(int i=0; i<numAccounts; i++)
								accounts.get(i).monthlyAccountUpdate();

							cardLayout.show(cardLayoutPanel, "1");
							welcomeLabel.setText("Monthly Update Comleted");
						}
					});


			for(int i=0; i<numAccounts; i++)
				accounts.get(i).monthlyAccountUpdate();


		}

	}

	/**
	 * <b>printAccountDetails</b> <br>
	 * This method contains the main logic used to print all of the account information for every account in 
	 * the banking system.  This method contains a for loop that iterates through every account within the 
	 * banking system.  A String array called parts is populated with the account information of the current 
	 * account by evoking that account's toString method.  A second String array called updateArray is populated
	 * with the contents of parts along with account type specific information.  The information contained in 
	 * updateArray is then inserted into the JTable Model printModel.  If a row for a specific account has not 
	 * been created, a new blank row is created and the values of that row are populated with the account information
	 * from updateArray.
	 */
	public void printAccountDetails() {

		for(int i=0; i<numAccounts; i++) {


			String[] parts = accounts.get(i).toString().split("#");
			String[] updateArray = new String[9];

			if (accounts.get(i) instanceof SavingsAccount) {
				updateArray[0] = parts[0];
				updateArray[1] = "Savings";
				updateArray[2] = parts[1];
				updateArray[3] = parts[2];
				updateArray[4] = parts[3];
				updateArray[5] = parts[4];
				updateArray[6] = "NA";
				updateArray[7] = parts[5];
				updateArray[8] = parts[6];

			}else {
				updateArray[0] = parts[0];
				updateArray[1] = "Chequing";
				updateArray[2] = parts[1];
				updateArray[3] = parts[2];
				updateArray[4] = parts[3];
				updateArray[5] = parts[4];
				updateArray[6] = parts[5];
				updateArray[7] = "NA";
				updateArray[8] = "NA";
			}

			if (printModel.getRowCount() < numAccounts)
				printModel.addRow(new Object[]{"", "", "", "", "", "", "", "", "", });

			for (int k = 0; k < updateArray.length; k++)
				printModel.setValueAt(updateArray[k], i, k);
		}		
	}


	/**
	 * <b>displayAccount</b> <br>
	 * 
	 * This method contains the main logic used to display a single account and its information.  If the user has not 
	 * initialized any bank accounts yet, then an error message occurs and the display account button is disabled.  If
	 * there are bank accounts within the system, the display account button is enabled and the user is prompted to enter
	 * the account number of the account they wish to display.  When the display account button is pushed, the method checks 
	 * to see if the given account number is valid.  If the account number is invalid, the user is given an error message.
	 * Otherwise, the method enters a for loop which iterates through accounts until it finds the user specified account.  If
	 * the user specified account is not found, an error message is display.  If the account is found, A String array called 
	 * parts is populated with the account information of the current account by evoking that account's toString method.  A 
	 * second String array called displayArray is populated with the contents of parts along with account type specific 
	 * information.  The information contained in updateArray is then inserted into the JTable Model displayModel.
	 */
	public void displayAccount() {

		if (numAccounts == 0) {
			displayAccButton.setEnabled(false);
			displayStatusLabel.setText("There are no accounts to display.");
		}else {
			displayStatusLabel.setText("Select account to display.");
			displayAccButton.setEnabled(true);

			displayAccButton.addActionListener(
					new ActionListener()
					{
						public void actionPerformed( ActionEvent e )
						{
							int displayAccNum = 0;
							try {
								displayAccNum = Integer.parseInt(displayAccNumText.getText());
								boolean accFound = false;
								for(int i=0; i<numAccounts; i++)
									if(accounts.get(i).getAccNumber() == displayAccNum) {

										String[] parts = accounts.get(i).toString().split("#");
										String[] displayArray = new String[9];

										if (accounts.get(i) instanceof SavingsAccount) {
											displayArray[0] = parts[0];
											displayArray[1] = "Savings";
											displayArray[2] = parts[1];
											displayArray[3] = parts[2];
											displayArray[4] = parts[3];
											displayArray[5] = parts[4];
											displayArray[6] = "NA";
											displayArray[7] = parts[5];
											displayArray[8] = parts[6];

										}else {
											displayArray[0] = parts[0];
											displayArray[1] = "Chequing";
											displayArray[2] = parts[1];
											displayArray[3] = parts[2];
											displayArray[4] = parts[3];
											displayArray[5] = parts[4];
											displayArray[6] = parts[5];
											displayArray[7] = "NA";
											displayArray[8] = "NA";
										}
										accFound = true;
										displayStatusLabel.setText("Account Found");
										for (int k = 0; k < displayArray.length; k++)
											displayModel.setValueAt(displayArray[k], 0, k);
									} else if (i == numAccounts -1 && accFound == false) {
										displayStatusLabel.setText("Account not found");
									}
							}catch (Exception displayAccException){
								displayStatusLabel.setText("Invalid Account Number");
								displayAccException.printStackTrace();
							}
						}
					});
		}
	}

	/**
	 * <b>updateAccount</b>
	 * This method contains the main logic used to update  the balance of an account.  If the user has not initialized any 
	 * bank accounts yet then an error message is displayed and the update account button is disabled.  If the user has 
	 * initialized a bank account, then the update account button is enabled and the user is prompted to enter the account 
	 * number of the account they wish to update as well as the account as the amount they with to deposit/withdrawal (positive
	 * number for deposit, negative for withdrawal). When the update account button is selected, the values entered by the user
	 * are retrieved from the text fields: updateAccNumText and updateAmountText.  If a valid account number is entered then the 
	 * findAccount method is called with the given account number as a parameter.  If findAccount returns an index other than -1
	 * (-1 means that the account wasn't found and an error message is displayed), then the account with the given index is updated
	 * with the given amount assuming the amount is valid (If the amount is invalid, an error message is displayed).  Once the account 
	 * is successfully updated, a completion message is displayed and the textFields are reset.
	 */
	public void updateAccount() {

		if (numAccounts == 0) {
			updateAccButton.setEnabled(false);
		}else {
			updateStatusLabel.setText("Update An Account");
			updateAccButton.setEnabled(true);
			updateAccButton.addActionListener(
					new ActionListener()
					{
						public void actionPerformed( ActionEvent e )
						{
							int updateAccNumber = 0;
							double updateAmount = 0;
							int index = 0;
							try {
								updateAccNumber = Integer.parseInt(updateAccNumText.getText());
								updateAmount = Double.parseDouble(updateAmountText.getText());
								index = findAccount(updateAccNumber);
								if (index == -1) {
									updateStatusLabel.setText("Account Not Found");
								}else {
									if (accounts.get(index).updateBalance(updateAmount) == true) {
										cardLayout.show(cardLayoutPanel, "1");
										welcomeLabel.setText("Account has been updated successfully!");
										updateAccNumText.setText("");
										updateAmountText.setText("");
									} else {
										updateStatusLabel.setText("Not enough funds to complete transaction");
									}
								}
							}catch (Exception updateAccException) {
								updateStatusLabel.setText("Invalid Account Number");
							}
						}
					});
		}
	}

	/**
	 * <b>findAccount</b> <br>
	 * This method is called by the displayAccount and updateAccount methods and is used to find a specific bank account.  The 
	 * method is given a value which represents that account number the user wishes to find, the program enters a for loop where 
	 * it checks the given account number against the accounts array.  If a match is found then the account index is returned.
	 * Otherwise the method returns a value of -1.
	 * 
	 * @param num Value of num is the account number that needs to be found within the existing ArrayList of accounts
	 * @return index Value of index is equal to -1 if no account with the given account number exists.
	 */
	public int findAccount(int num) {
		int index = -1;
		for(int i=0; i<numAccounts; i++) {
			if(accounts.get(i).accNumber == num)
				index = i;
		}
		return index;
	}

	/**
	 * <b>readDataListener</b> <br>
	 * This method is called upon when the Read Data From File menu button is selected.  This purpose of this method is to call upon the
	 * readDataFromFile method once the readFile button is selected.  Once readFileButton is selected, it is disabled to prevent the user
	 * from accidentally entering the same data twice.
	 */
	public void readDataListener() {
		readFileButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed( ActionEvent e )
					{
						readDataFromFile();
						readFileButton.setEnabled(false);
					}
				});
	}

	/**
	 * <b>readDataFromFile</b> <br>
	 * This method contains the main logic for reading data from a given file.  This method contains 5 main local variables.  Those are: 
	 * boolean accNumTaken which tracks whether an account number read of a file is already taken.  Scanner bankFile who's value is the 
	 * file the program is reading from.  It gets this file by evoking the openFile method.  String type which tracks the type of the given 
	 * bank account.  BankAccount acc which is an instance of BankAccount class and a temporary object to hold the values retrieved from the 
	 * file.  Boolean isOK which checks if the data read from the file is usable.  This method assigns a file to bankFile and sets the value 
	 * of type to the next line of bankFile.  The method checks to see if the first character in the current line is an "s" or a "c" as they
	 * denote the type of the account.  acc's value is set to the discovered bank account type.  acc's readFile() method is called and bankFile
	 * is used as a parameter.  If readFile returns false, the file contains bad data and an error message is displayed.  Otherwise the method
	 * checks to see if there is anymore room in accounts and checks to see if the account number is already taken.  If all data is excepted,
	 * then new account is added and the process repeats. Once everyline of the file has been entered, the file is closed and the method returns 
	 * true.
	 * 
	 * @return True or False depending on whether data could be successfully read from the specified text file.
	 */
	public boolean readDataFromFile() {
		boolean accNumTaken = false;
		Scanner bankFile = openFile();
		if(bankFile == null)
			return true;
		String type = new String();
		BankAccount acc = null;
		boolean isOK = true;




		while(bankFile.hasNext()) {
			type = bankFile.next();
			if(type.charAt(0) == 's') {
				acc = new SavingsAccount();
			}else if (type.charAt(0) == 'c') {
				acc = new ChequingAccount();
			}else 
				return false;

			isOK = acc.readFile(bankFile);
			if(isOK) {
				if (numAccounts >= sizeBank) {
					cardLayout.show(cardLayoutPanel, "1");
					welcomeLabel.setText("Cannot add more accounts. Bank is full!");
					return false;
				}
				cardLayout.show(cardLayoutPanel, "1");
				if (findAccount(acc.getAccNumber()) == -1) {
					accounts.add(numAccounts, acc);
					numAccounts++;
				}else {
					accNumTaken = true;
				}
				if (accNumTaken == false) {
					welcomeLabel.setText("Accounts Added Successfully!");
				}else {
					welcomeLabel.setText("One or more accounts could not be added due to account number(s) already being in use.");
				}
			}else {
				cardLayout.show(cardLayoutPanel, "1");
				welcomeLabel.setText("Error, file contains bad data.");
				return false;
			}
		}
		closeFile(bankFile);
		return true;
	}

	/**
	 * <b>openFile</b> <br>
	 * This method is called by readDataFromFile method and its purpose is to retrieve a specified file and return it to readDataFromFile.  A 
	 * Scanner variable file has its value set to a file specified within the program.  If the file cannot be opened, an error message is displayed
	 * otherwise the file is returned.
	 * 
	 * @return file Returns the file found from the given name.
	 */
	public Scanner openFile() {
		Scanner file = null;
		try {
			file = new Scanner(Paths.get("BankData.txt"));
		} catch (IOException ioException ) {
			cardLayout.show(cardLayoutPanel, "1");
			welcomeLabel.setText("Error, could not open file.");
		}
		return file;
	}

	/**
	 * <b>closeFile</b> <br>
	 * This method is called by readDataFromFile and its purpose is to close the file that is used to read account data from once all of its 
	 * data has been read.  This method takes the file as a parameter and closes it.
	 * 
	 * @param bankFile File that has had all of its data read and is ready to be closed
	 */
	public static void closeFile(Scanner bankFile) {
		if (bankFile != null)
			bankFile.close();
	}

	/**
	 * <b>addToCard2</b> <br>
	 * The purpose of this method is to initialize all of the components of card 2 (add account processing) of the cardLayout.  The components 
	 * include JLabels which prompt the user to enter account details, JRadioButtonsused to select account type, JTextFields to enter the account 
	 * data and a JButton used to submit/add the account details.  All of these components are added to card 2.
	 */
	public void addToCard2() {
		fNameLabel = new JLabel("First Name:");
		lNameLabel = new JLabel("Last Name:");
		phoneLabel = new JLabel("Phone Number:");
		emailLabel = new JLabel("Email:");
		accNumLabel = new JLabel("Account Number:");
		opBalLabel = new JLabel("Opening Balance:");
		monFeeLabel = new JLabel("Monthly Fee:");
		interRateLabel = new JLabel("Interest Rate (Must be decimal):");
		minBalLabel = new JLabel("Min Balance:");

		chequingRadioButton = new JRadioButton("Chequing", false);
		savingsRadioButton = new JRadioButton("Savings", false);

		ButtonGroup group = new ButtonGroup();           
		group.add(chequingRadioButton);
		group.add(savingsRadioButton);

		fNameText = new JTextField(10);
		lNameText = new JTextField(10);
		phoneText = new JTextField(10);
		emailText = new JTextField(10);
		accNumText = new JTextField(10);
		opBalText = new JTextField(10);
		monFeeText = new JTextField(10);
		interRateText = new JTextField(10);
		minBalText = new JTextField(10);

		addAccStatusLabel = new JLabel("Account has not been submitted");

		createAccButton = new JButton("Create Account");

		card2.add(fNameLabel);
		card2.add(lNameLabel);
		card2.add(phoneLabel);
		card2.add(emailLabel);
		card2.add(accNumLabel);
		card2.add(opBalLabel);
		card2.add(chequingRadioButton);
		card2.add(savingsRadioButton);
		card2.add(monFeeLabel);
		card2.add(interRateLabel);
		card2.add(minBalLabel);
		card2.add(fNameText, 1);
		card2.add(lNameText, 3);
		card2.add(phoneText, 5);
		card2.add(emailText, 7);
		card2.add(accNumText, 9);
		card2.add(opBalText, 11);
		card2.add(monFeeText, 15);
		card2.add(interRateText, 17);
		card2.add(minBalText, 19);
		card2.add(addAccStatusLabel);
		card2.add(createAccButton);

	}

	/**
	 * <b>addToCard3</b> <br>
	 * The purpose of this method is to initialize all of the components of card 3 (update account processing) of the cardLayout.  The components 
	 * include JLabels which prompt the user to enter account number and update amount, JTextFields to enter the account data and a JButton used 
	 * to submit the update amount.  All of these components are added to card 3.
	 */
	public void addToCard3() {
		updateAccNumLabel = new JLabel("Account Number");
		updateAmountLabel = new JLabel("Update Amount (Positive number for deposit, negative number for withdrawal.)");
		updateStatusLabel = new JLabel("There are no accounts to update.");

		updateAccButton = new JButton("Update Account");

		updateAccNumText = new JTextField(10);
		updateAmountText = new JTextField(10);

		card3.add(updateAccNumLabel);
		card3.add(updateAccNumText);
		card3.add(updateAmountLabel);
		card3.add(updateAmountText);
		card3.add(updateStatusLabel);
		card3.add(updateAccButton);
	}

	/**
	 * <b>addToCard4</b> <br>
	 * The purpose of this method is to initialize all of the components of card 4 (display account processing) of the cardLayout.  The components 
	 * include JLabels which prompt the user to enter account details, a JTextField to enter the account number, a JButton used to submit the 
	 * account number, and JTable to display the account details.  All of these components are added to card 4.
	 */
	public void addToCard4() {
		displayAccNumLabel = new JLabel("Account Number");
		displayStatusLabel = new JLabel("Select account to display.");
		displayAccButton = new JButton("Display Account");
		displayAccNumText = new JTextField(10);

		JTable displayTable = new JTable(displayModel); 

		JScrollPane scrollPane = new JScrollPane(displayTable);

		displayModel.addColumn("Account Number"); 
		displayModel.addColumn("Account Type"); 
		displayModel.addColumn("Name"); 
		displayModel.addColumn("Phone Number"); 
		displayModel.addColumn("Email"); 
		displayModel.addColumn("Balance"); 
		displayModel.addColumn("Fee"); 
		displayModel.addColumn("Interest Rate"); 
		displayModel.addColumn("Minimum Balance"); 

		JPanel displayPanel = new JPanel();
		displayPanel.add(displayAccNumLabel);
		displayPanel.add(displayAccNumText);

		displayPanel.add(displayAccButton);
		displayPanel.add(displayStatusLabel);

		displayModel.addRow(new Object[]{"", "", "", "", "", "", "", "", "", });

		card4.add(displayPanel);
		card4.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * <b>addToCard5</b> <br>
	 * The purpose of this method is to initialize all of the components of card 5 (print all accounts processing) of the cardLayout.  The only 
	 * component is a JTable used to display the details of every account in the system.  A default table model, a scroll pane, and column headers 
	 * are all added to card5.
	 */
	public void addToCard5() {

		JTable printTable = new JTable(printModel); 
		JScrollPane printScrollPane = new JScrollPane(printTable);

		printModel.addColumn("Account Number"); 
		printModel.addColumn("Account Type"); 
		printModel.addColumn("Name"); 
		printModel.addColumn("Phone Number"); 
		printModel.addColumn("Email"); 
		printModel.addColumn("Balance"); 
		printModel.addColumn("Fee"); 
		printModel.addColumn("Interest Rate"); 
		printModel.addColumn("Minimum Balance"); 

		card5.add(printScrollPane, BorderLayout.CENTER);
	}

	/**
	 * <b>addToCard6</b> <br>
	 * The purpose of this method is to initialize all of the components of card 6 (monthly update processing) of the cardLayout.  The only components 
	 * are a JButton used to call upon the monthlyAccountUpdate method of every account in the system and JLabel to promt the user.  Both components
	 * are added to card6.
	 */
	public void addToCard6() {
		monthlyUpdateButton = new JButton("Run Monthly Update");
		monthlyUpdateStatusLabel = new JLabel("");

		card6.add(monthlyUpdateButton);
		card6.add(monthlyUpdateStatusLabel);
	}

	/**
	 * <b>addToCard7</b> <br>
	 * The purpose of this method is to initialize all of the components of card 7 (read data from file processing) of the cardLayout.  The only 
	 * component is a JButton used to call upon readDataFromFile method.  The component is added to card7.
	 */
	public void addToCard7() {
		readFileButton = new JButton("Read Data From File");

		card7.add(readFileButton);
	}
}
