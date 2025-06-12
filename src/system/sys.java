package system;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.time.LocalDate;
import java.util.ArrayList;

public class sys{
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException{
        sys mc = new sys();
        File transFile = new File("transport.txt");
        if(!transFile.exists()){
            transFile.createNewFile();
        }
        BufferedReader fileRead = new BufferedReader(new FileReader(transFile));
        if(fileRead.readLine() == null){
            mc.init();
            fileRead.close();
        }
        File ticketFile = new File("tickets.txt");
        if(!ticketFile.exists()){
            ticketFile.createNewFile();
        }

        String command;
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        println("Welcome to the TSYS Control Board, enter '?' to see availible options:");
        while(true){
            print(">");
            command = inp.readLine();

            switch(command){
                case "?" -> {
                    println("(BUY TICKET) -> BUY");
                    println("(SHOW CURRENT TICKETS) -> PRINT");
                    println("(REFUND TICKET/CANCEL TICKET) -> CANCEL");
                    println("(MANAGE TRANSPORTATION DATABASE) -> MDB");
                    println("(VIEW TRANSPORT DATABASE) -> SHOWDB");
                    println("(EXIT APPLICATION) -> EXIT");
                    println("");
                }
                case "BUY" -> {
                    buyTickets();
                }
                case "CANCEL" -> {
                    CancelTravel();
                }
                case "PRINT" -> {
                    printAllTickets();
                }
                case "SHOWDB" -> {
                    mc.printInfoAll();
                }
                case "MDB" -> {
                    ManageDB();
                }
                case "EXIT" -> {
                    System.exit(0);
                }
            }
        }
    }

    public static void CancelTravel() throws NumberFormatException, IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Ticket> allTickets = new ArrayList<>();

        println("You've decided to refund/cancel a purchased ticket, please provide the required information:");
        print("TICKET ID >");
        int tid = Integer.parseInt(inp.readLine());
        try{
            FileInputStream fis = new FileInputStream("tickets.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Ticket currObj = (Ticket) ois.readObject();
                if(currObj != null){
                    allTickets.add(currObj);
                }
                else{
                    cont = false;
                }
            }
            fis.close();
            ois.close();
        }
        catch(EOFException e){
            //Do nothing...
        }

