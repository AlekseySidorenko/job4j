package ru.job4j.tracker;

/**
 * Class StartUI Реализация задания [#784]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.12.2017
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;

    /**
     * Конструктор.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод реализует основной цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int exitStatus;

        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Select: "));
            exitStatus = key;
            menu.select(key);
        } while (exitStatus != 6);
    }

    /**
     * Запуск программы.
     * @param args args.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
