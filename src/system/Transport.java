package system;

import java.io.Serializable;

public class Transport implements Serializable{
    private String transportType;
    private String departTime;
    private String departureLocation;
    private String arrivalDestination;
    private String departCountry;
    private String arrivalCountry;
    private double ticketPrice;
    private double rTicketPrice;
    private int freeSeats;
    private String companyName;
    private int transportationId;

    public Transport(){}
    public Transport(
        String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn, int tid
    ){
        this.transportType = tt;
        this.departTime = dt;
        this.departureLocation = dl;
        this.arrivalDestination = ad;
        this.departCountry = dc;
        this.arrivalCountry = ac;
        this.ticketPrice = tp;
        this.rTicketPrice = rtp;
        this.freeSeats = fs;
        this.companyName = cn;
        this.transportationId = tid;
    }

    public void setTransportType(String tt){ this.transportType = tt; }
    public void setDepartureTime(String dt){ this.departTime = dt; }
    public void setDepartureLocation(String dl){ this.departureLocation = dl; }
    public void setArrivalDestination(String ad){ this.arrivalDestination = ad; }
    public void setDepartureCountry(String dc){ this.departCountry = dc; }
    public void setArrivalCountry(String ac){ this.arrivalCountry = ac; }
    public void setTicketPrice(double tp){ this.ticketPrice = tp; }
    public void setRTicketPrice(double rtp){ this.rTicketPrice = rtp; }
    public void setFreeSeats(int fs){ this.freeSeats = fs; }
    public void setCompanyName(String cn){ this.companyName = cn; }
    public void setTransportId(int tid){ this.transportationId = tid; }

    public String getTransportType(){ return this.transportType; }
    public String getDepartureTime(){ return this.departTime; }
    public String getDepartureLocation(){ return this.departureLocation; }
    public String getArrivalDestination(){ return this.arrivalDestination; }
    public String getDepartureCountry(){ return this.departCountry; }
    public String getArrivalCountry(){ return this.arrivalCountry; }
    public double getTicketPrice(){ return this.ticketPrice; }
    public double getRTicketPrice(){ return this.rTicketPrice; }
    public int getFreeSeats(){ return this.freeSeats; }
    public String getCompanyName(){ return this.companyName; }
    public int getTransportationId(){ return this.transportationId; }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+this.transportationId);
        System.out.println("Transportation Type: "+this.transportType);
        System.out.println("Departure Time: "+this.departTime);
        System.out.println("Departure Location: "+this.departureLocation);
        System.out.println("Arrival Destination: "+this.arrivalDestination);
        System.out.println("Departure Country: "+this.departCountry);
        System.out.println("Arrival Country: "+this.arrivalCountry);
        System.out.println("Ticket Price: "+this.ticketPrice);
        System.out.println("Return Ticket Price: "+this.rTicketPrice);
        System.out.println("Available Seats: "+this.freeSeats);
        System.out.println("Via Company: "+this.companyName);
    }

    public void DisplayRelevantInfo(){
        System.out.println("----------------");
        System.out.println("ID: "+getTransportationId());
        System.out.println("Transport Type: "+getTransportType());
        System.out.println("Departure Time: "+getDepartureTime());
        System.out.println(getDepartureLocation()+" -> "+getArrivalDestination());
        System.out.println("PRICE: "+getTicketPrice()+" RETURN TICKET: "+getRTicketPrice());
        System.out.println("Transportation provided by: "+getCompanyName());
        System.out.println("----------------");
        System.out.println("");
    }
}