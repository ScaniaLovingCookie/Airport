package com.example.christian.viennaeast.ADT;




import java.io.Serializable;

public class Crush implements Serializable{

    private String FirstName = "";
    private String LastName = "";
    private String IATA = "";
    private String IACO = "";
    private String Airline = "";
    private String Callsign = "";
    private String FlightNumber = "";
    private String Gate = "";
    private String Aircraft = "";

    private Event[] Events = new Event[0];

    private int index = 0;


    public Crush(String firstName, String lastName, String IATA, String IACO, String airline, String callsign, String flightNumber, String gate, String aircraft) {
        FirstName = firstName;
        LastName = lastName;
        this.IATA = IATA;
        this.IACO = IACO;
        Airline = airline;
        Callsign = callsign;
        FlightNumber = flightNumber;
        Gate = gate;
        Aircraft = aircraft;
    }

    public Crush(){
        FirstName = "";
        LastName = "";
        IATA = "";
        IACO = "";
        Airline = "";
        Callsign = "";
        FlightNumber = "";
        Gate = "";
        Aircraft = "";
    }

    public String getFirstName(){
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getIATA() {
        return IATA;
    }

    public String getIACO() {
        return IACO;
    }

    public String getAirline() {
        return Airline;
    }

    public String getCallsign() {
        return Callsign;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public String getGate() {
        return Gate;
    }

    public String getAircraft() {
        return Aircraft;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public void setIACO(String IACO) {
        this.IACO = IACO;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public void setCallsign(String callsign) {
        Callsign = callsign;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

    public void setGate(String gate) {
        Gate = gate;
    }

    public void setAircraft(String aircraft) {
        Aircraft = aircraft;
    }

    public int getIndex() { return index; }

    public void setIndex(int index) {
        this.index = index;
    }

    public Event[] getEvents() { return Events; }

    public void setEvents(Event[] events) { Events = events; }

    public Crush copy(){
        return new Crush(this.FirstName, this.LastName, this.IATA, this.IACO, this.Airline, this.Callsign, this.FlightNumber, this.Gate, this.Aircraft);
    }



    public class Event implements Serializable{

        private String Date = "";
        private int Type = 0;
        private String Info = "";

        public final String[] Types = {"Initial Approach", "Focus Lost", "Focus Resumed", "Departure for Pattern", "Approach from Pattern", "Apron Change", "Airspace Exit", "Airport Close", "Airport Open"};

        public Event(){

        }

        public Event(String date, int type) {
            Date = date;
            Type = type;
        }

        public String  getDateAsText(){
            String s = "";
            String s1, s2 = "", s3 = "";
            String s4 = "";


            if(Date.length() == 4){
                s2 = Date.substring(0,2);
                s3 = Date.substring(2,4);
            }else if(Date.length() == 6){
                s1 = Date.substring(0,2);
                s2 = Date.substring(2,4);
                s3 = Date.substring(4,6);
                s = s + s1 + ". ";
            }else{
                return s;
            }

            switch (s2){
                case "01":
                    s4  = "January";
                    break;
                case "02":
                    s4  = "February";
                    break;
                case "03":
                    s4  = "March";
                    break;
                case "04":
                    s4  = "April";
                    break;
                case "05":
                    s4  = "May";
                    break;
                case "06":
                    s4  = "June";
                    break;
                case "07":
                    s4  = "July";
                    break;
                case "08":
                    s4  = "August";
                    break;
                case "09":
                    s4  = "September";
                    break;
                case "10":
                    s4  = "October";
                    break;
                case "11":
                    s4  = "November";
                    break;
                case "12":
                    s4  = "December";
                    break;
            }
            s = s + s4 + " 20" + s3;

            return s;
        }

        public String getTypeAsText(){
            return Types[Type];
        }

        public String getDate() { return Date; }

        public void setDate(String date) { Date = date; }

        public int getType() { return Type; }

        public void setType(int type) { Type = type; }

        public String getInfo() { return Info; }

        public void setInfo(String info) { Info = info; }
    }

}
