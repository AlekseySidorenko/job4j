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
        users.add(new User("Ivan", 32));
        users.add(new User("Fedor", 28));
        users.add(new User("Fedor", 21));
        Set<User> result = new SortUser().sort(users);
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Fedor", 21));
        expected.add(new User("Ivan", 32));
        expected.add(new User("Fedor", 28));
        assertThat(result, is(expected));
    }
}