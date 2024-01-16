package unit9.task2;

public class AccountThreadV2 implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;
    private static final Object monitor = new Object();

    public AccountThreadV2(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            while (true) {
                synchronized (monitor) {
                    if (!accountFrom.takeOffMoney(money)) {
                        continue;
                    }
                    accountTo.addMoney(money);
                    System.out.println(
                        String.format(
                            "Transfered %d money from %s (%d) to %s (%d)",
                            money, accountFrom.getName(), accountFrom.getCacheBalance(), accountTo.getName(),
                            accountTo.getCacheBalance()
                        )
                    );
                    break;
                }

            }
        }
    }
}