        FileOutputStream fos = new FileOutputStream("tickets.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(Ticket tkts : allTickets){
            if(tkts.getTicketId() != tid){
                oos.writeObject(tkts);
                oos.flush();
            }
        }
        fos.close();
        oos.close();
    }

    public static void buyTickets() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        println("You've decided to purchase a ticket, please enter the required information below");
        println("---------------");
        println("TYPE OF TRANSPORT AVAILABLE:");
        println("AIR: Economy Flight - First Class Flight");
        println("TRAIN: Regular Train - High Speed Train");
        println("BUS: Economy Bus - Luxury Bus");
        println("BOAT/FERRY: FERRY");
        println("(please input the type of transport as stated above)");
        print("TRANSPORT TYPE >");
        String tt = inp.readLine();
        print("DEPARTURE LOCATION >");
        String depLoc = inp.readLine();
        print("ARRIVAL DESTINATION >");
        String arrivDest = inp.readLine();
        print("DATE OF DEPARTURE (format: yyyy-mm-dd)>");
        String date = inp.readLine();
        print("NUMBER OF PASSANGERS >");
        int passNum = Integer.parseInt(inp.readLine());

        ArrayList<Transport> allTran = showAvailableTransport(tt, depLoc, arrivDest, date, passNum);
        if(allTran.isEmpty()){
            println("No availible transports");
            return;
        }
        else{
            for(Transport t : allTran){
                t.DisplayRelevantInfo();
            }
        }

        println("Please choose which ticket you'd like to buy");
        print("TRANSPORT ID >");
        int tid = Integer.parseInt(inp.readLine());
        
        println("Would you like to purchase a return ticket? (YES/NO)");
        print(">");
        String returnTicketQM = inp.readLine();
        String rtd = "";

        switch(returnTicketQM){
            case "YES" -> {
                println("Please provide the following information to complete your purchase");
                print("RETURN DATE >");
                rtd = inp.readLine();
                print("FIRST NAME >");
                String fn = inp.readLine();
                print("LAST NAME >");
                String ln = inp.readLine();
                print("CARD NUMBER (no white spaces)>");
                int ccn = Integer.parseInt(inp.readLine());
                print("CARD CVS >");
                int cvs = Integer.parseInt(inp.readLine());
                print("CARD EXPIRATION DATE >");
                String ced = inp.readLine();
                boolean isReturn = true;

                finalizePurchase(tid, fn, ln, ccn, cvs, ced, depLoc, arrivDest, date, passNum, isReturn, rtd);
            }
            case "NO" -> {
                println("Please provide the following information to complete your purchase");
                print("FIRST NAME >");
                String fn = inp.readLine();
                print("LAST NAME >");
                String ln = inp.readLine();
                print("CARD NUMBER (no white spaces)>");
                int ccn = Integer.parseInt(inp.readLine());
                print("CARD CVS >");
                int cvs = Integer.parseInt(inp.readLine());
                print("CARD EXPIRATION DATE >");
                String ced = inp.readLine();
                boolean isReturn = false;

                finalizePurchase(tid, fn, ln, ccn, cvs, ced, depLoc, arrivDest, date, passNum, isReturn, rtd);
            }
        }
    }

    public static void finalizePurchase(int tid, String fn, String ln, int ccn, int cvs, String ced,
    String dl, String ad, String d, int pn, boolean ir, String rtd) throws IOException, ClassNotFoundException
    {
        Transport theTransport = new Transport();
        if(ir){
            try{
                FileInputStream fis = new FileInputStream("transport.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean cont = true;
                while(cont){
                    Transport currObj = (Transport) ois.readObject();
                    if(currObj != null){
                        if(
                            currObj.getTransportationId() == tid && currObj.getDepartureLocation().equals(dl) &&
                            currObj.getArrivalDestination().equals(ad) && currObj.getFreeSeats() >= pn &&
                            validDate(currObj.getDepartureTime(), d)
                        ){
                            theTransport = currObj;
                        }
                    }
                    else{
                        cont = false;
                    }
                }
                fis.close();
                ois.close();
            }
            catch(EOFException e){
                //Do nothing...
            }
            ReturnTicket returnTicket = new ReturnTicket(getTicketIndex(), fn, ln, ccn, cvs, ced, dl, ad, pn, d, theTransport.getRTicketPrice(), rtd);
            AddTicketEntry(returnTicket);
        }
        else{
            try{
                FileInputStream fis = new FileInputStream("transport.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean cont = true;
                while(cont){
                    Transport currObj = (Transport) ois.readObject();
                    if(currObj != null){
                        if(
                            currObj.getTransportationId() == tid && currObj.getDepartureLocation().equals(dl) &&
                            currObj.getArrivalDestination().equals(ad) && currObj.getFreeSeats() >= pn &&
                            validDate(currObj.getDepartureTime(), d)
                        ){
                            theTransport = currObj;
                        }
                    }
                    else{
                        cont = false;
                    }
                }
                fis.close();
                ois.close();
            }
            catch(EOFException e){
                //Do nothing...
            }
            Ticket regularTicket = new Ticket(getTicketIndex(), fn, ln, ccn, cvs, ced, dl, ad, pn, d, theTransport.getTicketPrice());
            AddTicketEntry(regularTicket);
        }
    }

    public static void AddTicketEntry(Ticket t) throws IOException, ClassNotFoundException{
        ArrayList<Ticket> allTickets = new ArrayList<>();

        try{
            FileInputStream fis = new FileInputStream("tickets.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Ticket currObj = (Ticket) ois.readObject();
                if(currObj != null){
                    allTickets.add(currObj);
                }
                else{
                    cont = false;
                }
            }
            fis.close();
            ois.close();
        }
        catch(EOFException e){
            //Do nothing...
        }
        allTickets.add(t);

        FileOutputStream fos = new FileOutputStream("tickets.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(Ticket tkts : allTickets){
            println("Adding "+tkts.getTicketId()+" to file");
            oos.writeObject(tkts);
            oos.flush();
        }
        fos.close();
        oos.close();
    }

    public static int getTicketIndex() throws IOException, ClassNotFoundException{
        int index = 1;
        try{
            FileInputStream fis = new FileInputStream("tickets.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Ticket currObj = (Ticket) ois.readObject();
                if(currObj != null){
                    index = currObj.getTicketId()+1;
                }
                else{
                    cont = false;
                }
            }
            fis.close();
            ois.close();
        }catch(EOFException e){
            //do nothing...
        }
        return index;
    }

    public static ArrayList<Transport> showAvailableTransport(String tt, String dl, String ad, String d, int pn) throws IOException, ClassNotFoundException{
        ArrayList<Transport> allTransport = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream("transport.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Transport currObj = (Transport) ois.readObject();
                if(currObj != null){
                    if(
                        currObj.getTransportType().equals(tt) &&
                        currObj.getDepartureLocation().equals(dl) && currObj.getArrivalDestination().equals(ad) &&
                        validDate(currObj.getDepartureTime(), d) && currObj.getFreeSeats() >= pn 
                    )
                    {
                        allTransport.add(currObj);
                    }
                }
                else{
                    cont=false;
                }
            }
            fis.close();
            ois.close();
        }
        catch(EOFException e){
            //Do nothing...
        }

        return allTransport;
    }

    public static boolean validDate(String trDate, String wantedDate){
        String[] date = trDate.split("/");
        String realDate = date[1];

        LocalDate trDateDate = LocalDate.parse(realDate);
        LocalDate wantedDateDate = LocalDate.parse(wantedDate);

        if(wantedDateDate.isEqual(trDateDate)){
            return true;
        }
        else{
            return false;
        }
    }

    public static void printAllTickets() throws IOException, ClassNotFoundException{
        BufferedReader fileRead = new BufferedReader(new FileReader("tickets.txt"));
        ArrayList<Ticket> allTickets = new ArrayList<>();

        if(fileRead.readLine() == null){
            println("NO TICKETS CURRENTLY");
        }
        else{
            try{
                FileInputStream fis = new FileInputStream("tickets.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                boolean cont = true;
                while(cont){
                    Ticket currObj = (Ticket) ois.readObject();
                    if(currObj != null){
                        allTickets.add(currObj);
                    }
                    else{
                        cont = false;
                    }
                }
                fis.close();
                ois.close();
            }
            catch(EOFException e){
                //Do nothing...
            }
            for(Ticket t : allTickets){
                t.printDetails();
            }
        }
        fileRead.close();
    }

    public static void ManageDB() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String comm;

        println("You've chosen to edit the current database, enter '?' for more info on availible options:");
        while(true){
            print(">");
            comm = inp.readLine();

            switch(comm){
                case "?" -> {
                    println("(ADD TRANSPORTATION ENTRY): ADD");
                    println("(REMOVE TRANSPORTATION ENTRY): REMOVE");
                    println("(RETURN TO MAIN DASHBOARD): RETURN");
                    println("(EXIT APPLICATION): EXIT");
                    println("");
                }
                case "ADD" -> {
                    AddEntry();
                }
                case "REMOVE" -> {
                    RemoveEntry();
                }
                case "RETURN" -> { return; }
                case "EXIT" -> { System.exit(0); }
            }
        }
    }

    public static void RemoveEntry() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        int id;
        ArrayList<Transport> allTrans = new ArrayList<>();

        println("You've decided to remove an entry from the list, please provide the ID:");
        print("ID >");
        id = Integer.parseInt(inp.readLine());
        
        try{
            FileInputStream fis = new FileInputStream("transport.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Transport currObj = (Transport) ois.readObject();
                if(currObj != null){
                    allTrans.add(currObj);
                }
                else{
                    cont = false;
                }
            }
            fis.close();
            ois.close();
        }catch(EOFException e){
            //do nothing...
        }

        FileOutputStream fos = new FileOutputStream("transport.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(Transport tr : allTrans){
            if(tr.getTransportationId() != id){
                oos.writeObject(tr);
                oos.flush();
            }
        }
        fos.close();
        oos.close();
    }

    public static void AddEntry() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        String tType;

        println("You've decided to add a new transportation entry, please provide the required information");
        println("Availible transporation categories:");
        println("BUSES:");
        println("   (REGULAR BUS): RB");
        println("   (LUXURY BUS): LB");
        println("PLANES:");
        println("   (ECONOMY): ECONAIR");
        println("   (FIRST CLASS): FIRSTCLASSAIR");
        println("TRAIN:");
        println("   (REGULAR TRAIN): RT");
        println("   (HIGH SPEED TRAIN): HST");
        println("(BOAT/FERRY): BOAT");
        println("(ROCKET): ROCKET");
        println("(RETURN TO PREVIOUS SECTION): RETURN");
        println("(EXIT APPLICATION): EXIT");
        print(">");
        tType = inp.readLine();

        switch(tType){
            case "RB":
                EconomyBus rb = newEconBus();
                appendEntry(rb);
                break;
            case "LB":
                LuxuryBus lb = newLuxBus();
                appendEntry(lb);
                break;
            case "ECONAIR":
                AirEconomy ae = newEconAir();
                appendEntry(ae);
                break;
            case "FIRSTCLASSAIR":
                AirFirstClass afc = newFCAir();
                appendEntry(afc);
                break;
            case "RT":
                RegularTrain rt = newRegTrain();
                appendEntry(rt);
                break;
            case "HST":
                HighSpeedTrain hst = newHSTrain();
                appendEntry(hst);
                break;
            case "BOAT":
                WaterTransport wt = newWTransport();
                appendEntry(wt);
                break;
            case "ROCKET":
                Rocket nr = newRocket();
                appendEntry(nr);
                break;
            case "RETURN":
                return;
            case "EXIT":
                System.exit(0);
        }
    }

    public static Rocket newRocket() throws IOException, ClassNotFoundException {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());
        print("Rocket landing information >");
        String landInfo = inp.readLine();

        int count = getAddIndex();

        Rocket newRocket = new Rocket("Rocket", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP, freeS,
        compName, count, geoD, landInfo);

        return newRocket;
    }

    public static WaterTransport newWTransport() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());

        int count = getAddIndex();

        WaterTransport wt = new WaterTransport("Boat/Ferry", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP,
        freeS, compName, count, geoD);
        return wt;
    }

    public static HighSpeedTrain newHSTrain() throws ClassNotFoundException, IOException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Does this train include dining (true/false) >");
        boolean hasD = Boolean.parseBoolean(inp.readLine());
        print("Does this train include private cabins? (true/false) >");
        boolean hpc = Boolean.parseBoolean(inp.readLine());
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());
        print("Does the ride contain any transfers? (true/false) >");
        boolean hasT = Boolean.parseBoolean(inp.readLine());
        String tranD = "None";
        if(hasT){
            print("Add description regarding transfer (Location A -> Location B via _means of transport_) >");
            tranD = inp.readLine();
        }

        int count = getAddIndex();

        HighSpeedTrain hst = new HighSpeedTrain("High Speed Train", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP,
        freeS, compName, count, hasD, geoD, hasT, tranD, hpc);
        return hst;
    }

    public static RegularTrain newRegTrain() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Does this train include dining (true/false) >");
        boolean hasD = Boolean.parseBoolean(inp.readLine());
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());
        print("Does the ride include WiFi? (true/false) >");
        boolean hwf = Boolean.parseBoolean(inp.readLine());
        print("Does the ride contain any transfers? (true/false) >");
        boolean hasT = Boolean.parseBoolean(inp.readLine());
        String tranD = "None";
        if(hasT){
            print("Add description regarding transfer (Location A -> Location B via _means of transport_) >");
            tranD = inp.readLine();
        }

        int count = getAddIndex();

        RegularTrain rt = new RegularTrain("Regular Train", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP,
        freeS, compName, count, hasD, geoD, hasT, tranD, hwf);
        return rt;
    }

    public static AirFirstClass newFCAir() throws ClassNotFoundException, IOException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("What types of benefits does the flight include (food, drink, parfumes etc.) >");
        String fcBen = inp.readLine();
        print("Does this flight contain a priority queue option (true/false) >");
        boolean hasPrio = Boolean.parseBoolean(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());

        int count = getAddIndex();

        AirFirstClass aFClass = new AirFirstClass("First Class Flight", depTime, depLoc, arrDest, depC, arrC,
        oneWayTP, returnTP, freeS, compName, count, hasPrio, geoD, fcBen);
        return aFClass;
    }

    public static AirEconomy newEconAir() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("What types of benefits does the flight include (food, drink, parfumes etc.) >");
        String econBen = inp.readLine();
        print("Does this flight contain a priority queue option (true/false) >");
        boolean hasPrio = Boolean.parseBoolean(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());

        int count = getAddIndex();

        AirEconomy ae = new AirEconomy("Economy Flight", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP, freeS,
        compName, count, hasPrio, geoD, econBen);
        return ae;
    }

    public static EconomyBus newEconBus() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());
        print("Does the ride include WiFi (true/false) >");
        boolean haswifi = Boolean.parseBoolean(inp.readLine());
        print("Does the ride contain any transfers? (true/false) >");
        boolean hasT = Boolean.parseBoolean(inp.readLine());
        String tranD = "None";
        if(hasT){
            print("Add description regarding transfer (Location A -> Location B via _means of transport_) >");
            tranD = inp.readLine();
        }

        int count = getAddIndex();
        
        EconomyBus regBus = new EconomyBus("Bus", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP, freeS, compName,
        count, "Economy Bus", false, tranD, geoD, haswifi);
        return regBus;
    }

    public static int getAddIndex() throws IOException, ClassNotFoundException{
        int index = 1;
        try{
            FileInputStream fis = new FileInputStream("transport.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Transport currObj = (Transport) ois.readObject();
                if(currObj != null){
                    index = currObj.getTransportationId()+1;
                }
                else{
                    cont = false;
                }
            }
            fis.close();
            ois.close();
        }catch(EOFException e){
            //do nothing...
        }
        return index;
    }

    public static LuxuryBus newLuxBus() throws IOException, ClassNotFoundException{
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        print("Departure Location >");
        String depLoc = inp.readLine();
        print("Arrival Destination >");
        String arrDest = inp.readLine();
        print("Departure Time (format: time_of_day - date) >");
        String depTime = inp.readLine();
        print("Departure Country >");
        String depC = inp.readLine();
        print("Arrival Country >");
        String arrC = inp.readLine();
        print("Geographical Info (Local, Intercity, International) >");
        String geoD = inp.readLine();
        print("Company Name >");
        String compName = inp.readLine();
        print("Ticket Price (One Way) >");
        double oneWayTP = Double.parseDouble(inp.readLine());
        print("Ticket Price (Return) >");
        double returnTP = Double.parseDouble(inp.readLine());
        print("Availible Seats >");
        int freeS = Integer.parseInt(inp.readLine());
        print("Does the ride include live service? (true/false) >");
        boolean hls = Boolean.parseBoolean(inp.readLine());
        print("Does the ride contain any transfers? (true/false) >");
        boolean hasT = Boolean.parseBoolean(inp.readLine());
        String tranD = "None";
        if(hasT){
            print("Add description regarding transfer (Location A -> Location B via _means of transport_) >");
            tranD = inp.readLine();
        }

        int count = getAddIndex();
        
        LuxuryBus luxBus = new LuxuryBus("Bus", depTime, depLoc, arrDest, depC, arrC, oneWayTP, returnTP, freeS, compName,
        count, "Luxury Bus", false, tranD, geoD, hls);
        return luxBus;
    }

    public static void appendEntry(Transport tr) throws IOException, ClassNotFoundException{
        ArrayList<Transport> allTrans = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream("transport.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean cont = true;
            while(cont){
                Transport currObj = (Transport) ois.readObject();
                if(currObj != null){
                    allTrans.add(currObj);
                }
                else{
                    cont = false;
                }
            }
            fis.close();
            ois.close();
        }catch(EOFException e){
            //do nothing
        }
        allTrans.add(tr);

        FileOutputStream fos = new FileOutputStream("transport.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(Transport t : allTrans){
            oos.writeObject(t);
            oos.flush();
        }
        fos.close();
        oos.close();
    }

    public void init() throws IOException, URISyntaxException {
        EconomyBus econBus = new EconomyBus("Economy Bus", "19:00/2024-09-20",
         "Ljubljana", "Koper", "Slovenia", "Slovenia", 5.6, 10, 35, "Nomago",
         1, "Economy", true, "None", "Intercity", true);
        LuxuryBus luxBus = new LuxuryBus(
            "Luxury Bus", "12:00/2024-09-21", "Skopje", "Belgrade", "Macedonia",
            "Serbia", 40.50, 80.20, 12, "Arriva", 2, "Luxary Bus", false, "None", "International", true);
        RegularTrain regTrain = new RegularTrain("Regular Train", "15:48/2024-05-30", "Ljubljana", "Koper",
        "Slovenia", "Slovenia", 5.85, 10.90, 38, "Slovenske Zeleznice", 3, false,
        null, true, "Divaca -> Koper via BUS", true);
        WaterTransport ferryA = new WaterTransport("Boat", "20:00/2024-04-11", "Solun", "Istanbul",
        "Greece", "Turkey", 20.50, 40, 110, "IstanTravel", 4, "International");
        EconomyBus cityBus = new EconomyBus("Economy Bus", "11:30/2024-11-10", "Novo Mesto Kandijski", "Sentrenej",
        "Slovenia", "Slovenia", 5.6, 9.5, 8, "Nomago", 5, "Economy Bus", false, "None", "Local", true);
        AirEconomy econAir = new AirEconomy("Economy Flight", "14:40/2024-05-06", "Ljubljana", "Skopje",
        "Slovenia", "Macedonia", 70, 125, 34, "Wizzair", 6, true, "International", "Food and Drinks");
        AirFirstClass fcAir = new AirFirstClass("First Class Flight", "20:00/2024-04-19", "Skopje", "Ljubljana",
        "Macedonia", "Slovenia", 110.50, 166.50, 10, "QatarAir", 7, false, "International", "Parfumes, live service of food and drinks");
        RegularTrain regTrainB = new RegularTrain("Regular Train", "19:00/2024-06-06", "Koper", "Ljubljana",
        "Slovenia", "Slovenia", 5.67, 11, 20, "Slovenske Zeleznice", 8, false, "Intercity", true,
        "Koper -> Divaca via BUS", true);
        WaterTransport ferryB = new WaterTransport("Ferry", "9:00/2024-04-12", "Istanbul", "Solun",
        "Turkey", "Greece", 70.50, 110.20, 120, "IstanTravel", 9, "International");
        EconomyBus jspBus = new EconomyBus("Economy Bus", "13:00/2024-10-10", "Center", "Novo Lisice",
        "Macedonia", "Macedonia", 1.20, 2.40, 10, "JSP", 10, "Double Decker", false, "None", "Local", false);
        LuxuryBus lBus = new LuxuryBus("Luxury Bus", "22:30/2024-05-23", "Ljubljana", "Belgrade",
        "Slovenia", "Serbia", 59, 100, 12, "Arriva", 11, "First Class, Luxary Bus",
        false, "None", "International", false);
        AirEconomy econAirB = new AirEconomy("Economy Flight", "10:50/2024-10-12", "Skopje", "Valletta",
        "Macedonia", "Malta", 44.50, 80, 24, "Wizzair", 12, false, "International", "Food and Drinks");
        AirEconomy econAirC = new AirEconomy("Economy Flight", "23:20/2024-10-20", "Tallahassee", "Kansas",
        "Florida/USA", "Texas/USA", 50.50, 100, 11, "Delta", 13, true, "International", "Food and Drinks");
        RegularTrain rTrain = new RegularTrain("Regular Train", "15:40/2024-05-25", "Novo mesto", "Ljubljana",
        "Slovenia", "Slovenia", 7.6, 14.10, 37, "Slovenske Zeleznice", 14, false, "Intercity", true,
        "Novo mesto centar -> Novo mesto via BUS", false);
        HighSpeedTrain hst = new HighSpeedTrain("High Speed Train", "10:00/2024-04-18", "Tokyo", "Sendai",
        "Japan", "Japan", 12.50, 16.90, 46, "JPTravel", 15, true, "Intercity", false, "None", true);

        ArrayList<Transport> AllTransports = new ArrayList<>();
        AllTransports.add(econBus);
        AllTransports.add(luxBus);
        AllTransports.add(regTrain);
        AllTransports.add(ferryA);
        AllTransports.add(cityBus);
        AllTransports.add(econAir);
        AllTransports.add(fcAir);
        AllTransports.add(regTrainB);
        AllTransports.add(ferryB);
        AllTransports.add(jspBus);
        AllTransports.add(lBus);
        AllTransports.add(econAirB);
        AllTransports.add(econAirC);
        AllTransports.add(rTrain);
        AllTransports.add(hst);
        
        FileOutputStream fileOS = new FileOutputStream("transport.txt");
        ObjectOutputStream objOS = new ObjectOutputStream(fileOS);
        for(Transport tr : AllTransports){
            objOS.writeObject(tr);
            objOS.flush();
        }
        objOS.close();
        fileOS.close();
    }

    public void printInfoAll() throws URISyntaxException, IOException, ClassNotFoundException{
        try{
            FileInputStream is = new FileInputStream("transport.txt");
            ObjectInputStream objIS = new ObjectInputStream(is);
            boolean cont = true;
            while(cont){
                Transport obj = (Transport) objIS.readObject();
                if(obj != null){
                    obj.DisplayInfo();
                    System.out.println("");
                }
                else{
                    cont = false;
                }
            }
            objIS.close();
            is.close();
        }catch(EOFException e){
            return;
        }
    }

    public static void println(String comm){ System.out.println(comm); }
    public static void print(String comm){ System.out.print(comm); }
}