package ru.job4j.tracker;

/**
 * Class StartUI Реализация задания [#784]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 12.12.2017
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех добавленных заявок.
     */
    private static final String SHOWALL = "1";
    /**
     * Константа меню для изменения заявки в трекере.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки из трекера.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню поиска заявки по Id.
     */
    private static final String FINDBYID = "4";
    /**
     * Константа меню поиска заявки (заявок) по имени.
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа меню для выхода из цикла.
     */
    private static final String EXIT = "6";

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOWALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.edit();
            } else if (DELETE.equals(answer)) {
                this.delete();
            } else if (FINDBYID.equals(answer)) {
                this.findById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод выводит меню на экран.
     */
    private void showMenu() {
        System.out.println("");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Метод реализует добавление новый заявки в трекер.
     */
    private void createItem() {
        System.out.println("------------ Adding new task ------------");
        String name = this.input.ask("Enter task name: ");
        String desc = this.input.ask("Enter task description: ");
        long create = System.currentTimeMillis();

        Item item = new Item(name, desc, create);
        this.tracker.add(item);
        System.out.println();
        System.out.println("------------ New task ------------");
        tracker.showItemById(item.getId());
    }

    /**
     * Метод реализует вывод на экран всех заявок из трекера.
     */
    private void showAllItems() {
        System.out.println("------------ Showing all tasks ------------");
        Item[] result = this.tracker.findAll();
        for (Item item : result) {
            tracker.showItemById(item.getId());
            System.out.println("------------");
        }
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void edit() {
        Item replacingItem = tracker.findById(this.input.ask("Enter editing item id: "));
        System.out.println("------------ Updating task with id " + replacingItem.getId() + "------------");
        replacingItem.setName(this.input.ask("Enter new task's name: "));
        replacingItem.setDesc(this.input.ask("Enter new task's description: "));
        tracker.update(replacingItem);
        System.out.println("Task was update");
        tracker.showItemById(replacingItem.getId());
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void delete() {
        Item deletingItem = tracker.findById(this.input.ask("Enter deleting item id: "));
        System.out.println("------------ Deleting task with id " + deletingItem.getId() + "------------");
        tracker.delete(deletingItem);
    }

    /**
     * Метод реализует поиск заявки (заявок) по имени.
     */
    private void findByName() {
        Item[] result = tracker.findByName(input.ask("Enter finding item (items) name: "));
        for (Item item : result) {
            tracker.showItemById(item.getId());
            System.out.println("------------");
        }
    }

    /**
     * Метод реализует поиск заявки по Id.
     */
    private void findById() {
        tracker.showItemById(input.ask("Enter finding item id: "));
    }

    /**
     * Запуск программы.
     * @param args args.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
