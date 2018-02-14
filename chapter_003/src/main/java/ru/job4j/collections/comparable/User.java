package ru.job4j.collections.comparable;

/**
 * Class User Решение задачи: Организовать сортировку User [#10034]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.02.2018
 */
public class User implements Comparable<User> {
    final String name;
    final int age;

    /**
     * Конструктор.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }
}