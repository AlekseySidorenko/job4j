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
    private int position = 0;

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
        this.actions[position++] = this.new AddItem(0, "Add new item");
        this.actions[position++] = new MenuTracker.ShowItems(1, "Show all items");
        this.actions[position++] = new EditItem(2, "Edit item");
        this.actions[position++] = this.new DeleteItem(3, "Delete item");
        this.actions[position++] = new MenuTracker.FindItemById(4, "Find item by Id");
        this.actions[position++] = new MenuTracker.FindItemsByName(5, "Find items by name");
        this.actions[position++] = this.new ExitProgram(6, "Exit Program");
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
    public class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Class ShowItems Реализует вывод на экран всех заявок из трекера.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public static class ShowItems extends BaseAction {

        protected ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Showing all tasks ------------");
            for (Item item : tracker.findAll()) {
                tracker.showItemById(item.getId());
                System.out.println("------------");
            }
        }
    }

    /**
     * Class DeleteItem Реализует удаление заявки из трекера.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item deletingItem = tracker.findById(input.ask("Enter deleting item id: "));
            System.out.println("------------ Deleting task with id " + deletingItem.getId() + "------------");
            tracker.delete(deletingItem);
        }
    }

    /**
     * Class FindItemById Реализует поиск заявки по Id.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public static class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.showItemById(input.ask("Enter finding item id: "));
        }
    }

    /**
     * Class FindItemsByName Реализует поиск заявки (заявок) по имени.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public static class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {

            Item[] result = tracker.findByName(input.ask("Enter finding item (items) name: "));
            for (Item item : result) {
                tracker.showItemById(item.getId());
                System.out.println("------------");
            }
        }
    }

    /**
     * Class ExitProgram Реализует выход из программы.
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 25.12.2017
     */
    public class ExitProgram extends BaseAction {

        public ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }
}

/**
 * Class EditItem Реализует редактирование заявки.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 25.12.2017
 */
class EditItem extends BaseAction {

    protected EditItem(int key, String name) {
        super(key, name);
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
}