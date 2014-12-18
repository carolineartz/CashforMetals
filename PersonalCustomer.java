/**
 * Personal Customer class extends Customer; 
 * adds additional attributes specific
 * to personal customers
 * 
 * @author Caroline Artz
 * @version 1.0
 */

public class PersonalCustomer extends Customer {
	// instance variables
	private String homePhone;
	private String workPhone;

	// constructs personal customer
	// calls superclass constructor
	public PersonalCustomer(String name) {
		super(name);
		this.homePhone = "";
		this.workPhone = "";
	}

	// returns home phone
	public String getHomePhone() {
		return this.homePhone;
	}

	// returns work phone
	public String getWorkPhone() {
		return this.workPhone;
	}

	// sets home phone
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	// sets work phone
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	// return String representation of PersonalCustomer object
	@Override
	// indicates that this method overrides a superclass method
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append(super.toString());
		result.append("Home Phone: " + homePhone + NEW_LINE);
		result.append("Work Phone: " + workPhone + NEW_LINE);

		return result.toString();
	}

}
