package app.packages;

import java.util.ArrayList;

public class reciever {
    ArrayList<query> sent_queries;
    ArrayList<query> accepted_queries;

    reciever() {
        sent_queries = new ArrayList<>();
        accepted_queries = new ArrayList<>();
    }

    void add_query(query q) {
        sent_queries.add(q);
    }

    void accept_query(int index) {  //moves query at index from sent to accepted
        accepted_queries.add(sent_queries.get(index));
        sent_queries.remove(index);
    }

    query[] get_sent_queries() {
        return (query[]) sent_queries.toArray();
    }

    query[] get_accepted_queries() {
        return (query[]) accepted_queries.toArray();
    }

    void clear_sent_queries() { //only use when accepted_queries is still in use
        sent_queries = new ArrayList<>();
    }
}
