package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Class UserTest | Task Solution: Do not override equals() and hashCode() [#1005]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 26.08.2018
 */
public class UserTest {

    @Test
    public void userMapPrintResult() {
        User ivan = new User("Ivan", 1, 1985, 10, 11);
        User fedor = new User("Ivan", 1, 1985, 10, 11);

        Map map = new HashMap();
        map.put(ivan, "Ivan");
        map.put(fedor, "Ivan");
        System.out.println(map);
    }
}