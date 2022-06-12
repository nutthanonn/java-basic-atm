public class Account {
    private String name;
    private double balance = 10000;
    private String password;

    public Account(String name, double balance, String password) {
        this.name = name;
        this.balance = balance;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


    public double getBalance() {
        return balance;
    }

    public void getDeposit(double money){
        balance += money;
    } 

    public void getWithdraw(double money){
        balance -= money;
    } 
}
