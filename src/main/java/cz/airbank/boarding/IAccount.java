package cz.airbank.boarding;

/**
 * Account interface.
 */
public interface IAccount {
    /**
     * @return Account balance.
     */
    Long getBalance();

    /**
     * @param balance New balance.
     */
    void setBalance(Long balance);

    /**
     * @return Account name (it's unique).
     */
    String getName();
}
