public abstract class Event {
    private int _id;
    private String name;
    private String date;
    private String time;
    String type;
    private double vipPrice;
    private double goldPrice;
    private double silverPrice;
    private double bronzePrice;
    private double generalAdmissionPRice;
    private double eventCost;
    private double fireworkCost;
    private Venue venue;
    private boolean firework;
    /* New attributes addeed for PA3 implementation */
    private double amountDiscounted;
    
    public Event(int _idIn, String nameIn, String dateIn, String timeIn, double vipPriceIn, 
                double goldPriceIn, double silverPriceIn, double bronzePriceIn, 
                double generalAdmissionPriceIn, double venueCostIn, Venue venueIn, double amountDiscountedIn){
        _id = _idIn;
        name = nameIn;
        date = dateIn;
        time = timeIn;
        vipPrice = vipPriceIn;
        goldPrice = goldPriceIn;
        silverPrice = silverPriceIn;
        bronzePrice = bronzePriceIn;
        generalAdmissionPRice = generalAdmissionPriceIn;
        eventCost = venueCostIn;
        venue = venueIn;
        amountDiscounted = amountDiscountedIn;
    }

    public double getAmountDiscounted() {
        return amountDiscounted;
    }
    public void addAmountDiscounted(double discount) {
        amountDiscounted += discount;
    }
    /* NEW METHODS FOR PA4 IMPLEMENTATIONS*/
    
    
    public int getID(){
        return _id;
    }
    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getEventCost() {
        return eventCost;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getName(){
        return name;
    }
    public double getVipPrice(){
        return vipPrice;
    }
    public double getGoldPrice(){
        return goldPrice;
    }
    public double getSilverPrice(){
        return silverPrice;
    }
    public double getBronzePrice(){
        return bronzePrice;
    }
    public double getGeneralAdmissionPrice(){
        return generalAdmissionPRice;
    }
    public void setFireWorks(boolean hasfirework){
        firework = hasfirework;
    }
    public void setFireWorksCost(double cost){
        fireworkCost = cost;
    }
    public int getVenueTierCapacity(int tier){
        switch(tier){
            case 0:
                return venue.vipCapacity;
            case 1:
                return venue.goldCapacity;
            case 2:
                return venue.silverCapacity;
            case 3:
                return venue.bronzeCapacity;
            case 4:
                return venue.generalAdmissionCapacity;
            default:
                return -1;
        }
    }

    public int getVenueTierCapacityName(String tier){
        switch(tier.toLowerCase()){
            case "vip":
                return venue.vipCapacity;
            case "gold":
                return venue.goldCapacity;
            case "silver":
                return venue.silverCapacity;
            case "bronze":
                return venue.bronzeCapacity;
            case "general admission":
                return venue.generalAdmissionCapacity;
            default:
                return -1;
        }
    }

    public double getEventTierPrice(int tier){
        switch(tier){
            case 0:
                return vipPrice;
            case 1:
                return goldPrice;
            case 2:
                return silverPrice;
            case 3:
                return bronzePrice;
            case 4:
                return generalAdmissionPRice;
            default:
                return -1;
        }
    }

    public double getEventTierPriceName(String tier){
        switch(tier.toLowerCase()){
            case "vip":
                return vipPrice;
            case "gold":
                return goldPrice;
            case "silver":
                return silverPrice;
            case "bronze":
                return bronzePrice;
            case "general admission":
                return generalAdmissionPRice;
            default:
                return -1;
        }
    }

    public Ticket vendTicket(int tier){
        switch(tier){
            case 0 : 
                // 0 -> VIP TIER
                venue.vipSoldCount++;
                venue.vipCapacity--;
                return new VIPTicket(this);
            case 1:
                // 1 -> Gold TIER
                venue.goldSoldCount++;
                venue.goldCapacity--;
                return new GoldTicket(this);
            case 2:
                // 2 -> Silver TIER
                venue.silverSoldCount++;
                venue.silverCapacity--;
                return new SilverTicket(this);
            case 3:
                // 3 -> Bronze TIER
                venue.bronzeSoldCount++;
                venue.bronzeCapacity--;
                return new BronzeTicket(this);
            case 4:
                // 4 -> General TIER
                venue.generalSoldCount++;
                venue.bronzeCapacity--;
                return new GeneralAdmissionTicket(this);
            default:
                return null;
        }
    }



    public Ticket vendTicketName(String tier){
        switch(tier.toLowerCase()){
            case "vip" : 
                // 0 -> VIP TIER
                venue.vipSoldCount++;
                venue.vipCapacity--;
                return new VIPTicket(this);
            case "gold":
                // 1 -> Gold TIER
                venue.goldSoldCount++;
                venue.goldCapacity--;
                return new GoldTicket(this);
            case "silver":
                // 2 -> Silver TIER
                venue.silverSoldCount++;
                venue.silverCapacity--;
                return new SilverTicket(this);
            case "bronze":
                // 3 -> Bronze TIER
                venue.bronzeSoldCount++;
                venue.bronzeCapacity--;
                return new BronzeTicket(this);
            case "general admission":
                // 4 -> General TIER
                venue.generalSoldCount++;
                venue.bronzeCapacity--;
                return new GeneralAdmissionTicket(this);
            default:
                return null;
        }
    }

    public void printEventDetail(){
        double 
            vip_sold = venue.vipSoldCount * vipPrice,
            gold_sold = venue.goldSoldCount * goldPrice,
            silver_sold = venue.silverSoldCount * silverPrice,
            bronze_sold = venue.bronzeSoldCount * bronzePrice,
            general_sold = venue.generalSoldCount * generalAdmissionPRice,
            taxRevenue = ((0.0825)*(vip_sold + gold_sold + silver_sold +bronze_sold + general_sold)),
            expected_sold_out = (vipPrice * venue.VIP_CAPACITY_CONSTANT) + 
                                (goldPrice * venue.GOLD_CAPACITY_CONSTANT) + 
                                (silverPrice * venue.SILVER_CAPACITY_CONSTANT) + 
                                (bronzePrice * venue.BRONZE_CAPACITY_CONSTANT) + 
                                (generalAdmissionPRice * venue.GENERAL_ADMISSION_CAPACITY_CONSTANT);
        String bar = "-".repeat(50);
        taxRevenue = Math.round(taxRevenue*100);
        taxRevenue = taxRevenue / 100;

        System.out.println(
            bar + "\n" +
            "Event ID: " + _id + "\n" +
            name + "\n" + 
            date + "\n" + 
            "Even Type : " + venue.venueType + "\n" + 
            "Event Capacity : " + venue.totalCapacity + "\n" + 
            "Total VIP Seats Sold : " + venue.vipSoldCount + "\n" + 
            "Total Gold Seats Sold : " + venue.goldSoldCount + "\n" + 
            "Total Silver Seats Sold : " + venue.silverSoldCount + "\n" + 
            "Total Bronze Seats Sold : " + venue.bronzeSoldCount + "\n" + 
            "Total General Admission Seats Sold : " + venue.generalSoldCount + "\n" +
            "Total Revenue for VIP Tickets : $" + vip_sold + "\n" + 
            "Total Revenue for Gold Tickets : $" + gold_sold + "\n" + 
            "Total Revenue for Silver Tickets : $" + silver_sold + "\n" +
            "Total Revenue for Bronze Tickets : $" + bronze_sold + "\n" +
            "Total Revenue for General Admission Ticket : $" + general_sold + "\n" +
            "Total Revenue for all Tickets : $" + (vip_sold + gold_sold + silver_sold + bronze_sold + general_sold) + "\n" +
            "Expected profit (Sell Out) : $" + expected_sold_out +"\n" +
            "Actual Profit : $" + ((vip_sold + gold_sold + silver_sold + bronze_sold + general_sold) - expected_sold_out) + "\n" + 
            "Total Tax Revenue: $ " + taxRevenue + "\n" + 
            "Total Amount Discounted: $ " + getAmountDiscounted() + "\n" +
            bar
        );
    }
    public String eventToCSV(){
        double 
            vip_sold = venue.vipSoldCount * vipPrice,
            gold_sold = venue.goldSoldCount * goldPrice,
            silver_sold = venue.silverSoldCount * silverPrice,
            bronze_sold = venue.bronzeSoldCount * bronzePrice,
            general_sold = venue.generalSoldCount * generalAdmissionPRice;
        return String.join(",",
        ""+_id, // event id
        type, // event type
        name, // event name
        date, // event date
        time, // event time
        ""+vipPrice, // VIP Price
        ""+goldPrice, // Gold Prce
        ""+silverPrice, // Silver Price
        ""+bronzePrice, // Bronze Price
        ""+generalAdmissionPRice, // GA Price
        venue.name, // Veunue Name
        ""+(int)((venue.unavilableCapacity / venue.totalCapacity) * 100), // Unavilable Seats
        ""+venue.venueType, // Venue Type
        ""+venue.totalCapacity, // Capacity
        ""+eventCost, // Cost
        ""+(int)((venue.VIP_CAPACITY_CONSTANT / venue.totalCapacity) * 100), // VIP Pct
        ""+(int)((venue.GOLD_CAPACITY_CONSTANT / venue.totalCapacity) * 100), // Gold Pct
        ""+(int)((venue.SILVER_CAPACITY_CONSTANT / venue.totalCapacity) * 100), // Silver Pct
        ""+(int)((venue.BRONZE_CAPACITY_CONSTANT/ venue.totalCapacity) * 100), // Bronze Pct
        ""+(int)((venue.GENERAL_ADMISSION_CAPACITY_CONSTANT / venue.totalCapacity) * 100), // General Pct
        ""+(int)((venue.RESERVED_CAPACITY_CONSTANT / venue.totalCapacity) * 100), // Reserved Pct
        (""+firework).toUpperCase(), // firework
        ""+fireworkCost,
        ""+venue.vipSoldCount,
        ""+venue.goldSoldCount,
        ""+venue.silverSoldCount,
        ""+venue.bronzeSoldCount,
        ""+venue.generalSoldCount,
        ""+vip_sold,
        ""+gold_sold,
        ""+silver_sold,
        ""+bronze_sold,
        ""+general_sold
        );
    }
    public String toString(){
        String rtn = null;
        if(venue == null){
            rtn = "ID : " + _id + "\n" +
                "\tEvent\t: " + name + " (" + type + ")" + "\n" +
                "\tDate\t: " + date + "\n" +
                "\tTime\t: " + time;
        }
        else
            rtn = "ID : " + _id + "\n" +
                    "\tEvent\t: " + name + " (" + type + ")" + "\n" +
                    "\tVenue\t: " + venue.name + " (" + venue.venueType + ")" + "\n" + 
                    "\tDate\t: " + date + "\n" +
                    "\tTime\t: " + time;
        if(firework)
            rtn += "\n\tEvent has FireWorks!!";
        return "-".repeat(50) + "\n" + rtn;
    }

}


