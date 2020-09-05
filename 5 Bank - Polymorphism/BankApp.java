/**
 * (Program 5 - Banking Application) BankApp Class (Main File)
 * Driver for Program 5 Banking Application that stores lists of all bank deposits & customers. Processes user input
 * through switch statement with various class and instance calls.
 * @author Anthony Norderhaug
 * @date 3/20/2020
 */
import java.util.Scanner;
import java.util.ArrayList;

public class BankApp {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Customer> customerRegistry = new ArrayList<Customer>();
    static ArrayList<Deposit> allDeposits = new ArrayList<Deposit>();
    static Bank UnionBank = new Bank();
    static String bankName = "The Local Union";

    /**
     * Main method that prompts user created menu and processes input, calling respective case methods
     * @param args
     */

    public static void main(String[] args) {
        BankApp app = new BankApp(); // instancing BankApp class
        int menuInput;
        boolean revert = true;
        boolean validChoice;

        // prompt asking for and processing user input

        System.out.println(bankName);
        do { //do-while loop dependent on if exit is desired
            app.printMenu();
            do { //do-while loop for processing info, dependent on if input is valid
                menuInput = Integer.parseInt(scan.nextLine());
                validChoice = true;

                switch (menuInput) {
                    case 1:
                        app.createAccount();
                        break;
                    case 2:
                        app.addSecondaryHolder();
                        break;
                    case 3:
                        app.transferFunds();
                        break;
                    case 4:
                        app.accountRemoval();
                        break;
                    case 5:
                        app.accountInfo();
                        break;
                    case 6:
                        app.customerInfo();
                        break;
                    case 7:
                        app.updateEndOfMonth();
                        break;
                    case 8: // update to next month
                        UnionBank.month = UnionBank.nextMonth();
                        break;
                    case 9: // month info
                        System.out.println("Current Month: " + UnionBank.getMonth() + "\nMonth End Flag: " + UnionBank.getMonthEnd());
                        break;
                    case 0: // exit application
                        System.out.println("Thanks for using the bank application");
                        revert = false;
                        break;
                    default: // invalid input
                        validChoice = false;
                        System.out.println("Enter a relevant option: ");
                }
            } while (!validChoice);
        } while (revert);
    }

    /**
     * accountVerify() takes in an int representing specified accountID and checks if ID is associated with any
     * pre-standing accounts.
     * @param ID                    User-input int representing accountID
     * @return                      Account identified (null if no accounts found)
     */
    public static Deposit accountVerify(int ID) {
        for (Deposit account: allDeposits) {
            if (account.getAccountID() == ID) {
                return account;
            }
        }
        return null;
    }

    /**
     * customerVerify() takes in an int representing specified customerID and checks if ID is associated with any
     * customers in customerRegistry
     * @param ID                    User-input int representing customerID
     * @return                      Customer identified (null if no customers found)
     */
    public static Customer customerVerify(int ID) {
        for (Customer customer : customerRegistry) {
            if (ID == customer.getCustomerID()) {
                return customer;
            }
        }
        return null;
    }

    /**
     * printMenu() prints out entire prompt of options for user to follow when using the application.
     */
    public void printMenu() {
        System.out.println();
        System.out.println("1. Open a new account.");
        System.out.println("2. Add a second holder to an existing account.");
        System.out.println("3. Deposit/Withdraw");
        System.out.println("4. Delete a current account.");
        System.out.println("5. Print details about a account.");
        System.out.println("6. Print details about a customer.");
        System.out.println("7. Update to end month [reset withdrawals & add interest].");
        System.out.println("8. Update to next month.");
        System.out.println("9. Print details about month.");
        System.out.println("0. Exit");
        System.out.println("\nEnter a relevant option:");
    }

