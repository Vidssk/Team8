package com.example.aeronavmobile;

import java.time.LocalDate;

public class sender {   //converts survey info into queries for API's
    private LocalDate[] date_range;
    private int budget;
    private String location;

    sender() {
        date_range = new LocalDate[2];
        budget = 0;
        location = "No Location Specified";
    }

    void update_date(LocalDate start, LocalDate end) {
        date_range[0] = start;
        date_range[1] = end;
    }

    void update_budget(int budget) {
        this.budget = budget;
    }

    void update_location(String location) {
        this.location = location;
    }

    LocalDate get_start_date() {
        return date_range[0];
    }

    LocalDate get_end_date() {
        return date_range[1];
    }

    int get_budget() {
        return budget;
    }

    String get_location() {
        return location;
    }

    String to_string() {    //feel free to modify this to make it more useful for display
        return "Location: " + location
                + "\nBudget: " + budget
                + "\nDate: " + date_range[0] + " - " + date_range[1];
    }

    query send() {  //finalize pre-process
        return new query(); //will be updated when API's have functionality
    }

    transportation create_transportation(LocalDateTime start, LocalDateTime end) {
        int trans_time = transportation::calculate_time(location);
        return new transportation(
                    start,
                    end,
                    trans_time,
                    trans_time,
                    location,
                    budget
            );
    }
}

