package app.packages;

import java.util.ArrayList;

public class reciever { //recieves info from queries
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
        return sent_queries.toArray(new query[sent_queries.size()]);
    }

    query[] get_accepted_queries() {
        return accepted_queries.toArray(new query[accepted_queries.size()]);
    }

    void clear_sent_queries() { //only use when accepted_queries is still in use
        sent_queries = new ArrayList<>();
    }
}
