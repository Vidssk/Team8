package com.example.aeronavmobile;
import java.time.LocalDate;
import java.time.Month;

//Code to Generate Package
public class generage_package {
        void run(String location, int budget, LocalDateTime start_date_time, LocalDateTime end_date_time) {

            sender send = generate_sender(location, budget, start_date_time, end_date_time);
            transportation trans = send.create_transportation(start_date_time, end_date_time);
            
            send.send();

            //get queries from API
            ArrayList<query> queries = new ArrayList();

            reciever recieve = new reciever();
            for(query item: queries) {
                recieve.add_query(item);
            }


        }

        sender generate_sender(String location, int budget, LocalDateTime start_date_time, LocalDateTime end_date_time) {
            sender send = new sender();
            send.update_location(location);
            send.update_budget(budget);
            send.update_date(start_date_time, end_date_time);
            return send;
        }

        void initial_demo() {   //used to test while building
            sender send = new sender();

            //these would be filled in by the survey
            send.update_location("Dallas");
            send.update_budget(10000);  //in cents
            send.update_date(LocalDate.of(2024, Month.JUNE, 12), LocalDate.of(2024, Month.JUNE, 18));

            send.send();

            transportation trans = new transportation(
                    send.get_start_date().atTime(12, 30),
                    send.get_end_date().atTime(16, 0),
                    15,
                    15,
                    send.get_location(),
                    send.get_budget()
            );

            //API stuff happens

            reciever recieve = new reciever();

            //for(query item: returned queries)
            recieve.add_query(new query() /*item*/);

            recieve.accept_query(0);    //this will accept the 1 query input rn

            packager pack = new packager(trans);

            for(query item: recieve.get_accepted_queries())
                pack.add_rec(item.to_rec());

            if(pack.check_validity())
                System.out.print("Valid: ");
            else
                System.out.print("Invalid: ");

            travel_package tp = pack.export();  //exports to chosen travel_package

            System.out.println(tp.to_string());
        }
}
