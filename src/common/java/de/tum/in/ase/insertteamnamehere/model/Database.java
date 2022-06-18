package de.tum.in.ase.insertteamnamehere.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Database {
    private List<Restaurant> restaurants;

    private void addAllRestaurants() {
        //Die zwei auskommentierten Zahlen nach den Objekten sind Koordinaten --> je nach Implementierung der Karte evtl. später für Location nutzbar

        //1
        Restaurant takumiViktualienmarkt = new Restaurant("Takumi München Chicken & Vegan", null, "Westenriederstraße 37, 80331 München", RestaurantType.JAPANESE, PriceCategory.AVERAGE, null, null);
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
        Restaurant sausalitos = new Restaurant("Sausalitos", null, "Tal 16, 80331 München", RestaurantType.BAR_RESTAURANT, PriceCategory.AVERAGE, null, null);
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
        Restaurant hansImGlueckTal = new Restaurant("Hans im Glück Tal", null, "Tal 10, 80331 München", RestaurantType.BURGER, PriceCategory.AVERAGE, null, null);
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
        Restaurant ratskeller = new Restaurant("Ratskeller München", null, "Marienplatz 8, 80331 München", RestaurantType.GERMAN, PriceCategory.AVERAGE, null, null);
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
        Restaurant augustinerAmDom = new Restaurant("Augustiner am Dom", null, "Frauenplatz 8, 80331 München", RestaurantType.GERMAN, PriceCategory.AVERAGE, null, null);
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
        Restaurant cafeZeitgeist = new Restaurant("Café Zeitgeist", null, "Türkenstraße 74, 80799 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
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
        Restaurant loStudente = new Restaurant("Lo Studente", null, "Schellingstraße 30, 80799 München", RestaurantType.PIZZA, PriceCategory.AVERAGE, null, null);
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
        Restaurant tacoCompany = new Restaurant("Taco Company", null, "Amalienstraße 69, 80799 München", RestaurantType.MEXICAN, PriceCategory.AVERAGE, null, null);
        //48.15046921948561, 11.578340843506178
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.MONDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.TUESDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.WEDNESDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.THURSDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.FRIDAY);
        tacoCompany.addOpeningTimes(LocalTime.of(9, 30), LocalTime.of(20, 0), DayOfWeek.SATURDAY);
        restaurants.add(tacoCompany);


        //9
        Restaurant teaTime = new Restaurant("Tea Time", null, "Türkenstraße 69, 80799 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
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
        Restaurant twentyPho = new Restaurant("Twenty Pho", null, "Augustenstraße 83, 80333 München", RestaurantType.VIETNAMESE, PriceCategory.AVERAGE, null, null);
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
        Restaurant chiThu = new Restaurant("Chi Thu", null, "Leopoldstraße 65, 80802 München", RestaurantType.VIETNAMESE, PriceCategory.AVERAGE, null, null);
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
        Restaurant laTazzaDoro = new Restaurant("La Tazza D'Oro", null, "Hohenzollernstraße 13, 80801 München", RestaurantType.ITALIAN, PriceCategory.AVERAGE, null, null);
        //48.15969689206116, 11.582469217443164
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.MONDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.TUESDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.WEDNESDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.THURSDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.FRIDAY);
        laTazzaDoro.addOpeningTimes(LocalTime.of(11, 0), LocalTime.of(22, 30), DayOfWeek.SATURDAY);
        restaurants.add(laTazzaDoro);

        //13
        Restaurant rischart = new Restaurant("Rischart Café Rialto", null, "Leopoldstraße 62, 80802 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
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
        Restaurant munMun = new Restaurant("Mun Mun", null, "Münchner Freiheit 7, 80802 München", RestaurantType.THAI, PriceCategory.AVERAGE, null, null);
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
        Restaurant newEra = new Restaurant("New Era Coffee & Bar", null, "Münchner Freiheit 12, 80802 München", RestaurantType.CAFE, PriceCategory.AVERAGE, null, null);
        //48.16269234168049, 11.587227992819823
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.MONDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.TUESDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.WEDNESDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.THURSDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.FRIDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.SATURDAY);
        newEra.addOpeningTimes(LocalTime.of(8, 0), LocalTime.of(21, 0), DayOfWeek.SUNDAY);
        restaurants.add(newEra);
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
