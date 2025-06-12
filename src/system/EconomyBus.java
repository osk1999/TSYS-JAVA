package system;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

public class EconomyBus extends BusTransport {
    private boolean hasWIFI;

    public EconomyBus(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn,
            int tid, String bt, boolean ht, String td, String rt, boolean hwf) {
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid, bt, ht, td, rt);
        this.hasWIFI = hwf;
    }

    public void setHasWIFI(boolean hwf){ this.hasWIFI = hwf; }
    public boolean getHasWIFI(){ return this.hasWIFI; }
    public void setHasWIFIManual() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set WIFI Status (on/off)>");
        String wifistatus = inp.readLine();

        switch(wifistatus){
            case "on":
                this.hasWIFI = true;
                System.out.println("[+] WIFI is ON");
                break;
            case "off":
                this.hasWIFI = false;
                System.out.println("[+] WIFI is OFF");
                break;
            default:
                System.out.println("[-] Invalid input provided");
                break;
        }
    }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+getTransportationId());
        System.out.println("Transportation Type: "+getTransportType()+" | Bus Type: "+getBusType());
        if(getHasWIFI()){
            System.out.println("WIFI: Included");
        }
        else{
            System.out.println("WIFI: Not Included");
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
        if(getHasTransfers() == true){
            System.out.println("Transfers: "+getTransferDesc());
        }
    }
}
