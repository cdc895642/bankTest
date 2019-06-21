package cz.airbank.boarding;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BankDataFactory {
    /**
     * Map of account names (keys) with their balances (values).
     */
    private static final Map<String, Long> accounts = new HashMap<>();

    static {
        accounts.put("A", 154800L);
        accounts.put("B", 14900L);
        accounts.put("C", 847199L);
    }

    ;

    /**
     * Set of arrays. Each array (of size 3) consists of account source, account target and amount.
     */
    private static final Set<Object[]> transactions = new HashSet<>();

    static {
        transactions.add(new Object[]{"A", "G", 865L});
        transactions.add(new Object[]{"A", "B", 65494L});
        transactions.add(new Object[]{"B", "A", 986000L});
    }

    public static Map<String, Long> getAccounts() {
        return accounts;
    }

    public static Set<Object[]> getTransactions() {
        return transactions;
    }
}

