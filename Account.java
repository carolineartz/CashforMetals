/**
 * CashforMetals Account
 * represents a single 
 * Customer's account
 * 
 * @author Caroline Artz
 * @version 1.0
 */
import java.util.Calendar;

public class Account {
	// instance variables for a customer's account
	private long accountNumber;
	private double balance;
	private double interestRate;
	private Calendar dateOpened;

	// constructs a customer's account
	// sets attributes to current date,
	// final and default amounts
	public Account(long custID) {
		this.accountNumber = UniqueIDFactory.generateUniqueID();
		this.balance = Constants.DEFAULT_ACCOUNT_BALANCE;
		this.interestRate = Constants.DEFAULT_INTEREST_RATE;
		this.dateOpened = Calendar.getInstance();
	}

	// returns account number
	public long getAccountNumber() {
		return this.accountNumber;
	}

	// returns balance
	public double getBalance() {
		return this.balance;
	}

	// returns date opened
	public Calendar getDateOpened() {
		return this.dateOpened;
	}

	// returns interest rate
	public double getInterestRate() {
		return this.interestRate;
	}

	// sets balance to amount after deposit
	public void makeDeposit(double deposit) {
		this.balance = this.balance + deposit;
	}

    // calcs & sets new balance
	// and returns back the withdrawal amount
	public double makeWithdrawal(double amount) {
		this.balance = balance - amount;
		return amount;
	}

	// return String representation of Account object
	@Override
	// indicates that this method overrides a superclass method
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append("Account Number: " + accountNumber + NEW_LINE);
		result.append("Balance: " + StringFormatter.formatMoney(balance)
				+ NEW_LINE);
		result.append("Interest Rate: "
				+ StringFormatter.formatTwoDecimals(interestRate) + NEW_LINE);
		result.append("Date Opened: " + StringFormatter.formatDate(dateOpened)
				+ "\n");
		result.append(NEW_LINE);
		return result.toString();
	}

}
