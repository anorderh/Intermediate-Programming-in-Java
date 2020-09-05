/**
 * (Program 5 - Banking Application) Customer Class
 * Class defining the Object of Customer, keeping tabs on customerID, name, and accountsHeld
 * @author Anthony Norderhaug
 * @date 3/20/2020
 */
public class Customer {

    final int customerID;
    String customerName;
    int accountsHeld;

    /**
     * Customer() constructor for initializing with no String specified.
     */
    public Customer() {
        customerID = Bank.customers++;
        customerName = "Customer" + customerID;
        accountsHeld = 0;
    }

    /**
     * Customer() constructor for initializing with String specified. customerName is assigned with String argument.
     *
     * @param name                  String name, user-input String for name
     */

    public Customer(String name) {
        customerID = Bank.customers++;
        customerName = name;
        accountsHeld = 0;
    }

    /**
     * getCustomerID() gets
     * @return                  int customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * getCustomerName() gets
     * @return                  String customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * setCustomerName() sets
     * @param name              String specified by user for naming
     */
    public void setCustomerName(String name) {
        customerName = name;
    }

    /**
     * getCustomerAccounts() gets
     * @return                  int accountsHeld
     */
    public int getCustomerAccounts() {
        return accountsHeld;
    }

    /**
     * setCustomerAccounts() sets
     * @param quantity          int specified by user to change accountsheld
     */
    public void setCustomerAccounts(int quantity) {
        accountsHeld = quantity;
    }

    /**
     * toString() override Object class toString(), hardcoded to specify customerName and customerID
     * @return                  "Customer: " + getCustomerName() + " | Customer ID: " + getCustomerID()
     */
    @Override
    public String toString() {
        return "Customer: " + getCustomerName() + " | Customer ID: " + getCustomerID();
    }


}
