package app.packages;

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
        return (rec[]) recs.toArray();
    }

    private void sort_by_time() {
        recs.sort((o1, o2) -> o1.to_minutes() - o2.to_minutes());
    }
}
