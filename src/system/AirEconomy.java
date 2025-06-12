package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class AirEconomy extends AirTransport {
    private String benefitsEcon;

    public AirEconomy(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn,
            int tid, boolean hp, String rt, String benE) {
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid, hp, rt);
        this.benefitsEcon = benE;
    }

    public void setBenefitsEcon(String ei){ this.benefitsEcon = ei; }
    public String getBenefitsEcon(){ return this.benefitsEcon; }
    public void setEconomyFlightBenefitsManual() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set the included benefits of the flight >");
        String benefits = inp.readLine();

        this.benefitsEcon = benefits;
    }
    
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
        if(getHasPriority()){
            System.out.println("Notice: People included on the priority list may come aboard the plane sooner.");
        }
        System.out.println("Benefits Include: "+getBenefitsEcon());
    }
}
