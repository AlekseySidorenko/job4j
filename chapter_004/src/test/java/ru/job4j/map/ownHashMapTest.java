package ru.job4j.map;

import org.junit.Test;

public class ownHashMapTest {

    @Test
    public void userMapPrintResult() {
        User ivan = new User("Ivan", 1, 1985, 10, 11);
        User fedor = new User("Fedor", 1, 1985, 10, 11);
        User vasya = new User("Vasya", 1, 1985, 10, 11);

        ownHashMap<User, String> map = new ownHashMap<>();
        map.insert(ivan, "Ivan");
        map.insert(fedor, "FeDor");
        System.out.println(map.get(fedor));
        System.out.println(map.get(ivan));


        for (ownHashMap.Entry<User, String> key : map) {
            System.out.println(key);
        }
    }
}
