package unit6.task4;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit6.task4.domain.User;
import unit6.task4.service.GraphSearchService;
import unit6.task4.service.SearchService;

public class GraphSearchServiceTest {

    private User me;
    private SearchService searchService = new GraphSearchService();

    @BeforeEach
    public void init() {
        me = new User("Андрей");
        User polina = new User("Полина");
        User evgeniy = new User("Евгений");
        User alina = new User("Алина");
        User konstantine = new User("Константин");
        User kseniya = new User("Ксения");
        User vasiliy = new User("Василий");
        User daria = new User("Дарья");
        User maxOne = new User("Макс");
        User maxTwo = new User("Макс");

        me.setFriends(List.of(polina));
        polina.setFriends(List.of(me, evgeniy, alina));
        evgeniy.setFriends(List.of(polina, maxOne));
        maxOne.setFriends(List.of(evgeniy, alina));
        alina.setFriends(List.of(maxOne, polina, maxTwo, konstantine));
        maxTwo.setFriends(List.of(alina, daria));
        konstantine.setFriends(List.of(alina, kseniya, vasiliy));
        kseniya.setFriends(List.of(konstantine));
        vasiliy.setFriends(List.of(konstantine, daria));
        daria.setFriends(List.of(vasiliy, maxTwo));
    }

    @Test
    public void testDepth() {
        List<User> resultSearchInDepth = searchService.searchForFriendsInDepth(me, "Макс");
        var resultNames = resultSearchInDepth.stream().map(User::getName).toList();
        Assertions.assertArrayEquals(resultNames.toArray(), new String[]{"Макс", "Макс"});
        Assertions.assertTrue(resultNames.size() == 2);
    }

    @Test
    public void testWidth() {
        List<User> resultSearchInWidth = searchService.searchForFriendsInWidth(me, "Макс");
        var resultNames = resultSearchInWidth.stream().map(User::getName).toList();
        Assertions.assertArrayEquals(resultNames.toArray(), new String[]{"Макс", "Макс"});
        Assertions.assertTrue(resultNames.size() == 2);
    }
}
