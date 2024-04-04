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

    protected int to_minutes(LocalDateTime date_time) {
        return (date_time.getDayOfYear() * 24 + date_time.getHour()) * 60 + date_time.getMinute();
    }

    int to_minutes() {
        return (date_time.getDayOfYear() * 24 + date_time.getHour()) * 60 + date_time.getMinute();
    }

    Boolean overlap(rec recommendation) {   //returns true if current rec overlaps with supplied one
        int a_start = to_minutes(date_time);
        int a_end = to_minutes(date_time) + length;

        int b_start = to_minutes(recommendation.get_date_time());
        int b_end = to_minutes(recommendation.get_date_time()) + recommendation.get_length();
        
        return a_end > b_start || a_start < b_end;
    }
}
