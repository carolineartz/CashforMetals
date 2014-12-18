/**
 * Transaction class represents a transaction
 * between a customer and CashforMetals
 * 
 * @author Caroline Artz
 * @version 1.0
 */

import java.util.Calendar;
import java.util.Scanner;

public class Transaction {
	// instance variables for metal weights
	private double wtPlatinum, wtGold, wtSilver;
	// instance variable for total value of transaction
	private double totalValue;
	// instance variables for transaction ID and date
	private long transactionID;
	private Calendar transactionDate;
	// instance Scanner object for user input
	Scanner input = new Scanner(System.in);

	// constructs transaction with a unique ID
	// sets transaction date
	public Transaction() {
		this.transactionID = UniqueIDFactory.generateUniqueID();
		this.transactionDate = Calendar.getInstance();

	}

	// calculates value of transaction
	public double calcValue() {
		this.totalValue = ((this.wtGold * Constants.GOLD_VALUE)
				+ (this.wtPlatinum * Constants.PLATINUM_VALUE) + (this.wtSilver * Constants.SILVER_VALUE));
		return totalValue;
	}

	// overloaded method
	// calculates value of transaction with bonus parameter
	public double calcValue(double bonus) {
		this.totalValue = ((this.wtGold * Constants.GOLD_VALUE)
				+ (this.wtPlatinum * Constants.PLATINUM_VALUE) + (this.wtSilver * Constants.SILVER_VALUE))
				* bonus;
		return totalValue;
	}

	// returns gold weight
	public double getGold() {
		return wtGold;
	}

	// returns platinum weight
	public double getPlatinum() {
		return wtPlatinum;
	}

	// returns silver weight
	public double getSilver() {
		return wtSilver;
	}

	// returns transaction date
	public Calendar getTransactionDate() {
		return this.transactionDate;
	}

	// returns transaction ID
	public long getTransactionID() {
		return this.transactionID;
	}

	// returns total value of offer
	public double getValue() {
		return this.totalValue;
	}

	// validates input of metal weights
	private double inputValidation(Scanner input) {
		double v;
		validLoop: while (true) {
			if (!input.hasNextDouble()) {
				do {
					input.next(); // to discard the input
					System.out.print("Please enter the weight as a number > ");
				} while (!input.hasNextDouble()); // end do/while
			} // end if

			v = input.nextDouble();
			while (v < 0) {
				System.out.print("Please enter a positive weight > ");
				continue validLoop;
			}// end while
			break;
		} // end while true
		return v;
	}

	// sets weight of gold
	public void setGold(Scanner input) {
		System.out.print("Enter the weight of gold > ");
		wtGold = inputValidation(input);
	}

	// sets weight of platinum
	public void setPlatinum(Scanner input) {
		System.out.print("Enter the weight of platinum > ");
		wtPlatinum = inputValidation(input);
	}

	// sets weight of silver
	public void setSilver(Scanner input) {
		System.out.print("Enter the weight of silver > ");
		wtSilver = inputValidation(input);
	}

	// return String representation of Transaction object
	@Override
	// indicates that this method overrides a superclass method
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append("Transaction ID: " + transactionID + NEW_LINE);
		result.append("Gold Weight: "
				+ StringFormatter.formatTwoDecimals(wtGold) + NEW_LINE);
		result.append("Platinum Weight: "
				+ StringFormatter.formatTwoDecimals(wtPlatinum) + NEW_LINE);
		result.append("Silver Weight: "
				+ StringFormatter.formatTwoDecimals(wtSilver) + NEW_LINE);
		result.append("Total Value: " + StringFormatter.formatMoney(totalValue));
		result.append(NEW_LINE);
		return result.toString();
	}

}
