package cz.airbank.boarding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for our new Banking system.
 */
public class AirBankTest {
    private IBank bank;

    @Before
    public void setUp() throws AccountNotFoundException {
        bank = new AirBank();
        bank.setAccounts(BankDataFactory.getAccounts());
        bank.setTransactions(BankDataFactory.getTransactions());
    }

    @Test
    public void testBalances() {
        Assert.assertEquals(1075306L, (long) bank.getBalance("A"));
        Assert.assertEquals(-905606L, (long) bank.getBalance("B"));
        Assert.assertEquals(847199L, (long) bank.getBalance("C"));
    }

//    @Ignore
    @Test
    public void testSumAmounts() throws AccountNotFoundException, InterruptedException {
        Assert.assertEquals(1051494L, (long) bank.getSumAmounts());
        bank.processTransaction("A", "A", 12L);
        Assert.assertEquals(1051506L, (long) bank.getSumAmounts());


        TransactionThread t1 = new TransactionThread("A", "B");
        t1.start();
        TransactionThread t2 = new TransactionThread("C", "A");
        t2.start();

        t1.join();
        t2.join();

        Assert.assertEquals(1075306L, (long) bank.getBalance("A"));
        Assert.assertEquals(1051506L + TransactionThread.LOOPS * 2, (long) bank.getSumAmounts());
    }

//    @Ignore
    @Test(expected = AccountNotFoundException.class)
    public void testProcessTransaction() throws AccountNotFoundException {
        bank.processTransaction("D", "A", 1L);
    }

    private class TransactionThread extends Thread {
        public final static int LOOPS = 10000;
        String source;
        String target;

        TransactionThread(String source, String target) {
            this.source = source;
            this.target = target;
        }

        public void run() {
            for (int i = 0; i < LOOPS; ++i) {
                try {
                    bank.processTransaction(source, target, 1L);
                } catch (AccountNotFoundException e) {
                    // nothing
                }
            }
        }
    }
}
