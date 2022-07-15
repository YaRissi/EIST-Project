package de.tum.in.ase.insertteamnamehere;


import de.tum.in.ase.insertteamnamehere.model.PriceCategory;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);

        Restaurant r = new Restaurant("PetersPizza",
                "1000",
                new Point(4,4),
                "am Berg",
                RestaurantType.BAR_RESTAURANT,
                PriceCategory.AFFORDABLE,
                new HashSet<>(),
                new ArrayList<>());

    }
}
