package system;

public class TrainTransport extends Transport {
    private boolean hasDining;
    private String regionType;
    private boolean hasTransfers = false;
    private String transferDescription = "None";

    public TrainTransport(
        String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn, int tid,
        boolean hd, String rt, boolean ht, String td 
    ){
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid);
        this.hasDining = hd;
        this.regionType = rt;
        this.hasTransfers = ht;
        this.transferDescription = td;
    }

    public void setHasDining(boolean hd){ this.hasDining = hd; }
    public void setRegionType(String rt){ this.regionType = rt; }
    public void setHasTransfers(boolean ht){ this.hasTransfers = ht; }
    public void setTransferDescription(String td){ this.transferDescription = td; }

    public boolean getHasDining(){ return this.hasDining; }
    public String getRegionType(){ return this.regionType; }
    public boolean getHasTransfers(){ return this.hasTransfers; }
    public String getTransferDesc(){ return this.transferDescription; }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+getTransportationId());
        System.out.println("Transportation Type: "+getTransportType());
        System.out.println("Geographical Info: "+getRegionType());
        System.out.println("Departure Time: "+getDepartureTime());
        System.out.println("Departure Location: "+getDepartureLocation());
        System.out.println("Arrival Destination: "+getArrivalDestination());
        System.out.println("Departure Country: "+getDepartureCountry());
        System.out.println("Arrival Country: "+getArrivalCountry());
        System.out.println("Ticket Price: "+getTicketPrice());
        System.out.println("Return Ticket Price: "+getRTicketPrice());
        System.out.println("Available Seats: "+getFreeSeats());
        System.out.println("Via Company: "+getCompanyName());
        if(getHasDining()){
            System.out.println("Train includes dining, you may request food.");
        }
        if(getHasTransfers()){
            System.out.println("Transfer: "+getTransferDesc());
        }
    }

    public void DisplayRelevantInfo(){
        System.out.println("----------------");
        System.out.println("ID: "+getTransportationId());
        System.out.println("Transport Type: "+getTransportType());
        System.out.println("Departure Time: "+getDepartureTime());
        System.out.println(getDepartureLocation()+" -> "+getArrivalDestination());
        if(getHasTransfers()){
            System.out.println("Transfer: "+getTransferDesc());
        }
        System.out.println("PRICE: "+getTicketPrice()+" RETURN TICKET: "+getRTicketPrice());
        System.out.println("Transportation provided by: "+getCompanyName());
        System.out.println("----------------");
        System.out.println("");
    }
}
