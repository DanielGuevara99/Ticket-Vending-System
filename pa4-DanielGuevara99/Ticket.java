public abstract class Ticket {
    String ticketType;
    String ticketId;
    Event EVENT;
    public Ticket(Event event){
        EVENT = event;
        ticketId = EVENT.getName().substring(0,3).toUpperCase();
        char [] seq = new char[8];
        for(int i = 0; i < 4; i++)
            seq[i] = (char)((int)(Math.random() * 26) + (int)'A');
        for(int i = 4; i < seq.length; i++)
            seq[i] = (char)((int)(Math.random() * 10) + (int)'0');
        for(char i : seq)
            ticketId += i;
    }
    abstract double getTicketPrice();
    public String toString(){
        String bar = "-".repeat(50);
        return bar + "\n" + 
                "Ticket ID : " + ticketId + "\n" +
                "Event : " + EVENT.getName() + "\n" +
                "Price : $" + this.getTicketPrice() + "\n" +
                bar;
    }

    public String printElectronicTicket(){
        String bar = "-".repeat(50);
        return bar + "\n" + 
                "Event Type : " + EVENT.getType() + "\n" +
                "Event Name : " + EVENT.getName() + "\n" + 
                "Event Date : " + EVENT.getDate() + "\n" + 
                "Ticket Type : " + ticketType + "\n" +
                "Confirmation Number : " + ticketId + "\n" +
                bar;
    }
    public String getTicketId() {
        return ticketId;
    }

    
}
class VIPTicket extends Ticket{
    VIPTicket(Event event){
        super(event);
        ticketType = "VIP Ticket";
    }
    public double getTicketPrice(){return EVENT.getVipPrice();}
}
class GoldTicket extends Ticket{
    GoldTicket(Event event){
        super(event);
        ticketType = "Gold Ticket";
    }
    public double getTicketPrice(){return EVENT.getGoldPrice();}
}
class SilverTicket extends Ticket{
    SilverTicket(Event event){
        super(event);
        ticketType = "Silver Ticket";
    }
    public double getTicketPrice(){return EVENT.getSilverPrice();}
}
class BronzeTicket extends Ticket{
    BronzeTicket(Event event){
        super(event);
        ticketType = "Bronze Ticket";
    }
    public double getTicketPrice(){return EVENT.getBronzePrice();}
}
class GeneralAdmissionTicket extends Ticket{
    GeneralAdmissionTicket(Event event){
        super(event);
        ticketType = "General Admission Ticket";
    }
    public double getTicketPrice(){return EVENT.getGeneralAdmissionPrice();}
}
