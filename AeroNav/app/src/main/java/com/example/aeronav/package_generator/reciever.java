import java.util.ArrayList;

public class reciever {
    ArrayList<query> pending;
    ArrayList<query> confirmed;

    public reciever() {
        pending = new ArrayList<query>();
        confirmed = new ArrayList<query>();
    }

    public void add_query(query item) {
        pending.add(item);
    }

    public void accept_query(int index) {
        confirmed.add(pending.remove(index));
    }

    public query[] get_accepted_queries() {
        return confirmed.toArray(new query[confirmed.size()]);
    }
}
