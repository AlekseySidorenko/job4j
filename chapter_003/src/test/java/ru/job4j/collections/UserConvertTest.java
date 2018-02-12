package ru.job4j.collections;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.02.2018
 */
public class UserConvertTest {

    /**
     * Test process.
     */
    @Test
    public void whenSentListWithUsersThenGetHashMap() {
        List<User> list = new ArrayList<>();
        User user1 = new User(123, "Alexey", "Spb");
        User user2 = new User(124, "Egor", "Moscow");
        User user3 = new User(125, "Fedor", "Belogorsk");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        UserConvert uc = new UserConvert();
        HashMap<Integer, User> result = uc.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(123, new User(123, "Alexey", "Spb"));
        expected.put(124, new User(124, "Egor", "Moscow"));
        expected.put(125, new User(125, "Fedor", "Belogorsk"));
        assertThat(result, is(expected));
    }
}