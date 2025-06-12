package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AirFirstClass extends AirTransport {
    private String benefitsFC;


    public AirFirstClass(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn,
            int tid, boolean hp, String rt, String benFC) {
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid, hp, rt);
        this.benefitsFC = benFC;
    }

    public void setBenefitsFC(String benFC){ this.benefitsFC = benFC; }
    public String getBenefitsFC(){ return this.benefitsFC; }
    public void setFirstClassFlightBenefitsManual() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set the included benefits of the flight >");
        String benefits = inp.readLine();

        this.benefitsFC = benefits;
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
        System.out.println("Benefits Include: "+getBenefitsFC());
    }
}