    /**
     * createAccount() processes input and creates either a Checking or Savings instance. If no valid choice is
     * detected, prompt is outputted and returns to main menu.
     */
    public void createAccount() {
        int input;

        System.out.println("1. Deposit Account: Checking\n2. Deposit Account: Savings\n" +
                "0. Return to the main menu\nEnter the type of account you wish to open: ");
        input = Integer.parseInt(scan.nextLine());
        if (input == 1) {
            allDeposits.add(new Checking());
        } else if (input == 2) {
            allDeposits.add(new Savings());
        } else {
            System.out.println("Exiting to main menu");
        }
    }

    /**
     * addSecondaryHolder() processes int input to identify a pre-standing account. If successful (not null), this
     * account undergoes addAccountHolder() in Deposit class and details of account and holders are outputted.
     */
    public void addSecondaryHolder() {
        System.out.println("Enter your account ID: ");
        Deposit account = accountVerify(Integer.parseInt(scan.nextLine()));

        if (account != null) {
            account.addAccountHolder();
            System.out.println("For Account ID: " + account.getAccountID());
            System.out.println("First Holder: " + account.firstHolder);
            System.out.println("Second Holder: " + account.secondHolder);
        }
    }

    /**
     * transferFunds() processes int input to identify a pre-standing account. If successful (not null), further input
     * is processed to determine action (deposit, withdraw, exit).
     */
    public void transferFunds() {
        System.out.println("Enter your account ID: ");
        Deposit account = accountVerify(Integer.parseInt(scan.nextLine()));

        if (account != null) {
            System.out.println("1. Deposit\n2. Withdraw\n0. Return to Main Menu");
            int menuInput = Integer.parseInt(scan.nextLine());;

            if (menuInput == 1) {
                System.out.println("Enter the amount you wish to deposit:");
                account.depositMoney(Integer.parseInt(scan.nextLine()));
            } else if (menuInput == 2) {
                System.out.println("Enter the amount you wish to withdraw:");
                if (account instanceof Checking) {
                    ((Checking) account).withdrawMoney(Integer.parseInt(scan.nextLine()));
                } else if (account instanceof Savings) {
                    ((Savings) account).withdrawMoney(Integer.parseInt(scan.nextLine()));
                }
            } else {
                System.out.println("Exiting to main menu");
            }
        }
    }

    /**
     * accountRemoval() processes int input to identify a pre-standing account. If successful, deleteAccount() of
     * respective Deposit instance is called and is subsequently removed from allDeposits list.
     */
    public void accountRemoval() {
        System.out.println("Enter your account ID: ");
        Deposit account = accountVerify(Integer.parseInt(scan.nextLine()));

        if (account != null) {
            allDeposits.remove(account.deleteAccount());
        }
    }

    /**
     * accountInfo() processes int input to identify a pre-standing account. If successful, the Account object is
     * printed, utilizing its overridden toString() method.
     */
    public void accountInfo() {
        System.out.println("Enter your account ID: ");
        Deposit account = accountVerify(Integer.parseInt(scan.nextLine()));

        if (account != null) {
            System.out.println(account);
        }
    }

    /**
     * customerInfo() processes int input to identify a pre-existing customer. If successful, the Customer object is
     * printed, utilizing its overridden toString() method.
     */
    public void customerInfo() {
        System.out.println("Enter your customer ID: ");
        Customer customer = customerVerify(Integer.parseInt(scan.nextLine()));

        if (customer != null) {
            System.out.println(customer);
        }
    }

    /**
     * updateEndOfMonth() calls Bank member endOfMonth() and iterates through all existing Deposits, calling addInterest()
     * respectively if Deposit object is either Checking or Savings instance
     */
    public void updateEndOfMonth() {
        UnionBank.endOfMonth();
        for (Deposit account : allDeposits) {
            if (account instanceof Checking) {
                ((Checking) account).addInterest();
                account.resetWithdrawals();
            }

            if (account instanceof Savings) {
                ((Savings) account).addInterest();
                account.resetWithdrawals();
            }
        }
    }

    public static String getInfo() {
        return "Anthony Norderhaug, Program 5";
    }
}
