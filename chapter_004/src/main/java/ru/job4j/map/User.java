package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Class User | Task Solution: hashCode() only override [#1003]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 19.08.2018
 */
public class User {

    private final String name;
    private int children;
    private final Calendar birthday;

    public User(String name, int children, int year, int month, int day) {
        this.name = name;
        this.children = children;
        this.birthday = new GregorianCalendar(year, month, day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }
}