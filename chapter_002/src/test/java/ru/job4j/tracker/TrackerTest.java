package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 04.12.2017
 */
public class TrackerTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddItemThenReturnAddedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "Description", 1L);
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    /**
     * Test update.
     */
    @Test
    public void whenUpdateItemNameThenReturnNewItemName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.update(next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenItemsHasNoItem() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("firstItem", "testDescription", 123L);
        tracker.add(firstItem);
        Item secondItem = new Item("secondItem", "testDescription", 123L);
        tracker.add(secondItem);
        tracker.delete(firstItem);

        assertThat(tracker.findById(firstItem.getId()), is((Item) null));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenAddThreeItemThenItemsHasThreeItem() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("firstItem", "testDescription", 123L);
        tracker.add(firstItem);
        Item secondItem = new Item("secondItem", "testDescription", 123L);
        tracker.add(secondItem);
        Item thirdItem = new Item("thirdItem", "testDescription", 123L);
        tracker.add(thirdItem);
        Item[] expected = {firstItem, secondItem, thirdItem};

        assertThat((tracker.findAll()), is(expected));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenAddTwoItemWithSameNameAndThirdItemThenFindTwoItemByName() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("TestItem", "testDescription1", 1231L);
        tracker.add(firstItem);
        Item secondItem = new Item("TestItem", "testDescription2", 1232L);
        tracker.add(secondItem);
        Item thirdItem = new Item("TestItem3", "testDescription2", 1232L);
        tracker.add(thirdItem);

        Item[] expected = {firstItem, secondItem};

        assertThat((tracker.findByName("TestItem")), is(expected));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenAddTwoItemThenFindTwoItemById() {
        Tracker tracker = new Tracker();
        Item firstItem = new Item("firstItem", "testDescription1", 1231L);
        tracker.add(firstItem);
        Item secondItem = new Item("secondItem", "testDescription2", 1232L);
        tracker.add(secondItem);

        Item[] expected = {firstItem, secondItem};
        Item[] result = {tracker.findById(firstItem.getId()), tracker.findById(secondItem.getId())};

        assertThat((result), is(expected));
    }
}
