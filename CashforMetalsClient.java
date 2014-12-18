/**
 * CashforMetalsClient.java
 * 
 * Runs the CashforMetalsClient program. Interacts with a 
 * user to create customers, accounts and transactions
 *
 * @author Caroline Artz
 * @version 1.0
 */

import java.util.*;
//did not use use BigDecimal
//import java.math.BigDecimal;

public class CashforMetalsClient {

	// Data structure to store customers
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		Customer customer;
		Transaction transaction;
		double withdrawalAmount = 0;

		boolean finished = false;

		while (finished == false) {
			// Menu Display and Get user input
			int inputInt = 0;
			while (inputInt == 0) {
				inputInt = displayMenuAndGetInput();

				// if the input is out of range
				if ((inputInt < 1) || (inputInt > 8)) {
					System.out.println("\nThe input is out of range!");
					System.out.println();
					inputInt = 0;
				}
			} // end while

			// switch to correspondence function
			switch (inputInt) {
			case 1:
				customer = createPersonalCustomer();
				System.out
						.println("\nThe Personal customer has been created: \n"
								+ customer.toString());
				customers.add(customer);
				break;
			case 2:
				customer = createCommercialCustomer();
				System.out
						.println("\nThe Commercial customer has been created: \n"
								+ customer.toString());
				customers.add(customer);
				break;
			case 3:
				transaction = recordTransaction();
				if (transaction != null)
					System.out.println("\nThe Transaction has been created: \n"
							+ transaction.toString());
				else
					System.out.println("\nThe ID could not be found. \n");
				break;
			case 4:
				withdrawalAmount = makeWithdrawal();
				if (withdrawalAmount > 0)
					System.out.println("\nAmount withdrawn from this account: "
							+ StringFormatter.formatMoney(withdrawalAmount)
							+ "\n");
				else if (withdrawalAmount == -2) // added check to not allow
													// transactions greater than
													// account balance
					System.out
							.println("\nInsufficient funds. Withdrawal canceled. \n");
				else
					System.out.println("\nThe ID could not be found. \n");
				break;
			case 5:
				displayCustomer();
				break;
			case 6:
				displayCustomerSummary();
				break;
			case 7:
				displayGrandSummary();
				break;
			case 8:
				// exit
				finished = true;
				break;
			default:
				System.out.println("Invalid Input!");
				System.out.println("");
				break;
			} // end switch
		} // end while

	}

	// --------------------------------------------------------------------------------------------

	public static int displayMenuAndGetInput() {

		int inputInt = -1;

		// display menu and get user input
		// added try/catch block for non-integer validation
		boolean done = false;
		while (!done) {
			// Menu Display
			try {
				System.out.println("***\t\t\t\t\t\t\t***");
				System.out
						.println("***\tWelcome to Cash for Metals Calculator!!!\t***");
				System.out.println("***\t\t\t\t\t\t\t***");
				System.out.println(" 1.  Create Personal Customer");
				System.out.println(" 2.  Create Commercial Customer");
				System.out.println(" 3.  Record Transaction");
				System.out.println(" 4.  Make Withdrawal");
				System.out.println(" 5.  Display Customer");
				System.out.println(" 6.  Display Customer Summary");
				System.out.println(" 7.  Display Grand Summary");
				System.out.println(" 8.  Exit");
				System.out.println("");

				// get valid input from user
				System.out.print("Please input your choice (1-8): ");
				inputInt = input.nextInt();
				done = true;
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("\nTry again by entering a number. \n");
			}// end catch
		}// end while(!done)

		return inputInt;
	}// end displayMenuAndGetInput()

	// creates a new PersonalCustomer
	// returns customer
	public static Customer createPersonalCustomer() {
		// new personal customer object
		PersonalCustomer customer = new PersonalCustomer(input.nextLine());
		// gets, validates, and sets personal customer attributes
		System.out.print("\nEnter the customer home phone > ");
		customer.setHomePhone(input.nextLine());
		while (true) {

			if (customer.getHomePhone().isEmpty()) {
				System.out.print("\nEnter the customer home phone > ");
				customer.setHomePhone(input.nextLine());
			} else {
				System.out.print("\nEnter the customer work phone > ");
				customer.setWorkPhone(input.nextLine());
				if (customer.getWorkPhone().isEmpty()) {
					continue;
				} else {
					break;
				}// end else
			}// end else
		}// end while(true)
		return customer;

	}// end createPersonalCustomer()

	// creates a new CommercialCustomer
	// returns customer
	public static Customer createCommercialCustomer() {
		// new commercial customer object
		CommercialCustomer customer = new CommercialCustomer(input.nextLine());
		// gets, validates, and sets commercial customer attributes
		System.out.print("\nEnter the customer contact person > ");
		customer.setContact(input.nextLine());
		while (true) {

			if (customer.getContact().isEmpty()) {
				System.out.print("\nEnter the customer contact person > ");
				customer.setContact(input.nextLine());
			} else {
				System.out
						.print("\nEnter the customer contact person phone > ");
				customer.setContactPhone(input.nextLine());
				if (customer.getContactPhone().isEmpty()) {
					continue;
				} else {
					break;
				}// end else
			}// end else
		}// end while(true)
		return customer;
	}// end createCommercialCustomer

	// Create a new Transaction
	public static Transaction recordTransaction() {
		System.out
				.print("\nEnter the customer ID to create the transaction > ");
		// validates existence of customer and input format
		boolean done = false;
		while (!done) {
			try {
				// checks if customer exists
				Customer customer = findCustomer(input.nextLong());
				if (customer != null) {
					done = true;
					// creates, records and returns new transaction
					return customer.makeTransaction(customer);
				} else
					return null;

			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.print("Try again using a number > ");
			}// end catch
		}// end while(!done)

		return null;// if customer didn't exist
	}// end recordTransaction()

	// Make a withdrawal
	public static double makeWithdrawal() {
		System.out.print("\nEnter the customer ID to make a withdrawal > ");
		// validates input, available funds and input format
		boolean validate = false;
		while (!validate) {
			try {
				// checks if customer exists
				Customer customer = findCustomer(input.nextLong());
				if (customer != null) {
					boolean done = false;
					while (!done) {

						try {
							// sets amount to withdraw from account
							System.out.print("Enter the amount to withdraw > ");
							double amount = input.nextDouble();
							// validates available funds
							if (amount > customer.getAccount().getBalance()) {
								done = true; // break from loop
								validate = true; // break outer loop
								return -2; // insufficient funds available
							} else {
								done = true; // break from loop
								validate = true; // break outer loop
								return customer.getAccount().makeWithdrawal(
										amount);
							}// end else
						} catch (InputMismatchException e) {
							input.nextLine();
							System.out
									.println("Try again. Do not enter a $ sign with the amount.");
						}// end catch
					} // end while(!done)

				} else
					validate = true;
				return -1; // ID not found
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.print("Try again using a number > ");
			}// end catch
		}// end while (!validate)
		return -1; // ID not found
	}// end makeWithdrawal()

	// Displays customer summary with
	// customer name, customer, ID,
	// account details, transaction details
	public static void displayCustomer() {
		System.out.print("\nEnter the customer ID to view a summary > ");
		// validates input format
		boolean validate = false;
		while (!validate) {
			try {
				// checks if customer exists
				Customer customer = findCustomer(input.nextLong());
				if (customer != null)
					System.out.print(customer + "\n");
				else
					System.out.println("\nThe ID could not be found. \n");
				validate = true; // break loop
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.print("Try again using a number > ");
			}// end catch
		}// end while (!validate)

	}// end displayCustomer()

	// displays total number of customers
	// display the sum total value of all accounts
	public static void displayCustomerSummary() {
		// finds number of elements in customer arraylist
		System.out.println("\nTotal number of customers: " + customers.size());
		// traverses customer ArrayList and adds account balance
		// to customer total
		double customerTotal = 0;
		for (Customer customer : customers) {
			customerTotal = customerTotal + customer.getAccount().getBalance();
		}// end for
			// prints the summary info
		System.out.println("Total value of all accounts: "
				+ StringFormatter.formatMoney(customerTotal) + "\n");

	}// end displayCustomerSummary()

	// Display all customers, accounts and transactions
	public static void displayGrandSummary() {
		// traverses customer ArrayList and prints
		// string representation of customer info for each customer
		for (Customer customer : customers) {
			System.out.print(customer + "\n");
		}// end for
	}// end displayGrandSummary

	// Helper method to determine if a customer exists
	public static Customer findCustomer(long customerID) {
		// traverses customer ArrayList checking input customer ID against
		// IDs of existing customers
		// returns the customer if exists, or null
		for (Customer customer : customers) {
			if (customer.getCustID() == customerID) {
				return customer;
			}// end if
		}// end for
		return null;
	}// end findCustomer()

}// end class CashforMetalsClient