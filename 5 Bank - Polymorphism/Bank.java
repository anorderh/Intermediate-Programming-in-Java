/**
 * (Program 5 - Banking Application) Bank Class
 * Class defining the Object of Bank, keeping tabs on customer quantity, account quantity, and date
 * @author Anthony Norderhaug
 * @date 3/20/2020
 */
public class Bank {
    static int accounts = 0;
    static int customers = 0;
    int month;
    static boolean isMonthEnd = false;

    /**
     * Bank() constructor for initializing month to 0 and isMonthEnd to false
     */
    public Bank() {
        month = 0;
        isMonthEnd = false;
    }

    /**
     * getMonthEnd() gets
     * @return                  boolean isMonthEnd
     */
    public boolean getMonthEnd() {
        return isMonthEnd;
    }

    /**
     * getMonth gets
     * @return                  int month
     */
    public int getMonth() {
        return month;
    }

    /**
     * nextMonth() increments month contingent on isMonthEnd being true. If month is December (11), month cycles back
     * to 0. Afterwards, isMonthEnd boolean is reset to false.
     * @return                  int month (-1 if isMonthEnd is false)
     */
    public int nextMonth() {
        if (isMonthEnd) {
            if (month < 11) {
                month++;
            }
            else {
                month = 0;
            }
            isMonthEnd = false;
            return month;
        }
        else System.out.println("It is not the end of month!");
        return -1;
    }

    /**
     * endOfMonth() sets isMonthEnd to true and returns month count.
     * @return                  int month (-1 if isMonthEnd is true)
     */

    public int endOfMonth() {
        if (isMonthEnd) {
            System.out.println("It is already end of month!");
            return -1;
        }
        else {
            isMonthEnd = true;
            System.out.println("It is now end of month!");
        }
        return month;
    }

}
