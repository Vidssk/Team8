package app.packages;

import java.time.LocalDateTime;

public class transportation extends rec {   //the time it takes to travel there and back
    private LocalDateTime departure_date;
    private LocalDateTime return_date;
    private int departure_length;
    private int return_length;
    
    transportation(String name, LocalDateTime departure_date, LocalDateTime return_date, int departure_length, int return_length, String location, int price) {
        super(name, departure_date, departure_length, location, price);
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

    Boolean in_time(LocalDateTime date_time) {    //returns true if date_time is between arrival time and leaving time
        int start_day = departure_date.getDayOfYear();
        int start_hour = departure_date.getHour();
        int start_min = departure_date.getMinute();
        int start = (start_day * 24 + start_hour) * 60 + start_min + departure_length;  //exact minute should arrive on trip

        int end_day = return_date.getDayOfYear();
        int end_hour = return_date.getHour();
        int end_min = return_date.getMinute();
        int end = (end_day * 24 + end_hour) * 60 + end_min; //exact minute to return from trip

        int cur_day = date_time.getDayOfYear();
        int cur_hour = date_time.getHour();
        int cur_min = date_time.getMinute();
        int cur = (cur_day * 24 + cur_hour) * 60 + cur_min; //exact minute of the year at specified time

        return start < cur && cur < end;
    }
}
