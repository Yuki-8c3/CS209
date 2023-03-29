package week7;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private double balance;

    /**
     *
     * @param money
     */
    public synchronized void deposit(double money) {
    	// use synchronized to make the processes to equally reach the block
        try {
			double newBalance = balance + money;
			try {
			    Thread.sleep(10);   // Simulating this service takes some processing time
			}
			catch(InterruptedException ex) {
			    ex.printStackTrace();
			}
			balance = newBalance;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	public void lockedDeposit(double money) {
		// use synchronized to make the processes to equally reach the block
		final ReentrantLock lock = new ReentrantLock();
		try {
			lock.lock();
			double newBalance = balance + money;
//			try {
//				Thread.sleep(10);   // Simulating this service takes some processing time
//			}
//			catch(InterruptedException ex) {
//				ex.printStackTrace();
//			}
			balance = newBalance;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}


    public double getBalance() {
        return balance;
    }
}