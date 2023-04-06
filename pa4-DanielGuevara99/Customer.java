import java.util.ArrayList;
import java.util.HashMap;
public class Customer {
    private int _id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Account account;
    private boolean is_ticket_miner_member;
    private ArrayList<Ticket> purchased_tickets;
    private boolean admin;
    private double savings;
    private HashMap<String,Ticket> electronicTicket;

    
    public Customer(int _idIn, String firstNameIn, String lastNameIn, String usernameIn, String passwordIn, double savingsIn){
        _id = _idIn;
        firstName = firstNameIn;
        lastName = lastNameIn;
        this.username = usernameIn;
        this.password = passwordIn;
        account = new Account();
        purchased_tickets = new ArrayList<>();
        admin = false;
        savings = savingsIn;
        electronicTicket =  new HashMap<String,Ticket>();
        }

    public void addToElectronicTicket(Ticket ticketIn){
        electronicTicket.put(ticketIn.ticketId, ticketIn);
    }

    public void viewElectronicTicketKeys(){
        if(electronicTicket.size() == 0){
            System.out.println("No purchased Tickets!");
            return;
        }
        for (String i : electronicTicket.keySet()) {
            System.out.println(i);
          }
    }
    
    public HashMap<String, Ticket> getElectronicTicket() {
        return electronicTicket;
    }

    public void viewElectronicTicketValues(String confirmationNumber){
        if(electronicTicket.size() == 0){
            System.out.println("No purchased Tickets!");
            return;
        }
        for(Ticket i : electronicTicket.values()){
            if(i.getTicketId().equalsIgnoreCase(confirmationNumber)){
                System.out.println(i.printElectronicTicket());
            }
        }
    }

    public double getCustomerSavings() {
        return savings;
    }
    public void addCustomerSavings(double savingsIn) {
        savings += savingsIn;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setAdmin(boolean status){
        admin = status;
    }
    public boolean isAdmin(){
        return admin;
    }
    public void setTicketMasterMemeber(boolean status){
        is_ticket_miner_member = status;
    }
    public boolean getTicketMasterMember(){
        return is_ticket_miner_member;
    }
    public boolean validateUsername(String username){
        return this.username.equals(username);
    }        
    public boolean validatePassword(String password){
        return this.password.equals(password);
    }        
    public void addPurchasedTickets(Ticket ticket){
        purchased_tickets.add(ticket);
    }
    public void viewPurchasedTickets(){
        if(purchased_tickets.size() == 0){
            System.out.println("No purchased Tickets!");
            return;
        }
        for(Ticket ticket : purchased_tickets)
            System.out.println(ticket);
    }
    public boolean isSufficent(double amount){
        return account.isSufficent(amount);
    }
    public boolean deposit(double amount){
        return account.deposit(amount);
    }
    public boolean withdraw(double amount){
        return account.withdraw(amount);
    }
    public String toString(){
        return "Customer : " + lastName + ", " + firstName; 
    }

    
}
