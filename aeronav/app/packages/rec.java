package app.packages;

import java.time.LocalDateTime;

public class rec {
    private String name;
    private LocalDateTime date_time;
    private int length; //in minutes
    private String location;
    private int price;    //in cents

    rec(String name, LocalDateTime date_date, int length, String location, int price) {
        this.name = name;
        this.date_time = date_date;
        this.length = length;
        this.location = location;
        this.price = price;
    }

    String get_name() {
        return name;
    }

    LocalDateTime get_date_time() {
        return date_time;
    }

    int get_length() {
        return length;
    }

    String get_location() {
        return location;
    }

    int get_price() {
        return price;
    }

    String to_String() {    //change to whatever is needed
        return name
            + "\nLocation: " + location
            + "\nDate & Time: " + date_time
            + "\nPrice: " + price;
    }
}
