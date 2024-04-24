//package com.example.aeronavmobile;

import java.time.LocalDateTime;

public class transportation extends rec {   //the time it takes to travel there and back
    private LocalDateTime departure_date;
    private LocalDateTime return_date;
    private int departure_length;
    private int return_length;

    transportation(LocalDateTime departure_date, LocalDateTime return_date, int departure_length, int return_length, String location, int price) {
        super("Travel", departure_date, departure_length, location, price);
        this.departure_date = departure_date;
        this.return_date = return_date;
        this.departure_length = departure_length;
        this.return_length = return_length;
    }

    LocalDateTime get_departure_date() {
        return departure_date;
    }

    LocalDateTime get_return_date_time() {
        return return_date;
    }

    int get_departure_length() {
        return departure_length;
    }

    int get_return_length() {
        return return_length;
    }

    String to_string() {    //change to what's necessary
        return "Departure Date & Time: " + departure_date
                + "\nTravel Time: " + departure_length
                + "\nReturn Date & Time: " + return_date
                + "\nTravel Time: " + return_length;
    }

    Boolean in_time(LocalDateTime date_time) {    //returns true if date_time is between arrival time and leaving time
        int start = to_minutes(departure_date) + departure_length;  //exact minute should arrive on trip
        int end = to_minutes(return_date); //exact minute to return from trip
        int cur = to_minutes(date_time); //exact minute of the year at specified time

        return start < cur && cur < end;
    }

    static int calculate_time(String start_location, String destination) {  //communicates with API's to get distance from starting location
        /*TODO*/
        return 0;
    }
}

