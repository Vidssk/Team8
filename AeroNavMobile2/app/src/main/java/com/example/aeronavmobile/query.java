package com.example.aeronavmobile;


import java.time.LocalDateTime;

public class query {    //however API queries should be formatted
    String name;
    LocalDateTime date_time;
    int length;
    String location;
    int price;

    query() {
        name = "No name specified";
        date_time = LocalDateTime.now();
        length = 0;
        location = "No location specified";
        price = 0;
    }

    String to_String() {
        return "Name: " + name
                + "\nDate & Time: " + date_time
                + "\nLength: " + length
                + "\nLocation: " + location
                + "\n Price: " + price;
    }

    rec to_rec() {
        return new rec(name, date_time, length, location, price);
    }
}

