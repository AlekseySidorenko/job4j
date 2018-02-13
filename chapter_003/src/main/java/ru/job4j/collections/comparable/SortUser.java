package ru.job4j.collections.comparable;

import java.util.*;

/**
 * Class SortUser Решение задачи: Организовать сортировку User [#10034]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.02.2018
 */
public class SortUser implements Comparator<User> {

    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     * @param list List.
     * @return TreeSet пользователей, отсортированных по возрасту в порядке возрастания.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>(new SortUser());
        result.addAll(list);
        return result;
    }

    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o1.age, o2.age);
    }
}