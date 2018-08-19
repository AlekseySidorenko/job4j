package ru.job4j.map;

import java.util.Calendar;

/**
 * Class User | Task Solution: Create User model [#999]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 19.08.2018
 */
public class User {

    private final String name;
    private int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}