package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HighSpeedTrain extends TrainTransport {
    private boolean hasPrivateCabins;

    public HighSpeedTrain(String tt, String dt, String dl, String ad, String dc, String ac, double tp, double rtp, int fs,
            String cn, int tid, boolean hd, String rt, boolean ht, String td, boolean hpc) {
        super(tt, dt, dl, ad, dc, ac, tp, rtp, fs, cn, tid, hd, rt, ht, td);
        this.hasPrivateCabins = hpc;
    }
    
    public void setHasPrivateCabins(boolean hpc){ this.hasPrivateCabins = hpc; }
    public boolean getHasPrivateCabins(){ return this.hasPrivateCabins; }
    public void setHasPrivateCabinsManual() throws IOException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Set the status of inclusion/availability of private cabins in the ride");
        System.out.print("(YES/NO) >");
        String pcAvailable = inp.readLine();

        switch(pcAvailable){
            case "YES":
                this.hasPrivateCabins = true;
                System.out.println("[+] Private cabins are set to available/included");
                break;
            case "NO":
                this.hasPrivateCabins = false;
                System.out.println("[+] Private cabins are set to unavailable/not included");
            default:
                System.out.println("[-] Invalid input provided");
                break;
        }
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
        if(getHasDining()){
            System.out.println("Train includes dining, you may request food.");
        }
        if(getHasPrivateCabins()){
            System.out.println("Ride includes private cabins");
        }
        if(getHasTransfers()){
            System.out.println("Transfer: "+getTransferDesc());
        }
    }
}
