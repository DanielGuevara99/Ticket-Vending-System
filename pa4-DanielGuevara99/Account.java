public class Account {
    int _ID;
    double balance;

    public double getBalance(){
        return balance;
    }
    public boolean deposit(double balance){
        this.balance += balance;
        return true;
    }
    public boolean withdraw(double balance){
        if(this.balance - balance >= 0){
            this.balance -= balance;
            return true;
        }
        return false;
    }
    /**
     * Checks if balance vs input balance is sufficent
     * @param amount value to be checked
     * @return boolean 
     */
    public boolean isSufficent(double amount){
        return balance >= amount;
    }


}
