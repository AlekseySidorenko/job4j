package ru.job4j.tracker;

/**
 * Class MenuTracker Реализовать события на внутренних классах. [#787]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 25.12.2017
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    /**
     * Конструктор.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int getActionsSize() {
        return actions.length;
    }
    /**
     * Метод формирует меню и предоставляет выбор пользователю.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = new MenuTracker.FindItemById();
        this.actions[5] = new MenuTracker.FindItemsByName();
        this.actions[6] = this.new ExitProgram();
    }

    /**
     * Метод выполняет действие, выбранное пользователем.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Метод выводит меню на экран.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Class AddItem Реализует добавление новый заявки в трекер.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new task ------------");
            String name = input.ask("Enter task name: ");
            String desc = input.ask("Enter task description: ");
            long create = System.currentTimeMillis();

            Item item = new Item(name, desc, create);
            tracker.add(item);
            System.out.println();
            System.out.println("------------ New task ------------");
            tracker.showItemById(item.getId());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new item");
        }
    }

    /**
     * Class ShowItems Реализует вывод на экран всех заявок из трекера.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public static class ShowItems implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Showing all tasks ------------");
            for (Item item : tracker.findAll()) {
                tracker.showItemById(item.getId());
                System.out.println("------------");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * Class DeleteItem Реализует удаление заявки из трекера.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item deletingItem = tracker.findById(input.ask("Enter deleting item id: "));
            System.out.println("------------ Deleting task with id " + deletingItem.getId() + "------------");
            tracker.delete(deletingItem);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * Class FindItemById Реализует поиск заявки по Id.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public static class FindItemById implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            tracker.showItemById(input.ask("Enter finding item id: "));

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    /**
     * Class FindItemsByName Реализует поиск заявки (заявок) по имени.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public static class FindItemsByName implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            Item[] result = tracker.findByName(input.ask("Enter finding item (items) name: "));
            for (Item item : result) {
                tracker.showItemById(item.getId());
                System.out.println("------------");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }

    /**
     * Class ExitProgram Реализует выход из программы.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public class ExitProgram implements UserAction {

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program");
        }
    }
}

/**
 * Class EditItem Реализует редактирование заявки.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 25.12.2017
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        Item editingItem = tracker.findById(input.ask("Enter editing item's id: "));
        System.out.println("------------ Updating task with id " + editingItem.getId() + "------------");
        editingItem.setName(input.ask("Enter new task's name: "));
        editingItem.setDesc(input.ask("Enter new task's description: "));
        tracker.update(editingItem);
        System.out.println("Task was update");
        tracker.showItemById(editingItem.getId());
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }
}