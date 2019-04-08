package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 04.12.2017
 */
public class TrackerTest {

    Tracker tracker = Tracker.getInstance();

    /**
     * Test tracker singleton.
     */
    @Test
    public void whenTryToGetNewTrackerThenReturnOnlyOneTracker() {
        Tracker trackerTwo = Tracker.getInstance();
        assertThat(tracker, is(trackerTwo));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddItemThenReturnAddedItem() {
        tracker.dropItems();
        Item item = new Item("test", "Description");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    /**
     * Test update.
     */
    @Test
    public void whenUpdateItemNameThenReturnNewItemName() {
        tracker.dropItems();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        next.setId(previous.getId());
        tracker.update(next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenItemsHasNoItem() {
        tracker.dropItems();
        Item firstItem = new Item("firstItem", "testDescription");
        tracker.add(firstItem);
        Item secondItem = new Item("secondItem", "testDescription");
        tracker.add(secondItem);
        tracker.delete(firstItem);
        assertNull(tracker.findById(firstItem.getId()));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenAddThreeItemThenItemsHasThreeItem() {
        tracker.dropItems();
        Item firstItem = new Item("firstItem", "testDescription");
        tracker.add(firstItem);
        Item secondItem = new Item("secondItem", "testDescription");
        tracker.add(secondItem);
        Item thirdItem = new Item("thirdItem", "testDescription");
        tracker.add(thirdItem);
        List<Item> expected = new ArrayList<>();
        expected.add(firstItem);
        expected.add(secondItem);
        expected.add(thirdItem);
        assertThat((tracker.findAll()), is(expected));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenAddTwoItemWithSameNameAndThirdItemThenFindTwoItemByName() {
        tracker.dropItems();
        Item firstItem = new Item("TestItem", "testDescription1");
        tracker.add(firstItem);
        Item secondItem = new Item("TestItem", "testDescription2");
        tracker.add(secondItem);
        Item thirdItem = new Item("TestItem3", "testDescription2");
        tracker.add(thirdItem);
        List<Item> expected = new ArrayList<>();
        expected.add(firstItem);
        expected.add(secondItem);
        assertThat((tracker.findByName("TestItem")), is(expected));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenAddTwoItemThenFindTwoItemById() {
        tracker.dropItems();
        Item firstItem = new Item("firstItem", "testDescription1");
        tracker.add(firstItem);
        Item secondItem = new Item("secondItem", "testDescription2");
        tracker.add(secondItem);
        Item[] expected = {firstItem, secondItem};
        Item[] result = {tracker.findById(firstItem.getId()), tracker.findById(secondItem.getId())};
        assertThat((result), is(expected));
    }
}