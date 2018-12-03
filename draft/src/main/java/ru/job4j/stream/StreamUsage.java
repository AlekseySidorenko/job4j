package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static class Task {
        private final String name;
        private final long spent;

        Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return "Task{"
                    + "name='"
                    + name + '\''
                    + ", spent="
                    + spent
                    + '}';
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = Arrays.asList(
                new Task("Bug #1", 100),
                new Task("Task #2", 150),
                new Task("Bug #3", 100)
        );
        List<Task> bugs = tasks.stream().filter(task -> task.name.contains("Bug")).collect(Collectors.toList());
        long total = tasks.stream().map(task -> task.spent).reduce(0L, Long::sum);
        bugs.forEach(System.out::println);
        System.out.println(total);
    }
}