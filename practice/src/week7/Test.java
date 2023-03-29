package week7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);

        for(int i = 1; i <= 100; i++) {
            service.execute(new DepositThread(account, 10));
        }

        service.shutdown();

        while(!service.isTerminated()) {}

        System.out.println("Synchronized Balance: " + account.getBalance());


        ExecutorService lock = Executors.newFixedThreadPool(100);
        Account accountLock = new Account();
        for(int i = 1; i <= 100; i++) {
            lock.execute(new DepositThreadLock(accountLock, 10));
        }

        lock.shutdown();

        while(!lock.isTerminated()) {}

        System.out.println("Locked Balance: " + account.getBalance());
    }
}
