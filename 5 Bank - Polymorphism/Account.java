/**
 * (Program 5 - Banking Application) Account Interface
 * Interface specifying methods to be implemented by deriving classes. Pertains to manipulation of account.
 * @author Anthony Norderhaug
 * @date 3/20/2020
 */
public interface Account {
    boolean addAccountHolder();
    boolean updateAccount(Customer customer);
    boolean updateAccount(Customer customer, int accountQuantity);
    Account deleteAccount();
    int getAccountID();

}
