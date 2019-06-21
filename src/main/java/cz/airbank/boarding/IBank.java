package cz.airbank.boarding;

import java.util.Map;
import java.util.Set;

/**
 * Interface for standard bank application.
 */
public interface IBank {
    /**
     * Initializes bank system with accounts. Set of accounts is final and won't change during runtime.
     *
     * @param accounts Map of account names (keys) with their balances (values).
     */
    void setAccounts(Map<String, Long> accounts);

    /**
     * Initializes bank system with transactions. System should ignore transactions where source or target account is not in the system
     * (previously set by setAccounts).
     *
     * @param transactions Set of arrays. Each array (of size 3) consists of account source, account target and amount.
     */
    void setTransactions(Set<Object[]> transactions) throws AccountNotFoundException;

    /**
     * Returns actual balance for given account name. If account doesn't exist, returns null.
     *
     * @param name Account name.
     * @return Actual account balance.
     */
    Long getBalance(String name);

    /**
     * Returns account object for given account name.
     *
     * @param name Account name.
     * @return Account object.
     * @throws cz.airbank.boarding.AccountNotFoundException If acconut is not found in the system.
     */
    IAccount getAccount(String name) throws AccountNotFoundException;

    /**
     * @return Returns sum of amounts for all done transactions.
     */
    Long getSumAmounts();

    /**
     * Main functionality for transaction processing. Moves money from source account to target account.
     *
     * @param source Source account. Amount must be subtract from account balance.
     * @param target Target account. Amount must be add to account balance.
     * @param amount Amount of the transaction.
     * @throws cz.airbank.boarding.AccountNotFoundException If either source or target account is not found in the system.
     */
    void processTransaction(String source, String target, Long amount) throws AccountNotFoundException;
}
