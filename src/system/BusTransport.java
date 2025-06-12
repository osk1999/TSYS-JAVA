package system;

public class BusTransport extends Transport {
    private String busType;
    private boolean hasTransfers = false;
    private String transferDescription = "None";
    private String regionType;

    public BusTransport(
        String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn, int tid, String bt, boolean ht,
        String td, String rt
    ){
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid);
        this.busType = bt;
        this.hasTransfers = ht;
        this.transferDescription = td;
        this.regionType = rt;
    }

    public void setBusType(String bt){ this.busType = bt; }
    public void setHasTransfers(boolean ht){ this.hasTransfers = ht; }
    public void setTransferDescription(String td){ this.transferDescription = td; }
    public void setRegionType(String rt){ this.regionType = rt; }

    public String getBusType(){ return this.busType; }
    public boolean getHasTransfers(){ return this.hasTransfers; }
    public String getTransferDesc(){ return this.transferDescription; }
    public String getRegionType(){ return this.regionType; }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+getTransportationId());
        System.out.println("Transportation Type: "+getTransportType()+" | Bus Type: "+getBusType());
        System.out.println("Departure Time: "+getDepartureTime());
        System.out.println("Departure Location: "+getDepartureLocation());
        System.out.println("Arrival Destination: "+getArrivalDestination());
        System.out.println("Departure Country: "+getDepartureCountry());
        System.out.println("Arrival Country: "+getArrivalCountry());
        System.out.println("Ticket Price: "+getTicketPrice());
        System.out.println("Return Ticket Price: "+getRTicketPrice());
        System.out.println("Available Seats: "+getFreeSeats());
        System.out.println("Via Company: "+getCompanyName());
        if(getHasTransfers()){
            System.out.println("Transfers: "+getTransferDesc());
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
