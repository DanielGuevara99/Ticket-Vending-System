import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunTicket {
    static Customer current_user = null;
    static int getCapacityFromPercent(int percent, int capacity){
        return (int)((percent / 100.0) * capacity);
    }
    /* Method reads customer list csv */
    static boolean populateCustomers(ArrayList<Customer> customers, String file_name){
        Scanner fs = null;
        try{
            fs = new Scanner(new File(file_name)); // file scanner
        }
        catch(FileNotFoundException e){ // catch exception
            System.out.println(e.getMessage()); // print file not found
            return false;
        }
        // fs.nextLine(); // removes header
        int _id = 0; // col 0 in original csv
        String firstName = null; // col 1 in original csv
        String lastName = null; // col 2 in original csv
        String username = null; // col 3 in original csv
        String password = null; // col 4 in original csv
        double moneyAvailabe = 0; // col 5 in original csv
        boolean is_ticket_miner_member = false; // col 6 in original csv
        double savings = 0;


        String[] fileHeader = "ID,First Name,Last Name,Money Available,Concerts Purchased,TicketMiner Membership,Username,Password".split(",");
        String[] csvRead = fs.nextLine().split(",");
        HashMap<String,Integer> customerMap = new HashMap<String,Integer>();

        for(int i = 0; i < csvRead.length; i++){
            customerMap.put(csvRead[i],i);
        }

        // while loop will continue as long as next line exists
        while(fs.hasNextLine()){ 
            // current_line array will hold data from csv
            String [] current_line = fs.nextLine().split(","); 
            _id = Integer.parseInt(current_line[customerMap.get(fileHeader[0])]); // col 0 in original csv
            firstName = current_line[customerMap.get(fileHeader[1])]; // col 1 in original csv
            lastName = current_line[customerMap.get(fileHeader[2])]; // col 2 in original csv
            moneyAvailabe = Double.parseDouble(current_line[customerMap.get(fileHeader[3])]); // col 3 in original csv
            is_ticket_miner_member = current_line[customerMap.get(fileHeader[5])].equalsIgnoreCase("TRUE"); // col 4 in original csv
            username = current_line[customerMap.get(fileHeader[6])]; // col 5 in original csv
            password = current_line[customerMap.get(fileHeader[7])]; // col 6 in original csv
            // new instance of customer to add to customers list
            Customer customer = new Customer(_id, firstName, lastName, username, password, savings);
            customer.deposit(moneyAvailabe);
            customer.setTicketMasterMemeber(is_ticket_miner_member);
            customers.add(customer);
        }
        fs.close();
        return true;
    }
    /*Method Reads eventlist csv */
    static boolean populateEvents(ArrayList<Event> event_list, String file_name){
        Scanner fs;
        try{
            fs = new Scanner(new File(file_name));
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage()); // print file not found
            return false;
        }  
        int event_id = 0; // col 0 
        int venueUnavilablePercent = 0; // col 11
        int venueCapacity = 0; // col 13
        int venueCost = 0; //col 14
        int venueVipPercent = 0; //col 15
        int venueGoldPercent = 0; //col 16
        int venueSilverPercent = 0; //col 17
        int venueBronzePercent = 0; //col 18
        int venueGeneralAdmissionPrecent = 0; //col 19
        int venueReservedPercent = 0; //col 20
        int eventFireworkCost = 0; //col 22
        String eventType = null; //col 1
        String eventName = null; //col 2
        String eventDate = null; //col 3
        String eventTime = null; //col 4
        String venueName = null; //col 10
        String venueType = null; //col 12
        double vipPrice = 0; //col 5
        double goldPrice = 0; //col 6
        double silverPrice = 0; //col 7
        double bronzePrice = 0; //col 8
        double generalAdmissionPrice = 0; //col 9
        boolean venueFirework = false; //col 21
        double amountDiscounted = 0;

        String[] fileHeader = "Event ID,Event Type,Name,Date,Time,VIP Price,Gold Price,Silver Price,Bronze Price,General Admission Price,Venue Name,Seats Unavailable Pct,Venue Type,Capacity,Cost,VIP Pct,Gold Pct,Silver Pct,Bronze Pct,General Admission Pct,Reserved Extra Pct,Fireworks Planned,Fireworks Cost".split(",");
        String[] csvRead = fs.nextLine().split(",");
        HashMap<String,Integer> eventMap= new HashMap<String,Integer>();

        for(int i = 0; i < csvRead.length; i++){
            eventMap.put(csvRead[i],i);
        }

        // fs.nextLine(); // skip header
        // while loop will continue as long as next line exist
        while(fs.hasNextLine()){

            // current_line array will hold data from csv
            String [] current_line = fs.nextLine().split(",");
            event_id = Integer.parseInt(current_line[eventMap.get(fileHeader[0])]); // col 0
            eventType = current_line[eventMap.get(fileHeader[1])];// col 1
            eventName = current_line[eventMap.get(fileHeader[2])];// col 3
            eventDate = current_line[eventMap.get(fileHeader[3])];// col 18
            eventTime = current_line[eventMap.get(fileHeader[4])];// col 5
            vipPrice = Double.parseDouble(current_line[eventMap.get(fileHeader[5])]);// col 8
            goldPrice = Double.parseDouble(current_line[eventMap.get(fileHeader[6])]);// col 2
            silverPrice = Double.parseDouble(current_line[eventMap.get(fileHeader[7])]);// col 21
            bronzePrice = Double.parseDouble(current_line[eventMap.get(fileHeader[8])]);// col 9
            generalAdmissionPrice = Double.parseDouble(current_line[eventMap.get(fileHeader[9])]);// col 11
            venueName = current_line[eventMap.get(fileHeader[10])];// col 13
            venueUnavilablePercent = Integer.parseInt(current_line[eventMap.get(fileHeader[11])]);// col 15
            venueType = current_line[eventMap.get(fileHeader[12])];// col 7
            venueCapacity = Integer.parseInt(current_line[eventMap.get(fileHeader[13])]); // col 4
            venueCost = Integer.parseInt(current_line[eventMap.get(fileHeader[14])]);// col 16
            venueVipPercent = Integer.parseInt(current_line[eventMap.get(fileHeader[15])]);// col 17
            venueGoldPercent = Integer.parseInt(current_line[eventMap.get(fileHeader[16])]);// col 19
            venueSilverPercent = Integer.parseInt(current_line[eventMap.get(fileHeader[17])]); // col 17
            venueBronzePercent = Integer.parseInt(current_line[eventMap.get(fileHeader[18])]); // col 22
            venueGeneralAdmissionPrecent = Integer.parseInt(current_line[19]);// col 0
            venueReservedPercent = Integer.parseInt(current_line[eventMap.get(fileHeader[20])]);//col 6
            amountDiscounted = 0;
            try{ // if firworks planned equals TRUE
                venueFirework = current_line[eventMap.get(fileHeader[21])].equalsIgnoreCase("true");
                eventFireworkCost = Integer.parseInt(current_line[eventMap.get(fileHeader[22])]);
            }
            catch(ArrayIndexOutOfBoundsException e){ // if no data in col 21
                venueFirework = false;
                eventFireworkCost = 0;
            }
            // venue factory
            Venue venue = null;
            /* switch case to create either new arena, auditorium, stadium or open air venue  */
            switch (venueType.toLowerCase().replaceAll(" ","")){
                case "stadium": venue = new Stadium(
                    venueName,
                    getCapacityFromPercent(venueVipPercent, venueCapacity),
                    getCapacityFromPercent(venueGoldPercent, venueCapacity),
                    getCapacityFromPercent(venueSilverPercent, venueCapacity),
                    getCapacityFromPercent(venueBronzePercent, venueCapacity),
                    getCapacityFromPercent(venueGeneralAdmissionPrecent,venueCapacity),
                    getCapacityFromPercent(venueUnavilablePercent,venueCapacity),
                    getCapacityFromPercent(venueReservedPercent,venueCapacity),
                    venueCapacity
                    );
                case "arena" : venue = new Arena(
                    venueName,
                    getCapacityFromPercent(venueVipPercent, venueCapacity),
                    getCapacityFromPercent(venueGoldPercent, venueCapacity),
                    getCapacityFromPercent(venueSilverPercent, venueCapacity),
                    getCapacityFromPercent(venueBronzePercent, venueCapacity),
                    getCapacityFromPercent(venueGeneralAdmissionPrecent,venueCapacity),
                    getCapacityFromPercent(venueUnavilablePercent,venueCapacity),
                    getCapacityFromPercent(venueReservedPercent,venueCapacity),
                    venueCapacity
                    );
                case "auditorium" : venue = new Auditorium(
                    venueName,
                    getCapacityFromPercent(venueVipPercent, venueCapacity),
                    getCapacityFromPercent(venueGoldPercent, venueCapacity),
                    getCapacityFromPercent(venueSilverPercent, venueCapacity),
                    getCapacityFromPercent(venueBronzePercent, venueCapacity),
                    getCapacityFromPercent(venueGeneralAdmissionPrecent,venueCapacity),
                    getCapacityFromPercent(venueUnavilablePercent,venueCapacity),
                    getCapacityFromPercent(venueReservedPercent,venueCapacity),
                    venueCapacity
                    );
                case "openair" : venue = new OpenAir(
                    venueName,
                    getCapacityFromPercent(venueVipPercent, venueCapacity),
                    getCapacityFromPercent(venueGoldPercent, venueCapacity),
                    getCapacityFromPercent(venueSilverPercent, venueCapacity),
                    getCapacityFromPercent(venueBronzePercent, venueCapacity),
                    getCapacityFromPercent(venueGeneralAdmissionPrecent,venueCapacity),
                    getCapacityFromPercent(venueUnavilablePercent,venueCapacity),
                    getCapacityFromPercent(venueReservedPercent,venueCapacity),
                    venueCapacity
                    );
            }
            // event factory
            Event event = null;
            /* switch case to create either new sport, concert or special event */
            switch (eventType.toLowerCase()){
                case "sport": event = new Sport(event_id,eventName,eventDate,eventTime,vipPrice,
                    goldPrice,silverPrice,bronzePrice,generalAdmissionPrice,venueCost,venue, amountDiscounted);
                case "concert": event = new Concert(event_id,eventName,eventDate,eventTime,vipPrice,
                    goldPrice,silverPrice,bronzePrice,generalAdmissionPrice,(int)venueCost,venue, amountDiscounted);
                case "special": event = new Special(event_id,eventName,eventDate,eventTime,vipPrice,
                    goldPrice,silverPrice,bronzePrice,generalAdmissionPrice,(int)venueCost,venue, amountDiscounted);
            }
            event.setFireWorks(venueFirework);
            event.setFireWorksCost(eventFireworkCost);
            event_list.add(event);  // add event to list 
        }
        fs.close();
        return true;
    }
    /* Gets largest eventID */
    public static int getLargestID(ArrayList<Event> events){
        int max = 0;
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getID() > max){
                max = events.get(i).getID();
            }
        }
        return max;
    }
    /* Get total capacity of a venue bases on event list csv */
    public static int getVenueCapacity(ArrayList<Event> events, String name){
        int capacity = 0;
        for(Event event: events){
            if(event.getVenue().getName().equalsIgnoreCase(name)){
                capacity = event.getVenue().getTotalCapacity();
                break;
            }
        }
        return capacity;
    }

    /* Get Venue cost from csv */
    public static double getVenueCost(ArrayList<Event> events, String name){
        double cost = 0;
        for(Event event: events){
            if(event.getVenue().getName().equalsIgnoreCase(name)){
                cost = event.getEventCost();
                break;
            }
        }
        return cost;
    }

    /* Method to get capacity of all different tiers */
    public static int setTicketCapacity(int percent, int totalCapacity){
        totalCapacity /= 100.0;
        int capacity = (int)totalCapacity * percent;
        return capacity;
    }

    /* Method to get price of all tiers based on general admission price */
    public static double calculatePrice(double factor, double gaPrice){
        double tierPrice = gaPrice*factor;
        tierPrice = Math.round(tierPrice*100);
        tierPrice = tierPrice / 100;
        return tierPrice;
    }

    /* loginUser method will prompt user to log into system */
    public static void loginUser(ArrayList<Customer> customers, Scanner sc, PrintWriter logs){
        String username;
        String password;
        System.out.print("Please enter your username : ");
        username = sc.nextLine();
        logs.println(username);
        System.out.print("Please enter your password : ");
        password = sc.nextLine();
        logs.println(password);
        boolean username_validated = false; 
        boolean password_validated = false;
        //iterate through customers list
        for(Customer tmp_customer : customers){ 
            // checks if user input and customers list data match
            username_validated = tmp_customer.validateUsername(username);
            password_validated = tmp_customer.validatePassword(password);
            if(username_validated && password_validated){
                current_user = tmp_customer;
                break;
            }
        }
        if(!username_validated) { // tells user incorrect username
            System.out.println("No username \"" + username + "\" in database!");
            return;
        }
        if(!password_validated) { // tells user incorrect password
            System.out.println("Passowrd is Incorrect!");
            return;
        }
        System.out.print("Are you an admin ? : ");
        String response = sc.nextLine();
        logs.println(response);
        // logs.close();
        /*if user is an admin, then user admin atrribute is set to TRUE */
        if(response.equalsIgnoreCase("yes"))
            current_user.setAdmin(true);
        return;
    }
    /* Method is responsible for retreiving event by ID, method will itertate through events list
     * and return event with matching eventID, will return null if eventID not found */
    public static Event inquireEventByID(ArrayList<Event> events, int _id){
        for(Event event : events){
            if(event.getID() == _id)
                return event;
        }
        System.out.println("Event with id " + _id + " not found!");
        return null;
    }
    /* Method is responsible for retreiving event by name, method will itertate through events list
     * and return event with matching eventName, will return null if eventName not found */
    public static Event inquireEventByName(ArrayList<Event> events, String name){
        for(Event event : events){
            if(event.getName().equalsIgnoreCase(name))
                return event;
        }
        System.out.println("Event with name \"" + name + "\" not found!");
        return null;
    }
    /* Method will return id of an event */
    // do something
    /* Method is responsible for manually adding a new event to the event list */
    public static void AdminAddEvent(ArrayList<Event> events ,Scanner sc){
        String venueOptions = """

            1. Sun Bowl Stadium
            2. Don Haskins Center
            3. Magoffin  Auditorium
            4. San Jacinto Plaza
            5. Centennial Plaza
            """;
        Venue venue = null;
        Event event = null;
        int _id = getLargestID(events);
        _id += 1;
        // String eventName, eventDate, eventTime, eventType;
        double fireworkCost;
        // double eventCost, vipPrice, goldPrice, silverPrice, bronzePrice
        boolean firework;
        System.out.println("Manually adding event! What is name of event? " );
        String eventName = sc.nextLine();

        System.out.println("What is the date of the event? (MM/DD/YYYY) ");
        String eventDate = sc.nextLine();

        System.out.println("What time will the event take place? (XX:XX AM or PM)");
        String eventTime = sc.nextLine();

        System.out.println("Will there be fireworks? (yes/no) ");
        if(sc.nextLine().equalsIgnoreCase("yes")){
            firework = true;
            System.out.println("How much will fireworks cost? ($$.$$)");
            fireworkCost = Double.parseDouble(sc.nextLine());
        } else { 
            firework = false;
            fireworkCost = 0.0;
        }
        System.out.println("What is price of General Admision? ($$.$$). Max General Admission price is $1000.00");
        double generalAdmissionPrice = Double.parseDouble(sc.nextLine());

        System.out.println("Select a venue option (number)\n " + venueOptions);
        int venueChoice = Integer.parseInt(sc.nextLine());
        int capacity;
        String venueName = "";
        switch(venueChoice) {
            case 1: 
                venueName = "Sun Bowl Stadium";
                capacity = getVenueCapacity(events, venueName);
                venue = new Stadium(venueName, setTicketCapacity(5, capacity), 
                setTicketCapacity(10, capacity), setTicketCapacity(15, capacity),
                setTicketCapacity(20, capacity), setTicketCapacity(45, capacity),
                setTicketCapacity(0, capacity), setTicketCapacity(5, capacity), capacity);
            case 2: 
                venueName = "Don Haskins Center";
                capacity = getVenueCapacity(events, venueName);
                venue = new Arena(venueName, setTicketCapacity(5, capacity), 
                setTicketCapacity(10, capacity), setTicketCapacity(15, capacity),
                setTicketCapacity(20, capacity), setTicketCapacity(45, capacity),
                setTicketCapacity(0, capacity), setTicketCapacity(5, capacity), capacity);
            case 3: 
                venueName = "Magoffin Auditorium";
                capacity = getVenueCapacity(events, venueName);
                venue = new Auditorium(venueName, setTicketCapacity(5, capacity), 
                setTicketCapacity(10, capacity), setTicketCapacity(15, capacity),
                setTicketCapacity(20, capacity), setTicketCapacity(45, capacity),
                setTicketCapacity(0, capacity), setTicketCapacity(5, capacity), capacity);
            case 4: 
                venueName = "San Jacinto Plaza";
                capacity = getVenueCapacity(events, venueName);
                venue = new OpenAir(venueName, setTicketCapacity(5, capacity), 
                setTicketCapacity(10, capacity), setTicketCapacity(15, capacity),
                setTicketCapacity(20, capacity), setTicketCapacity(45, capacity),
                setTicketCapacity(0, capacity), setTicketCapacity(5, capacity), capacity);
            case 5: 
                venueName = "Centennial Plaza";
                capacity = getVenueCapacity(events, venueName);
                venue = new OpenAir(venueName, setTicketCapacity(5, capacity), 
                setTicketCapacity(10, capacity), setTicketCapacity(15, capacity),
                setTicketCapacity(20, capacity), setTicketCapacity(45, capacity),
                setTicketCapacity(0, capacity), setTicketCapacity(5, capacity), capacity);
        }
        System.out.println("What kind of event will you be creating?\n Concert? Sport? Special?");
        String eventType = sc.nextLine();
        switch(eventType.toLowerCase()){
            case "concert": event = new Concert(_id,eventName,eventDate,eventTime,calculatePrice(5.0, generalAdmissionPrice),
                calculatePrice(3.0, generalAdmissionPrice),calculatePrice(2.5, generalAdmissionPrice),
                calculatePrice(1.5, generalAdmissionPrice),generalAdmissionPrice,getVenueCost(events, venueName),venue, 0.0);
            case "sport" : event = new Sport(_id,eventName,eventDate,eventTime,calculatePrice(5.0, generalAdmissionPrice),
                calculatePrice(3.0, generalAdmissionPrice),calculatePrice(2.5, generalAdmissionPrice),
                calculatePrice(1.5, generalAdmissionPrice),generalAdmissionPrice,getVenueCost(events, venueName),venue, 0.0);
            default : event = new Special(_id,eventName,eventDate,eventTime,calculatePrice(5.0, generalAdmissionPrice),
                calculatePrice(3.0, generalAdmissionPrice),calculatePrice(2.5, generalAdmissionPrice),
                calculatePrice(1.5, generalAdmissionPrice),generalAdmissionPrice,getVenueCost(events, venueName),venue, 0.0);
        }
        event.setFireWorks(firework);
        event.setFireWorksCost(fireworkCost);
        events.add(event);
    }
    /* Method is responsible for manually purchasing events from csv */
    public static void autoPurchasers(ArrayList<Event> events,ArrayList<Customer> customers, String file_name){
        Scanner fs = null;
        try{
            fs = new Scanner(new File(file_name)); // file scanner
        }
        catch(FileNotFoundException e){ // catch exception
            System.out.println(e.getMessage()); // print file not found
            // return false;
        }   
        /* First,Last,Action,Event ID,Event Name,Ticket Quantity,Ticket Type
            Arim,MartinDelCampo,Buy,45,UTEP Wind Symphony 1,6,Silver */
        String firstName, lastName, action, eventName, ticketType;
        int eventID, ticketQuantity;
        String[] fileHeader = "First,Last,Action,Event ID,Event Name,Ticket Quantity,Ticket Type".split(",");
        String[] csvRead = fs.nextLine().split(",");
        HashMap<String,Integer> customerMap = new HashMap<String,Integer>();

        for(int i = 0; i < csvRead.length; i++){
            customerMap.put(csvRead[i],i);
        }

        // while loop will continue as long as next line exists
        while(fs.hasNextLine()){ 
            // current_line array will hold data from csv
            String [] current_line = fs.nextLine().split(","); 
            firstName = current_line[customerMap.get(fileHeader[0])]; // col 0 in original csv
            lastName = current_line[customerMap.get(fileHeader[1])]; // col 1 in original csv
            action = current_line[customerMap.get(fileHeader[2])]; // col 2 in original csv
            eventID = Integer.parseInt(current_line[customerMap.get(fileHeader[3])]);
            eventName = current_line[customerMap.get(fileHeader[4])]; // col 3 in original csv
            ticketQuantity = Integer.parseInt(current_line[customerMap.get(fileHeader[5])]);
            ticketType = current_line[customerMap.get(fileHeader[6])]; // col 4 in original csv
            // Customer tempCustomer = customers.get(getCustomerIndex(customers, firstName, lastName));
            // Event tempEvent = events.get(getEventIndex(events, eventID));
            while(true){
                if(action.equalsIgnoreCase("Buy")){ // check action is to buy
                    if(ticketQuantity > 6 || ticketQuantity <= 0){
                        break;
                    }// next check if customer and event exist
                    if(getCustomerIndex(customers, firstName, lastName) < 0 || getEventIndex(events, eventID) < 0){
                        break;
                    }
                    Customer tempCustomer = customers.get(getCustomerIndex(customers, firstName, lastName));
                    Event tempEvent = events.get(getEventIndex(events, eventID));
                    if(tempEvent.getVenueTierCapacityName(ticketType) > ticketQuantity){ // verify ticket stock
                        double total_price, savings = 0;
                        if(tempCustomer.getTicketMasterMember()){ // if ticketminermember
                            double subtotal_price = ticketQuantity * tempEvent.getEventTierPriceName(ticketType);
                            savings = subtotal_price*(.10);
                            savings = Math.round(savings*100);
                            savings = savings/100;
    
                            subtotal_price -= savings;
                            double tax = (subtotal_price*(0.0825));
                            total_price = subtotal_price + tax;
                            if(tempCustomer.isSufficent(total_price)){
                                tempEvent.addAmountDiscounted(savings);
                                tempCustomer.addCustomerSavings(savings);
                                // tempCustomer.addToElectronicTicket(Ticket.ticketId, null);
                            }
                        } else { // if not ticket miner member
                        total_price = ticketQuantity * tempEvent.getEventTierPriceName(ticketType);
                        double tax = total_price * (0.0825);
                        total_price += tax;
                        }
                        if(tempCustomer.isSufficent(total_price)){ // verify user has enough $
                            tempCustomer.withdraw(total_price); // update user balance
                            for(int i = 0; i < ticketQuantity; i++){ // Append tickets to customer
                                tempCustomer.addPurchasedTickets(tempEvent.vendTicketName(ticketType));
                            }
                            tempCustomer.addToElectronicTicket(tempEvent.vendTicketName(ticketType));
                        }
                        else{ // insufficent funds
                            break;
                        }
                    }
                } 
                break; // end inner while loop
            }
        } // end outer while loop
        fs.close();
    }
    
    /* Method responsible for getting index of customer in customer list with first and last name */
    public static int getCustomerIndex(ArrayList<Customer> customers, String fname, String lname){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getFirstName().equalsIgnoreCase(fname) && customers.get(i).getLastName().
            equalsIgnoreCase(lname)){ 
                return i;
            }
        } return -1;   
    }

    /* Method responsible for getting index of event from event list with event ID */
    public static int getEventIndex(ArrayList<Event> events, int eventID){
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getID() == eventID){
                return i;
            }
        } return -1;
    }

    /*Method is responsible for purchasing ticket(s) */
    public static void handleTicketPurchase(Scanner sc, ArrayList<Event> events, PrintWriter logs){
        System.out.print("Enter event id to purchase : ");
        try{
            int choice = sc.nextInt();
            logs.println(choice);
            choice = getEventIndex(events, choice);
            //check if eventID exist within the range of events size
            if(choice > events.size() || choice < 0){
                System.out.println("There is no event with ID '" + choice + "'");
                return;
            }
            Event event = events.get(choice - 1);
            System.out.println("You have selected : \n" + event);
            //print event and receive user confirmation
            System.out.print("Type yes to confirm, no to cancel : ");
            String confirm_choice = sc.next();
            logs.println(confirm_choice);
            if(confirm_choice.equalsIgnoreCase("no")){
                System.out.println("Cancel Confirmed!");
                return;
            } /*if user confirmation received, pritn ticket tiers with prices */
            if(confirm_choice.equalsIgnoreCase("yes")){
                System.out.println("Please select ticket tier");
                String tier = 
                    "1. VIP Ticket\t:\t$" + event.getEventTierPrice(0) + "\n" +
                    "2. Gold Ticket\t:\t$" + event.getEventTierPrice(1) + "\n" +
                    "3. Silver Ticket\t:\t$" + event.getEventTierPrice(2) + "\n" +
                    "4. Bronze Ticket\t:\t$" + event.getEventTierPrice(3) + "\n" +
                    "5. General Admission Ticket\t:\t$" + event.getEventTierPrice(4) + "\n" +
                    "Input :\t";
                System.out.print(tier);
                int tier_choice = sc.nextInt();
                logs.println(tier_choice);
                System.out.print("Please enter quanitiy of tickets [1-6]: ");
                int quanitiy = sc.nextInt();
                logs.println(quanitiy);
                /*Verify selects between 1 and 6 ticket quanity */
                if(quanitiy > 6 || quanitiy <= 0){
                    System.out.println("Can't sell " + quanitiy + " quantity!");
                    return;
                }
                if(event.getVenueTierCapacity(tier_choice-1) > quanitiy){ // verify ticket stock
                    double total_price, savings = 0;
                    if(current_user.getTicketMasterMember()){
                        double subtotal_price = quanitiy * event.getEventTierPrice(tier_choice - 1);
                        savings = subtotal_price*(.10);
                        savings = Math.round(savings*100);
                        savings = savings/100;

                        subtotal_price -= savings;
                        double tax = (subtotal_price*(0.0825));
                        total_price = subtotal_price + tax;
                        if(current_user.isSufficent(total_price)){
                            event.addAmountDiscounted(savings);
                            current_user.addCustomerSavings(savings);
                            System.out.println("Congratulations ticket miner member you are eligible for a 10% discount!, You saved $" + savings + "!");
                            System.out.println("You're total savings are: $" + current_user.getCustomerSavings());
                        }
                    } else {
                    total_price = quanitiy * event.getEventTierPrice(tier_choice - 1);
                    double tax = total_price * (0.0825);
                    total_price += tax;
                    }
                    if(current_user.isSufficent(total_price)){ // verify user has enough $
                        current_user.withdraw(total_price); // update user balance
                        // Append tickets to customer
                        for(int i = 0; i < quanitiy; i++){
                            current_user.addPurchasedTickets(event.vendTicket(tier_choice - 1));
                        }
                        System.out.println("Thank you for your purchase! Tickets have been added to your account.");
                    }
                    else{ // print insufficent funds message
                        System.out.println("Sorry Insufficent Funds!");
                        System.out.println("Try Again!");
                        return;
                    }
                }
                else{ // print ticket out of stock message
                    System.out.println("Sorry We're out of " +
                     tier.split("\n")[tier_choice-1].substring(2) + 
                    " for this event");
                }

            }
        }
        catch(InputMismatchException e){
            System.out.println(e.getMessage());
            System.out.println("Try Again!");
            return;
        }

    }
    /* Method is responsible for handling event by either event name or event ID
     * based on user input */
    public static Event handleEventView(Scanner sc, ArrayList<Event> events, PrintWriter logs){
        String option = """
                A. Inquire Event by ID
                B. Inquire Event by name
                Input :\t""";
        System.out.print(option);
        String choice = sc.next();
        logs.println(choice);
        try{
            switch(choice.toLowerCase()){
                case "a":
                    System.out.print("Enter ID : ");
                    int _id = sc.nextInt();
                    logs.println(_id);
                    return inquireEventByID(events, _id);
                case "b":
                    System.out.print("Enter name : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    logs.println(name);
                    return inquireEventByName(events, name);
                default:
                    return null;
            }
        }
        catch(InputMismatchException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*Method responsible for writing updated eventlist csv file */
    public static void writeUpdatedCSV(ArrayList<Event> events){
        FileWriter writer = null;
        // header line with name for all columns
        String header = """
            Event ID,\
            Event Type,\
            Name,\
            Date,\
            Time,\
            VIP Price,\
            Gold Price,\
            Silver Price,\
            Bronze Price,\
            General Admission Price,\
            Venue Name,\
            Pct Seats Unavailable,\
            Venue Type,\
            Capacity,\
            Cost,\
            VIP Pct,\
            Gold Pct,\
            Silver Pct,\
            Bronze Pct,\
            General Admission Pct,\
            Reserved Extra Pct,\
            Fireworks Planned,\
            Fireworks Cost,\
            VIP Seats Sold,\
            Gold Seats Sold,\
            Silver Seats Sold,\
            Bronze Seats Sold,\
            General Admission Seats Sold,\
            Total VIP Revenue,\
            Total Gold Revenue,\
            Total Silver Revenue,\
            Total Bronze Revenue,\
            Total General Admission Revenue""";
        try{
            writer = new FileWriter(new File("Updated_Events.csv"));
            writer.write(header + "\n");
            //iterate through events list and write to csv file with eventToCSV method
            for(Event event : events)
                writer.write(event.eventToCSV() + "\n");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        File logFile = new File("Log.txt");
        FileWriter wf = new FileWriter(logFile);
        PrintWriter logs = new PrintWriter(wf);

         //Reading CustomerListPA1.CSV
        ArrayList<Customer> customers = new ArrayList<>();
        if(!populateCustomers(customers, "CustomerListRandomPA4.csv")){
            return;
        }
        // Reading EventListPA1.CSV
        ArrayList<Event> events = new ArrayList<>();
        if(!populateEvents(events, "EventListPA4-A.csv")){
            return;
        }

        autoPurchasers(events, customers, "AutoPurchase10K.csv"); //autopurchase test
    

        // create updated CSV list
        writeUpdatedCSV(events);
        Scanner sc = new Scanner(System.in);
        //user login
        while(current_user == null){
            loginUser(customers, sc, logs);
            System.out.println();
        }
        System.out.println("Welcome to TicketMaster " + current_user.getFirstName());
        String userchoice;
        // print option for user to select
        String options = """

                    1. View Events
                    2. Purchase Tickets
                    3. View Purchased Tickets
                    4. View Electronic Tickets
                    """;
        // print admnin option if user is an admin
        if(current_user.isAdmin()){ 
            options += """
                    5. View Event Detail (ADMIN)
                    6. Add New Event (ADMIN)
                    """;
        }
        options += """
            * Note type \"exit\" to exit at any point.
            Input :\t""";
        boolean exit = false;
        /* user will have the option of choosing from the available options unless exited */
        while(!exit){
            System.out.print(options);
            userchoice = sc.nextLine();
            logs.println(userchoice);
            /* Either events will be printed, tickets will be purchased,
             * or list of tickets purchased will be printed or event details will
             * be printed depending on user input */
            switch(userchoice.toLowerCase()){
                case "1":
                    for(Event event: events)
                        System.out.println(event);
                    break;
                case "2":
                    handleTicketPurchase(sc,events, logs);
                    break;
                case "3":
                    current_user.viewPurchasedTickets();
                    break;
                case "4":
                    current_user.viewElectronicTicketKeys();
                    System.out.println("Select a Confirmation Number to see Electronic Ticket");
                    try{
                        String confirmationNumber = sc.nextLine();
                        logs.println(confirmationNumber);
                        if(!current_user.getElectronicTicket().containsKey(confirmationNumber)){
                            System.out.println("Confirmation number does not exist");
                            break;
                        }
                        current_user.viewElectronicTicketValues(confirmationNumber);
                        break;
                    } catch (InputMismatchException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    {
                        if(!current_user.isAdmin()) break;
                        Event event = handleEventView(sc, events, logs);
                        if(event == null) break;
                        event.printEventDetail();
                        break;
                    }
                case "6":
                    {
                        if(!current_user.isAdmin()) break;
                        AdminAddEvent(events, sc);
                        break;
                    }
                // program exited
                case "exit":
                    exit = true;
            }
        }
        System.out.println("Thanks for shopping " + current_user.getFirstName() + ". See you next time!");
        writeUpdatedCSV(events); // new csv file
        current_user = null;
        logs.close();
    }
}
    

