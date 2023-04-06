public abstract class Venue {
    String venueType;
    String name;
    int vipCapacity;
    int goldCapacity;
    int silverCapacity;
    int bronzeCapacity;
    int generalAdmissionCapacity;
    int unavilableCapacity;
    int reservedCapacity;
    int totalCapacity;
    int vipSoldCount;
    int goldSoldCount;
    int silverSoldCount;
    int bronzeSoldCount;
    int generalSoldCount;
    int VIP_CAPACITY_CONSTANT;
    int GOLD_CAPACITY_CONSTANT;
    int SILVER_CAPACITY_CONSTANT;
    int BRONZE_CAPACITY_CONSTANT;
    int GENERAL_ADMISSION_CAPACITY_CONSTANT;
    int RESERVED_CAPACITY_CONSTANT;
    Venue(String nameIn,int vipCapacityIn,int goldCapacityIn,int silverCapacityIn,
        int bronzeCapacityIn,int generalAdmissionCapacityIn,int unavilableCapacityIn,
        int reservedCapacityIn,int totalCapacityIn){
            name = nameIn;
            this.vipCapacity = vipCapacityIn;
            this.goldCapacity = goldCapacityIn;
            this.silverCapacity = silverCapacityIn;
            this.bronzeCapacity = bronzeCapacityIn;
            this.generalAdmissionCapacity = generalAdmissionCapacityIn;
            this.unavilableCapacity = unavilableCapacityIn;
            this.reservedCapacity = reservedCapacityIn;
            
            VIP_CAPACITY_CONSTANT = vipCapacityIn;
            GOLD_CAPACITY_CONSTANT = goldCapacityIn;
            SILVER_CAPACITY_CONSTANT = silverCapacityIn;
            BRONZE_CAPACITY_CONSTANT = bronzeCapacityIn;
            GENERAL_ADMISSION_CAPACITY_CONSTANT = generalAdmissionCapacityIn;
            this.totalCapacity = totalCapacityIn;
    }
    /* PA4 implementation */
    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVipCapacity(int vipCapacity) {
        this.vipCapacity = vipCapacity;
    }
    public void setGoldCapacity(int goldCapacity) {
        this.goldCapacity = goldCapacity;
    }
    public void setSilverCapacity(int silverCapacity) {
        this.silverCapacity = silverCapacity;
    }
    public void setBronzeCapacity(int bronzeCapacity) {
        this.bronzeCapacity = bronzeCapacity;
    }
    public void setGeneralAdmissionCapacity(int generalAdmissionCapacity) {
        this.generalAdmissionCapacity = generalAdmissionCapacity;
    }
    public void setReservedCapacity(int reservedCapacity) {
        this.reservedCapacity = reservedCapacity;
    }
    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }
    public String getName() {
        return name;
    }
    public int getTotalCapacity() {
        return totalCapacity;
    }

    
    
}
    

