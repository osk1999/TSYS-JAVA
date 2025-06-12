package system;

public class ReturnTicket extends Ticket {
    private String returnDate;

    public ReturnTicket(int tid, String bfn, String bln, int bcn, int bcvs, String bced, String dl, String ad,
            int pn, String dd, double tp, String rtD) {
        super(tid, bfn, bln, bcn, bcvs, bced, dl, ad, pn, dd, tp);
        this.returnDate = rtD;
    }

    public void setRetDate(String rtd){ this.returnDate = rtd; }
    public String getRetDate(){ return this.returnDate; }

    public void printDetails(){
        System.out.println("TICKET #"+getTicketId());
        System.out.println("First Name: "+getBuyerFName());
        System.out.println("Last Name: "+getBuyerLName());
        System.out.println("Departing Location: "+getDepLocation());
        System.out.println("Arrival Destination: "+getArrivDest());
        System.out.println("Departure Date: "+getDepDate());
        System.out.println("Passangers: "+getPassangerNumber());
        System.out.println("PRICE: "+getTicketPrice()+" EUR");
        System.out.println("Return Date: "+getRetDate());
        System.out.println("");
    }
}
