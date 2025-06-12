package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LuxuryBus extends BusTransport {
    private boolean hasService;

    public LuxuryBus(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn,
            int tid, String bt, boolean ht, String td, String rt, boolean hsrv) {
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid, bt, ht, td, rt);
        this.hasService = hsrv;
    }

    public void setHasService(boolean hsrv){ this.hasService = hsrv; }
    public boolean getHasService(){ return this.hasService; }
    public void setHasServiceManual() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set service availability in this ride (available/unavailable)>");
        String service = inp.readLine();

        switch(service){
            case "available":
                this.hasService = true;
                System.out.println("[+] Service is set to AVAILABLE");
                break;
            case "unavailable":
                this.hasService = false;
                System.out.println("[+] Service is set to UNAVAILABLE");
                break;
            default:
                System.out.println("[-] Invalid input provided");
                break;
        }
    }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+getTransportationId());
        System.out.println("Transportation Type: "+getTransportType()+" | Bus Type: "+getBusType());
        if(getHasService()){
            System.out.println("Luxury Transport includes live service.");
        }
        else{
            System.out.println("Luxury Transport does not include live service.");
        }
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
        if(getHasTransfers()){
            System.out.println("Transfers: "+getTransferDesc());
        }
    }
}
