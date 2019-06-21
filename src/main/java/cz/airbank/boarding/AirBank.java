package cz.airbank.boarding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AirBank implements IBank {

    private Map<String, Long> accounts;
    private Set<Object[]> transactions;
    private Long sumAmounts=0L;

    @Override
    public void setAccounts(Map<String, Long> accounts) {
        if (this.accounts != null) {
            return;
        }
        this.accounts = new HashMap<>();
        accounts.forEach((account, amount)->this.accounts.put(account,amount.longValue()));
    }

    @Override
    public void setTransactions(Set<Object[]> transactions) throws AccountNotFoundException {
        this.transactions = transactions;
        Iterator<Object[]> iterator = transactions.iterator();
        while (iterator.hasNext()) {
            Object[] transaction = iterator.next();
            String from = (String) transaction[0];
            String to = (String) transaction[1];
            if (accounts.get(from)==null || accounts.get(to)==null){
                continue;
            }
            Long amount = (Long) transaction[2];
            processTransaction(from, to, amount);
        }
    }

    @Override
    public synchronized void processTransaction(String source, String target, Long amount) throws AccountNotFoundException {
        if (accounts.get(source)==null || accounts.get(target)==null){
            throw new AccountNotFoundException();
        }
        long newSourceAmount = accounts.get(source) - amount;
        accounts.put(source, newSourceAmount);
        long newTargerAmmount = accounts.get(target) + amount;
        accounts.put(target, newTargerAmmount);
        sumAmounts += amount;
    }

    @Override
    public IAccount getAccount(String name) throws AccountNotFoundException {
        return new IAccountImpl(name, accounts.get(name));
    }

    @Override
    public Long getSumAmounts() {
        return sumAmounts;
    }

    @Override
    public Long getBalance(String account) {
        return accounts.get(account);
    }
}
