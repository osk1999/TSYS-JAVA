package system;

public class AirTransport extends Transport {
    private boolean hasPriority;
    private String regionType;

    public AirTransport(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn, int tid,
    boolean hp, String rt){
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid);
        this.hasPriority = hp;
        this.regionType = rt;
    }

    public void setHasPriority(boolean hp){ this.hasPriority = hp; }
    public void setRegionType(String rt){ this.regionType = rt; }

    public boolean getHasPriority(){ return this.hasPriority; }
    public String getRegionType(){ return this.regionType; }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+getTransportationId());
        System.out.println("Transportation Type: "+getTransportType());
        System.out.println("Departure Time: "+getDepartureTime());
        System.out.println("Departure Location: "+getDepartureLocation());
        System.out.println("Arrival Destination: "+getArrivalDestination());
        System.out.println("Departure Country: "+getDepartureCountry());
        System.out.println("Arrival Country: "+getArrivalCountry());
        System.out.println("Ticket Price: "+getTicketPrice());
        System.out.println("Return Ticket Price: "+getRTicketPrice());
        System.out.println("Available Seats: "+getFreeSeats());
        System.out.println("Via Company: "+getCompanyName());
        if(getHasPriority()){
            System.out.println("Notice: People included on the priority list may come aboard the plane sooner.");
        }
    }
}
