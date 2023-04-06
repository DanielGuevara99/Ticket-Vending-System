public class Arena extends Venue{
    Arena(String nameIn,int vipCapacityIn,int goldCapacityIn,int silverCapacityIn,
    int bronzeCapacityIn,int generalAdmissionCapacityIn,int unavilableCapacityIn,
    int reservedCapacityIn,int totalCapacityIn){
            super(nameIn,vipCapacityIn,goldCapacityIn,silverCapacityIn,bronzeCapacityIn,
            generalAdmissionCapacityIn,unavilableCapacityIn,reservedCapacityIn,totalCapacityIn);
            venueType = "Arena";
    }
}
