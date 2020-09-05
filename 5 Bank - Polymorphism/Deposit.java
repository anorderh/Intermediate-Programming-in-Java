/**
 * (Program 5 - Banking Application) Deposit Abstract Class
 * Class partially implementing interface Account, keeping tabs on account balance, withdrawals, ID, holders. Additionally,
 * implements several methods regarding manipulation of these values.
 * @author Anthony Norderhaug
 * @date 3/20/2020
 */
public abstract class Deposit implements Account {
    int accountID;
    Customer firstHolder;
    Customer secondHolder;
    int balance;
    int withdrawals;

    /**
     * Deposit() constructor that initializes ID, balance, withdrawals, firstHolder through checkCustomer(), and outputs
     * prompt explaining program behavior
     */

    public Deposit() {
        accountID = Bank.accounts++;
        firstHolder = checkCustomer();
        balance = 0;
        withdrawals = 0;
        System.out.println("A new account was created with account ID: " + accountID);
        System.out.println("The first holder is: " + firstHolder);
    }

    /**
     * getAccountID() gets
     * @return                  int accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * getAccountID() sets
     * @param newID             int specified by user to change accountID
     */
    public void setAccountID(int newID) {
        accountID = newID;
    }

    /**
     * getBalance() gets
     * @return                  int balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * setBalance() sets        int specified by user to change balance to
     * @param newBalance
     */
    public void setBalance(int newBalance) {
        balance = newBalance;
    }

    /**
     * getWithdrawals() gets
     * @return                  int withdrawals
     */
    public int getWithdrawals() {
        return withdrawals;
    }

    /**
     * setWithdrawals() sets
     * @param quantity          int specified by user to represent quantity
     */
    public void setWithdrawals(int quantity) {
        withdrawals = quantity;
    }

/*  // unused methods but required in Zybooks?
    public int getMinimumFees() {
        // inquire more
    }

    public void setMinimumFees(int feeDesired) {
        // idk
    }
*/

    /**
     * getFirstHolder() gets
     * @return                  Customer firstHolder
     */
    public Customer getFirstHolder() {
        return firstHolder;
    }

    /**
     * setFirstHolder sets
     * @param newHolder         Customer object representing new account firstHolder
     */
    public void setFirstHolder(Customer newHolder) {
        firstHolder = newHolder;
    }

    /**
     * getSecondHolder() gets
     * @return                  Customer secondHolder
     */
    public Customer getSecondHolder() {
        return secondHolder;
    }

    /**
     * setSecondHolder() sets
     * @param newHolder         Customer object representing new account secondHolder
     */
    public void setSecondHolder(Customer newHolder) {
        secondHolder = newHolder;
    }

    /**
     * depositMoney() ensures deposit validity and adds onto pre-existing balance.
     * @param deposit           int representing desired deposit
     * @return                  true if successful, false if deposit is invalid
     */
    public boolean depositMoney(int deposit) {
        if (deposit < 0) {
            System.out.println("Invalid deposit");
            return false;
        } else {
            balance += deposit;
            System.out.println("Updated Balance: " + balance);
        }
        return true;
    }

    /**
     * checkCustomer() inquires whether customer is pre-existing or not. Depending on input, pre-existing Customer object
     * is identified or new object is created. Customer object is added to BankApp's customerRegistry and returned to call.
     * @return                  Customer object identified
     */
    public Customer checkCustomer() {
        int customerID;
        String customerName;
        Customer newCustomer;

        System.out.println("Are you an existing customer? [Y: Yes; N: No]");

        if (BankApp.scan.nextLine().charAt(0) == 'Y') {
            System.out.println("Enter Customer ID: ");
            customerID = Integer.parseInt(BankApp.scan.nextLine());

            for (Customer customer : BankApp.customerRegistry) {
                if (customerID == customer.getCustomerID()) {
                    return customer;
                }
            }
            System.out.println("There was no record of the ID. A new ID will be created");
        }
        System.out.println("Enter your name: ");
        customerName = BankApp.scan.nextLine();

        if (customerName.length() == 0) {
            newCustomer = new Customer();
        } else {
            newCustomer = new Customer(customerName);
        }

        BankApp.customerRegistry.add(newCustomer);
        return newCustomer;
    }

    /**
     * addAccountHolder() uses checkCustomer() to pull out and assign a Customer object to secondHolder
     * @return                  boolean true if successful (no implementation for false)
     */
    public boolean addAccountHolder() {
        secondHolder = checkCustomer();
        return true;
    }

    /**
     * updateAccount() processes Customer argument and uses setFirstHolder to assign towards Deposit's firstHolder
     * @param customer          Customer argument representing user-specified Customer
     * @return                  boolean true if successful (no implementation for false)
     */
    public boolean updateAccount(Customer customer) {
        setFirstHolder(customer);
        return true;
    }

    /**
     * updateAccount() processes Customer and int arguments, using int to discern whether Customer is assigned to
     * firstHolder or secondHolder
     * @param customer          Customer argument representing user-specified Customer
     * @param specify           int argument representing user-specified decision
     * @return                  boolean true if successful (if specify is not 1 or 2, false is returned)
     */
    public boolean updateAccount(Customer customer, int specify) {
        if (specify == 1) {
            firstHolder = customer;
        } else if (specify == 2) {
            secondHolder = customer;
        } else {
            return false;
        }

        return true;
    }

    /**
     * deleteAccount() processes int input representing customerID and identifies all accounts whose firstHolders have
     * the same customerID. Focusing on first account identified, user is prompted whether or not they want account
     * deleted. If desired, account is returned to method call.
     * @return                  Account object representing account desired to be deleted
     */
    public Account deleteAccount() {
        int customerID;
        System.out.println("Are you sure you want to delete your account?");

        if (BankApp.scan.nextLine().charAt(0) == 'Y') {
            System.out.println("Enter your Customer ID: ");
            customerID = Integer.parseInt(BankApp.scan.nextLine());;

            for (Deposit account : BankApp.allDeposits) {
                if (customerID == account.getFirstHolder().getCustomerID()) {
                    System.out.println("Do you want to delete your savings account with AccID: "
                            + accountID + " ? ");
                    ;
                    if (BankApp.scan.nextLine().charAt(0) == 'Y') {
                        return account;
                    } else {
                        System.out.println("No accounts were deleted");
                        return null;
                    }
                }
            }
            System.out.println("Customer ID invalid");
            return null;
        }
        System.out.println("No accounts were deleted");
        return null;
    }

    /**
     * resetWithdrawals() calls setWithdrawals() to reset withdrawals field back to 0
     */
    public void resetWithdrawals() {
        setWithdrawals(0);
    }

}
