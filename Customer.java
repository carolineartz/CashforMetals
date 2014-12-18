/**
 * CashforMetals Customer superclass represents
 * Customers in general: general descriptives,
 * account, and transactions
 * 
 * @author Caroline Artz
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
	// instance variables for the Customer class
	private String name;
	private long custID;
	private String address;
	//composed
	private Account account;
	// stores transactions for a single customer
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	Scanner input = new Scanner(System.in); // Scanner object for user input

	// constructor takes customer name
	public Customer(String name) {

		// validate input
		while (true) {
			if (name.isEmpty()) {
				System.out.print("\nEnter the customer name > ");
				name = input.nextLine();
			} else {
				// set and validate address
				System.out.print("\nEnter the customer address > ");
				this.address = input.nextLine();
				if (address.isEmpty()) {
					continue;
				} else
					break;
			}// end else
		}// end while(true)

		this.name = name;

		// generate and set unique customer ID
		this.custID = UniqueIDFactory.generateUniqueID();
		// creates an account for the new customer
		createAccount(custID);

	}// end constructor Customer

	// calls account constructor to create
	// a new account for the customer
	public void createAccount(long custID) {
		custID = this.custID;
		//via composition
		this.account = new Account(custID);
	}

	// executes a transaction for the customer
	public Transaction makeTransaction(Customer customer) {
		Transaction transaction = new Transaction();
		// sets metal weights to user input
		transaction.setGold(input);
		transaction.setPlatinum(input);
		transaction.setSilver(input);
		// returns the transaction value
		Value(transaction);
		// deposits the value into the customer's account
		account.makeDeposit(transaction.getValue());
		// adds the transaction to the customer's transactions
		transactions.add(transaction);
		return transaction;
	}

	// returns the value of the transaction
	public double Value(Transaction transaction) {
		return transaction.calcValue();
	}

	// returns customer's name
	public String getName() {
		return this.name;
	}

	// returns customer's ID
	public long getCustID() {
		return this.custID;
	}

	// returns customer's address
	public String getAddress() {
		return this.address;
	}

	// sets customer address
	public void setAddress(String address) {
		address = this.address;
	}

	// return's customer's account
	public Account getAccount() {
		return this.account;
	}

	// returns String representation of Customer object
	@Override
	// indicates that this method overrides a superclass method
	public String toString() {

		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append("Customer Name: " + name + NEW_LINE);
		result.append("Customer ID: " + custID + NEW_LINE);
		result.append("Customer Address: " + address + NEW_LINE);

		StringBuilder arrayresult = new StringBuilder();
		for (Transaction value : transactions) {
			arrayresult.append(value + "\n");
		}

		return result.toString() + "\n" + account + arrayresult.toString();
	}

}// end Customer class
