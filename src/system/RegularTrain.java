package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegularTrain extends TrainTransport {
    private boolean hasWifi;

    public RegularTrain(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs, String cn,
            int tid, boolean hd, String rt, boolean ht, String td, boolean hwf) {
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid, hd, rt, ht, td);
        this.hasWifi = hwf;
    }

    public void setHasWIFI(boolean hwf){ this.hasWifi = hwf; }
    public boolean getHasWIFI(){ return this.hasWifi; }
    public void setHasWIFIManual() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set WIFI Status (on/off)>");
        String wifistatus = inp.readLine();

        switch(wifistatus){
            case "on":
                this.hasWifi = true;
                System.out.println("[+] WIFI is ON");
                break;
            case "off":
                this.hasWifi = false;
                System.out.println("[+] WIFI is OFF");
                break;
            default:
                System.out.println("[-] Invalid input provided");
                break;
        }
    }

    public void DisplayInfo(){
        System.out.println("Transport ID: "+getTransportationId());
        System.out.println("Transportation Type: "+getTransportType());
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
        if(getHasDining()){
            System.out.println("Train includes dining, you may request food.");
        }
        if(getHasTransfers()){
            System.out.println("Transfer: "+getTransferDesc());
        }
    }
}
