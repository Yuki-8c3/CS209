package week7;


public class DepositThreadLock implements Runnable {
  private Account account;
  private double money;

  public DepositThreadLock(Account account, double money) {
    this.account = account;
    this.money = money;
  }

  @Override
  public void run() {
//			account.deposit(money);
    account.lockedDeposit(money);
  }
}
