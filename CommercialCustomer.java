/**
 * Commercial Customer class extends Customer;
 * adds additional attributes and
 * function behavior specific to commercial customers
 * 
 * @author Caroline Artz
 * @version 1.0
 */

public class CommercialCustomer extends Customer {
	// instance variables
	private String contact;
	private String contactPhone;
	private double bonus;

	// constructs commercial customer
	// calls superclass constructor
	public CommercialCustomer(String name) {
		super(name);
		this.contact = "";
		this.contactPhone = "";
		this.bonus = Constants.COMMERCIAL_CUSTOMER_BONUS_RATE;
	}

	// returns contact name
	public String getContact() {
		return this.contact;
	}

	// returns contact phone
	public String getContactPhone() {
		return this.contactPhone;
	}

	// sets contact name
	public void setContact(String contact) {
		this.contact = contact;
	}

	// sets contact phone
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	// return String representation of CommercialCustomer object
	@Override
	// indicates that this method overrides a superclass method
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append(super.toString());
		result.append("Contact Person: " + contact + NEW_LINE);
		result.append("Contact Person Phone: " + contactPhone + NEW_LINE);

		return result.toString();
	}

	// returns the value of the customer's current
	// transaction;
	// overrides superclass Value() to include the commercial customer bonus in
	// the transaction value calcs
	@Override
	public double Value(Transaction transaction) {
		return transaction.calcValue(this.bonus);
	}

}
