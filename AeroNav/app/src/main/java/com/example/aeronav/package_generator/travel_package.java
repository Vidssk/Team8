//package com.example.aeronavmobile;


import java.util.ArrayList;

public class travel_package {
    private transportation trans;
    private ArrayList<rec> recs;

    travel_package(transportation transportation, ArrayList<rec> recs) {
        trans = transportation;
        this.recs = recs;
        sort_by_time();
    }

    transportation get_transportation() {
        return trans;
    }

    rec[] get_recs() {
        return recs.toArray(new rec[recs.size()]);
    }

    private void sort_by_time() {
        recs.sort((o1, o2) -> o1.to_minutes() - o2.to_minutes());
    }

    String to_string() {    //change to whatever is necessary
        String out = "";
        for(int i=0;i<recs.size();i++)
            out += "\n" + i + ": " + recs.get(i).get_name() + " - " + recs.get(i).get_date_time();
        return "Departure - " + trans.get_departure_date() + out + "\nReturn - " + trans.get_return_date_time();
    }
}

