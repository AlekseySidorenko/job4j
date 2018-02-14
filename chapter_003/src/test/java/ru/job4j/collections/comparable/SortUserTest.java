package ru.job4j.collections.comparable;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.02.2018
 */
public class SortUserTest {

    /**
     * Test sort.
     */
    @Test
    public void whenNoOrderedUserListThenGetOrderedSet() {
        List<User> users = new ArrayList<>();
        User user1 = new User("Ivan", 32);
        User user2 = new User("Fedor", 28);
        User user3 = new User("Fedor", 21);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        Set<User> result = new SortUser().sort(users);
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Fedor", 21));
        expected.add(new User("Ivan", 32));
        expected.add(new User("Fedor", 28));
        assertThat(result, is(expected));
    }
}