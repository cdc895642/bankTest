package cz.airbank.boarding;

public class IAccountImpl implements IAccount {

  private String name;
  private Long balance;

  public IAccountImpl(String name, Long balance) {
    this.name = name;
    this.balance = balance;
  }

  @Override
  public Long getBalance() {
    return balance;
  }

  @Override
  public void setBalance(Long balance) {
    this.balance = balance;
  }

  @Override
  public String getName() {
    return name;
  }
}
