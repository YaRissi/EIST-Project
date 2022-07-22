package de.tum.in.ase.insertteamnamehere.model;

import de.tum.in.ase.insertteamnamehere.util.Coord;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public Database() {
        this.restaurants = initialiseRestaurants();
    }

    private List<Restaurant> restaurants = new ArrayList<>();

    public List<Restaurant> initialiseRestaurants(){
        Restaurant r1 = new Restaurant
                ("Gate- kitchen",Restaurant.generaterandomCoord(),"Lichtenbergstraße 8", RestaurantType.BAR_RESTAURANT, PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r1);
        Restaurant r2 = new Restaurant
                ("Lokitos", Restaurant.generaterandomCoord(),"Sonnenweg 21",RestaurantType.BAR_RESTAURANT, PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r1);
        Restaurant r3 = new Restaurant
                ("The FACULTY",Restaurant.generaterandomCoord(),"Walter- von- Dyck Str 12",RestaurantType.CAFE,PriceCategory.AFFORDABLE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r3);
        Restaurant r4 = new Restaurant
                ("Cafe Herr Lichtenberg",Restaurant.generaterandomCoord(),"Lichtenbergerstr 6",RestaurantType.CAFE, PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r4);
        Restaurant r5 = new Restaurant
                ("Olymps Restaurant", Restaurant.generaterandomCoord(),"Wielandstraße 3",RestaurantType.BAR_RESTAURANT,PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r5);
        Restaurant r6 = new Restaurant
                ("Garchinger Augustiner",Restaurant.generaterandomCoord(),"Freisinger Landstraße 4",RestaurantType.GERMAN,PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r6);
        Restaurant r7 = new Restaurant
                ("Poseidon",Restaurant.generaterandomCoord(),"Freisinger Landtraße 3",RestaurantType.BURGER,PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r7);
        Restaurant r8 = new Restaurant
                ("Onkel Luu`s Imbiss", Restaurant.generaterandomCoord(),"Boltzmannstraße 11",RestaurantType.CAFE,PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r8);
        Restaurant r9 = new  Restaurant
                ("The lonely Broccoli & Terrasse", Restaurant.generaterandomCoord(),"Leopoldstraße 170",RestaurantType.VEGETARIAN,PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r9);
        Restaurant r10 = new Restaurant
                ("El Greco", Restaurant.generaterandomCoord(),"Schleßheimer Str. 17", RestaurantType.GREEK, PriceCategory.AVERAGE, null,Restaurant.generateOpeningTimes());
        addRestaurant(r10);
        Restaurant r11= new Restaurant
                ("Nano Sushi",Restaurant.generaterandomCoord(),"Dieselstraße 28",RestaurantType.SUSHI_BAR,PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r11);
        Restaurant r12= new Restaurant
                ("Arabisch Indisches Restaurant Akrams Eching", Restaurant.generaterandomCoord(),"Obere Hauptstraße 6",RestaurantType.INDIAN, PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r12);
        Restaurant r13= new Restaurant
                ("Ristorante Pizzeria Da Umberto", Restaurant.generaterandomCoord(),"Schleißheimerstr. 40",RestaurantType.PIZZA, PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r13);
        Restaurant r14= new Restaurant
                ("Ristorante Pizzeria Roma", Restaurant.generaterandomCoord(),"Rathausplatz 7",RestaurantType.PIZZA, PriceCategory.AFFORDABLE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r14);
        Restaurant r15= new Restaurant
                ("Rabiang Thai Restaurant",Restaurant.generaterandomCoord(),"Georgenschwaigstrße 25",RestaurantType.THAI,PriceCategory.AVERAGE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r15);
        Restaurant r16= new Restaurant
                ("La Boheme Schwabing",Restaurant.generaterandomCoord(),"Leopoldstraße 180",RestaurantType.BAR_RESTAURANT,PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r16);
        Restaurant r17= new Restaurant(
                "Hans im Glück",Restaurant.generaterandomCoord(),"Leopoldstraße 250", RestaurantType.BURGER,PriceCategory.AVERAGE, null,Restaurant.generateOpeningTimes());
        addRestaurant(r17);
        Restaurant r18= new Restaurant
                ("Longgrain Thai Cuisine", Restaurant.generaterandomCoord(),"Belgradstraße 45", RestaurantType.THAI,PriceCategory.AFFORDABLE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r18);
        Restaurant r19= new Restaurant
                ("Trattoria La Piazza",Restaurant.generaterandomCoord(),"Kölner Platz 7",RestaurantType.ITALIAN, PriceCategory.EXPENSIVE,null,Restaurant.generateOpeningTimes());
        addRestaurant(r19);
        Restaurant r20= new Restaurant
                ("Korfu bei Dimi", Restaurant.generaterandomCoord(),"Lerchenauerstr 14", RestaurantType.GREEK,PriceCategory.AVERAGE, null,Restaurant.generateOpeningTimes());
        addRestaurant(r20);

        //1
        Restaurant takumiViktualienmarkt = new Restaurant("Takumi München Chicken & Vegan",Restaurant.generaterandomCoord() , "Westenriederstraße 37, 80331 München", RestaurantType.JAPANESE, PriceCategory.AVERAGE, null, null);
        //48.13534994382753, 11.579664625269427
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 30), DayOfWeek.WEDNESDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 30), DayOfWeek.THURSDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 30), DayOfWeek.FRIDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 30), DayOfWeek.SATURDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(14, 30), DayOfWeek.SUNDAY);

        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(17, 30), LocalTime.of(21, 15), DayOfWeek.WEDNESDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(17, 30), LocalTime.of(21, 15), DayOfWeek.THURSDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(17, 30), LocalTime.of(21, 15), DayOfWeek.FRIDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(17, 30), LocalTime.of(21, 15), DayOfWeek.SATURDAY);
        takumiViktualienmarkt.addOpeningTimes(LocalTime.of(17, 30), LocalTime.of(21, 15), DayOfWeek.SUNDAY);
        restaurants.add(takumiViktualienmarkt);


        //2
        Restaurant sausalitos = new Restaurant("Sausalitos", Restaurant.generaterandomCoord(), "Tal 16, 80331 München", RestaurantType.BAR_RESTAURANT, PriceCategory.AVERAGE, null, null);
        //48.135987192119714, 11.578892149131507
        sausalitos.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(1, 0), DayOfWeek.MONDAY);
        sausalitos.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(1, 0), DayOfWeek.TUESDAY);
        sausalitos.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(1, 0), DayOfWeek.WEDNESDAY);
        sausalitos.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(1, 0), DayOfWeek.THURSDAY);
        sausalitos.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(2, 0), DayOfWeek.FRIDAY);
        sausalitos.addOpeningTimes(LocalTime.of(12, 0), LocalTime.of(2, 0), DayOfWeek.SATURDAY);
        sausalitos.addOpeningTimes(LocalTime.of(16, 0), LocalTime.of(1, 0), DayOfWeek.SUNDAY);
        restaurants.add(sausalitos);

        //3
        Restaurant hansImGlueckTal = new Restaurant("Hans im Glück Tal", Restaurant.generaterandomCoord(), "Tal 10, 80331 München", RestaurantType.BURGER, PriceCategory.AVERAGE, null, null);
        //48.13609259598959, 11.57834849796949
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.MONDAY);
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.TUESDAY);
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.WEDNESDAY);
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.THURSDAY);
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(1, 0), DayOfWeek.FRIDAY);
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(1, 0), DayOfWeek.SATURDAY);
        hansImGlueckTal.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.SUNDAY);
        restaurants.add(hansImGlueckTal);

        //4
        Restaurant ratskeller = new Restaurant("Ratskeller München", Restaurant.generaterandomCoord(), "Marienplatz 8, 80331 München", RestaurantType.GERMAN, PriceCategory.AVERAGE, null, null);
        //48.13767428633818, 11.576067956695082
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.MONDAY);
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.TUESDAY);
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.WEDNESDAY);
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.THURSDAY);
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.FRIDAY);
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.SATURDAY);
        ratskeller.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(23, 0), DayOfWeek.SUNDAY);
        restaurants.add(ratskeller);

        //5
        Restaurant augustinerAmDom = new Restaurant("Augustiner am Dom", Restaurant.generaterandomCoord(), "Frauenplatz 8, 80331 München", RestaurantType.GERMAN, PriceCategory.AVERAGE, null, null);
        //48.13835446413484, 11.574061664334922
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.MONDAY);
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.TUESDAY);
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.WEDNESDAY);
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.THURSDAY);
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.FRIDAY);
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.SATURDAY);
        augustinerAmDom.addOpeningTimes(LocalTime.of(10, 0), LocalTime.of(0, 0), DayOfWeek.SUNDAY);
        restaurants.add(augustinerAmDom);

        //6
        Restaurant cafeZeitgeist = new Restaurant("Café Zeitgeist", Restaurant.generaterandomCoord(), "Türkenstraße 74, 80799 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
        //48.15143292984926, 11.57662948875597
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(12, 0), DayOfWeek.MONDAY);
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(12, 0), DayOfWeek.TUESDAY);
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(12, 0), DayOfWeek.WEDNESDAY);
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(13, 0), DayOfWeek.THURSDAY);
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(13, 0), DayOfWeek.FRIDAY);
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(13, 0), DayOfWeek.SATURDAY);
        cafeZeitgeist.addOpeningTimes(LocalTime.of(9, 0), LocalTime.of(12, 0), DayOfWeek.SUNDAY);
        restaurants.add(cafeZeitgeist);

        //7
        Restaurant loStudente = new Restaurant("Lo Studente", Restaurant.generaterandomCoord(), "Schellingstraße 30, 80799 München", RestaurantType.PIZZA, PriceCategory.AVERAGE, null, null);
        //48.15079784757689, 11.57575859493323
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.MONDAY);
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.TUESDAY);
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.WEDNESDAY);
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.THURSDAY);
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.FRIDAY);
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.SATURDAY);
        loStudente.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(0, 0), DayOfWeek.SUNDAY);
        restaurants.add(loStudente);

        //8
        Restaurant tacoCompany = new Restaurant("Taco Company", Restaurant.generaterandomCoord(), "Amalienstraße 69, 80799 München", RestaurantType.MEXICAN, PriceCategory.AVERAGE, null, null);
        //48.15046921948561, 11.578340843506178
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.MONDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.TUESDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.WEDNESDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.THURSDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.FRIDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.SATURDAY);
        restaurants.add(tacoCompany);


        //9
        Restaurant teaTime = new Restaurant("Tea Time", Restaurant.generaterandomCoord(), "Türkenstraße 69, 80799 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
        //48.151698006111474, 11.576700821271507
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.MONDAY);
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.TUESDAY);
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.WEDNESDAY);
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.THURSDAY);
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.FRIDAY);
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.SATURDAY);
        teaTime.addOpeningTimes(LocalTime.of(13, 0), LocalTime.of(20, 0), DayOfWeek.SUNDAY);
        restaurants.add(teaTime);


        //10
        Restaurant twentyPho = new Restaurant("Twenty Pho", Restaurant.generaterandomCoord(), "Augustenstraße 83, 80333 München", RestaurantType.VIETNAMESE, PriceCategory.AVERAGE, null, null);
        //48.15107031643462, 11.56382077285266
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.MONDAY);
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.TUESDAY);
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.WEDNESDAY);
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.THURSDAY);
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.FRIDAY);
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.SATURDAY);
        twentyPho.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(16, 0), DayOfWeek.SUNDAY);

        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.MONDAY);
        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.TUESDAY);
        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.WEDNESDAY);
        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.THURSDAY);
        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.FRIDAY);
        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.SATURDAY);
        twentyPho.addOpeningTimes(LocalTime.of(17, 0), LocalTime.of(22, 0), DayOfWeek.SUNDAY);
        restaurants.add(twentyPho);

        //11
        Restaurant chiThu = new Restaurant("Chi Thu", Restaurant.generaterandomCoord(), "Leopoldstraße 65, 80802 München", RestaurantType.VIETNAMESE, PriceCategory.AVERAGE, null, null);
        //48.16205020074638, 11.585947938528614
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.MONDAY);
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.TUESDAY);
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.WEDNESDAY);
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.THURSDAY);
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.FRIDAY);
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.SATURDAY);
        chiThu.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(21, 30), DayOfWeek.SUNDAY);
        restaurants.add(chiThu);

        //12
        Restaurant laTazzaDoro = new Restaurant("La Tazza D'Oro", Restaurant.generaterandomCoord(), "Hohenzollernstraße 13, 80801 München", RestaurantType.ITALIAN, PriceCategory.AVERAGE, null, null);
        //48.15969689206116, 11.582469217443164
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.MONDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.TUESDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.WEDNESDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.THURSDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.FRIDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.SATURDAY);
        restaurants.add(laTazzaDoro);

        //13
        Restaurant rischart = new Restaurant("Rischart Café Rialto", Restaurant.generaterandomCoord(), "Leopoldstraße 62, 80802 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
        //48.159626676068655, 11.585797951238305
        rischart.addOpeningTimes(LocalTime.of(7, 0), LocalTime.of(19, 30), DayOfWeek.MONDAY);
        rischart.addOpeningTimes(LocalTime.of(7, 0), LocalTime.of(19, 30), DayOfWeek.TUESDAY);
        rischart.addOpeningTimes(LocalTime.of(7, 0), LocalTime.of(19, 30), DayOfWeek.WEDNESDAY);
        rischart.addOpeningTimes(LocalTime.of(7, 0), LocalTime.of(19, 30), DayOfWeek.THURSDAY);
        rischart.addOpeningTimes(LocalTime.of(7, 0), LocalTime.of(19, 30), DayOfWeek.FRIDAY);
        rischart.addOpeningTimes(LocalTime.of(7, 0), LocalTime.of(19, 30), DayOfWeek.SATURDAY);
        rischart.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(18, 0), DayOfWeek.SUNDAY);
        restaurants.add(rischart);

        //14
        Restaurant munMun = new Restaurant("Mun Mun", Restaurant.generaterandomCoord(), "Münchner Freiheit 7, 80802 München", RestaurantType.THAI, PriceCategory.AVERAGE, null, null);
        //48.16276825147988, 11.586589371003813
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.MONDAY);
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.TUESDAY);
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.WEDNESDAY);
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.THURSDAY);
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.FRIDAY);
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.SATURDAY);
        munMun.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 0), DayOfWeek.SUNDAY);
        restaurants.add(munMun);


        //15
        Restaurant newEra = new Restaurant("New Era Coffee & Bar", Restaurant.generaterandomCoord(), "Münchner Freiheit 12, 80802 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
        //48.16269234168049, 11.587227992819823
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.MONDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.TUESDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.WEDNESDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.THURSDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.FRIDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.SATURDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.SUNDAY);
        restaurants.add(newEra);
        return restaurants;
    }
    public void addAllRestaurants() {
        //Die zwei auskommentierten Zahlen nach den Objekten sind Koordinaten --> je nach Implementierung der Karte evtl. später für Location nutzbar

    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
