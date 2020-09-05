/**
 * (Program 5 - Banking Application) Savings Class
 * Class extending Deposit that has an interest of 4% but limit of 3 withdrawals.
 * @author Anthony Norderhaug
 * @date 3/20/2020
 */
public class Savings extends Deposit {
    final int INTEREST = 4;

    /**
     * Savings() constructor just calling Deposit() constructor
     */
    public Savings() {
        super();
    }

    /**
     * withdrawMoney() verifies that withdrawal is valid. If true, withdrawal is subtracted from balance, withdrawals
     * count is incremented, and update message regarding balance and remaining withdrawals is outputted
     * @param withdrawal                    int representing desired withdrawal
     * @return                              boolean true if successful (false if withdrawal is invalid)
     */
    public boolean withdrawMoney(int withdrawal) {
        if (withdrawal > balance) {
            System.out.println("Not Enough Balance");
            return false;
        }
        else if (withdrawal < 0) {
            System.out.println("Invalid Amount");
            return false;
        }
        else if (withdrawals >= 3) {
            System.out.println("Withdrawals Limit Exceeded");
            return false;
        }

        balance -= withdrawal;
        withdrawals++;
        System.out.println("Updated Balance: " + balance);
        System.out.println("Remaining Withdrawals: " + (3 - withdrawals));
        return true;
    }

    /**
     * calcInterest() calculates interest through multiplying balance by interest and dividing by 1200.0. (interest is
     * percent, divided by 100 becomes applicable decimal, divided by 12 becomes monthly applicable decimal, multiplied
     * by balance yields gained interest.
     * @return                  int form of double interest (rounded down as desired in Zybooks)
     */
    public int calcInterest() {
        double interest = (getBalance() * INTEREST) / 1200.0;
        return (int) interest;
    }

    /**
     * addInterest() adds interest yielded from calcInterest() onto balance if isMonthEnd is true.
     * @return                  boolean true if isMonthEnd is true (false if isMonthEnd is false)
     */
    public boolean addInterest() {
        if (Bank.isMonthEnd) {
            balance += calcInterest();
            return true;
        }

        return false;
    }

    /**
     * toString() overrides Object class member and specifies specifically account type, balance, withdrawals, and
     * potential interest
     * @return                  hard-coded information, see below
     */
    @Override
    public String toString() {
        return "Savings | Balance: " + getBalance() + " | Withdrawals: "
                + getWithdrawals() + " | Potential Interest: " + calcInterest();
    }
}
