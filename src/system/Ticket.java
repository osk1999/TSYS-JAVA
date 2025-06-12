package system;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int ticketId;
    private String buyerFName;
    private String buyerLName;
    private int buyerCardNumber;
    private int buyerCVS;
    private String buyerCardExpDate;
    private String depLocation;
    private String arrivDestination;
    private int passangerNumber;
    private String depDate;
    private double ticketPrice;

    public Ticket(int tid, String bfn, String bln, int bcn, int bcvs, String bced, String dl, String ad, int pn, String dd, double tp){
        this.ticketId = tid;
        this.buyerFName = bfn;
        this.buyerLName = bln;
        this.buyerCardNumber = bcn;
        this.buyerCVS = bcvs;
        this.buyerCardExpDate = bced;
        this.depLocation = dl;
        this.arrivDestination = ad;
        this.passangerNumber = pn;
        this.depDate = dd;
        this.ticketPrice = tp;
    }

    public void setTicketId(int tid){ this.ticketId = tid; }
    public void setBuyerFName(String fn){ this.buyerFName = fn; }
    public void setBuyerLName(String ln){ this.buyerLName = ln; }
    public void setBuyerCardNumber(int cn){ this.buyerCardNumber = cn; }
    public void setBuyerCVS(int cvs){ this.buyerCVS = cvs; }
    public void setBuyerCExpDate(String ed){ this.buyerCardExpDate = ed; }
    public void setDepLocation(String dl){ this.depLocation = dl; }
    public void setArrivDest(String ad){ this.arrivDestination = ad; }
    public void setPassangerNumber(int pn){ this.passangerNumber = pn; }
    public void setDepDate(String dd){ this.depDate = dd; }
    public void setTicketPrice(double tp){ this.ticketPrice = tp; }

    public int getTicketId(){ return this.ticketId; }
    public String getBuyerFName(){ return this.buyerFName; }
    public String getBuyerLName(){ return this.buyerLName; }
    public int getBuyerCardNumber(){ return this.buyerCardNumber; }
    public int getBuyerCVS(){ return this.buyerCVS; }
    public String getBuyerCardEXPDate(){ return this.buyerCardExpDate; }
    public String getDepLocation(){ return this.depLocation; }
    public String getArrivDest(){ return this.arrivDestination; }
    public int getPassangerNumber(){ return this.passangerNumber; }
    public String getDepDate(){ return this.depDate; }
    public double getTicketPrice(){ return this.ticketPrice; }

    public void printDetails(){
        System.out.println("TICKET #"+getTicketId());
        System.out.println("First Name: "+getBuyerFName());
        System.out.println("Last Name: "+getBuyerLName());
        System.out.println("Departing Location: "+getDepLocation());
        System.out.println("Arrival Destination: "+getArrivDest());
        System.out.println("Departure Date: "+getDepDate());
        System.out.println("Passangers: "+getPassangerNumber());
        System.out.println("PRICE: "+getTicketPrice()+" EUR");
        System.out.println("");
    }
}
