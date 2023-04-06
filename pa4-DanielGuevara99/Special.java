public class Special extends Event {
    public Special(int _idIn, String nameIn, String dateIn, String timeIn, double vipPriceIn, 
    double goldPriceIn, double silverPriceIn, double bronzePriceIn, 
    double generalAdmissionPriceIn, double venueCostIn, Venue venueIn, double amountDiscountedIn){
        super(_idIn, nameIn, dateIn, timeIn, vipPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, 
        generalAdmissionPriceIn, venueCostIn, venueIn, amountDiscountedIn);
        type = "Special";
    }
}
