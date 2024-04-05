package app.packages;

import java.util.ArrayList;

public class packager {
    private ArrayList<rec> recs;
    private transportation trans;

    packager(transportation transportation) {
        recs = new ArrayList<>();
        trans = transportation;
    }

    rec[] get_recs() {
        return (rec[]) recs.toArray();
    }

    transportation get_transportation() {
        return trans;
    }

    void add_rec(rec recommendation) {
        recs.add(recommendation);
    }

    int size() {
        return recs.size();
    }

    Boolean check_validity() {  //returns false if any of the times overlap or not within time of trip
        for(int i=0;i<recs.size();i++)
            for(int j=i+1;j<recs.size();j++)
                if(recs.get(i).overlap(recs.get(j)))    //overlap
                    return false;

        for(rec item: recs)
            if(!trans.in_time(item.get_date_time()))    //in time
                return false;

        return true;
    }

    travel_package export() {   //converts current array into travel_package
        return new travel_package(trans, recs);
    }
}
