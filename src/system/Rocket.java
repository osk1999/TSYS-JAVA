package system;

public class Rocket extends Transport{
    private String landingInfo;
    private String regionType;

    public Rocket(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp,
            int fs, String cn, int tid, String rt, String landInf){
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid);
        this.regionType = rt;
        this.landingInfo = landInf;
    }

    public void setLandingInfo(String li){ this.landingInfo = li; }
    public void setRegionType(String rt){ this.regionType = rt; }

    public String getLandingInfo(){ return this.landingInfo; }
    public String getRegionType(){ return this.regionType; }

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
        System.out.println("Rocket Landing Info: "+getLandingInfo());
    }
}
