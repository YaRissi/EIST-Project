package reservationsystem.testing;

import de.tum.in.ase.insertteamnamehere.model.PriceCategory;
import de.tum.in.ase.insertteamnamehere.model.Restaurant;
import de.tum.in.ase.insertteamnamehere.model.RestaurantService;
import de.tum.in.ase.insertteamnamehere.model.RestaurantType;
import de.tum.in.ase.insertteamnamehere.user.User;
import de.tum.in.ase.insertteamnamehere.userinterface.FXMLInterfaceController;
import de.tum.in.ase.insertteamnamehere.util.Coord;
import de.tum.in.ase.insertteamnamehere.util.SortingOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {
    Restaurant r1;
    Restaurant r2;
    Restaurant r3;
    User user;
    RestaurantService test;
    List<Restaurant> resultList;

    @BeforeEach
    void setup(){
        r1 = new Restaurant("burger bar", new Coord(42.3623632f,43.3434234f),"somewhere", RestaurantType.BURGER, PriceCategory.AFFORDABLE,null,null);
        r2 = new Restaurant("chinese noodle bar", new Coord(30.3623632f,43.3434234f),"somewhere", RestaurantType.NOODLE_BAR, PriceCategory.AVERAGE,null,null);
        r3 = new Restaurant("italian pizza", new Coord(30.3623632f,43.3434234f),"somewhere", RestaurantType.PIZZA, PriceCategory.EXPENSIVE,null,null);
        user = new User("Bob",new RestaurantService(new User(null,null,null)),new Coord(42.3623632f,43.3434234f));
        test = new RestaurantService(user);
        test.clear();
        test.addRestaurant(r1);
        test.addRestaurant(r2);
        test.addRestaurant(r3);
        resultList = new ArrayList<>();
    }

    @Test
    void testSearchRegular() {
        resultList.add(r1);
        assertEquals(resultList,test.search("burger"));
    }

    @Test
    void testFilterDistance() {
        resultList.add(r1);
        assertEquals(resultList,test.filterDistance(test.getRestaurants(), SortingOptions.SortingOrder.ASCENDING,1));
    }

    @Test
    void testFilterType() {
        List<RestaurantType> types = new ArrayList<>();
        types.add(RestaurantType.NOODLE_BAR);
        resultList.add(r2);
        assertEquals(resultList, test.filterType(SortingOptions.SortingOrder.ASCENDING,types));
    }

    @Test
    void testFilterPrize() {
        resultList.add(r3);
        assertEquals(resultList, test.filterPrize(test.getRestaurants(), SortingOptions.SortingOrder.ASCENDING,PriceCategory.EXPENSIVE));
    }

    @Test
    void testFilterRating() {
        r2.addRating(4);
        r2.addRating(4);
        resultList.add(r2);
        assertEquals(resultList,test.filterRating(test.getRestaurants(), SortingOptions.SortingOrder.ASCENDING,2));
    }

    @Test
    void filterTimeSlots() {
        r3.addOpeningTimes(LocalTime.of(7,0),LocalTime.of(8,0), DayOfWeek.MONDAY);
        resultList.add(r3);
        assertEquals(resultList, test.filterTimeSlots(test.getRestaurants(),SortingOptions.SortingOrder.ASCENDING,LocalTime.of(7,0),LocalTime.of(8,0)));
    }
}