package unit9.task2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Account {

    private int cacheBalance;
    private String name;

    public void addMoney(int money) {
        this.cacheBalance += money;
    }

    public boolean takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return false;
        }

        this.cacheBalance -= money;
        return true;
    }

    public int getCacheBalance() {
        return cacheBalance;
    }

    public String getName() {
        return name;
    }

}