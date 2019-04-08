package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 08.04.2019
 */
public class TrackerSQLTest {

    TrackerSQL trackerSQL = TrackerSQL.getInstance();
    Item item = new Item("Item", "Item description");
    Item item1 = new Item("Item1", "Item1 description");

    @Before
    public void setUp() {
        trackerSQL.initConnection();
        trackerSQL.initItemsTable();
    }

    @After
    public void tearDown() throws Exception {
        trackerSQL.dropItemsTable();
        trackerSQL.close();
    }

    /**
     * Test tracker singleton.
     */
    @Test
    public void whenTryToGetNewTrackerThenReturnOnlyOneTracker() {
        TrackerSQL trackerTwo = TrackerSQL.getInstance();
        assertThat(trackerSQL, Is.is(trackerTwo));
    }

    /**
     * Test database connection.
     */
    @Test
    public void checkConnection() {
        assertThat(trackerSQL.initConnection(), is(true));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddItemThenGetSameItem() {
        trackerSQL.add(item);
        assertThat(trackerSQL.findById(item.getId()).getId(), is(item.getId()));
    }

    /**
     * Test update.
     */
    @Test
    public void whenUpdateItemNameThenReturnNewItemName() {
        trackerSQL.add(item);
        Item updatedItem = new Item("UpdatedItem", "Item description");
        updatedItem.setId(item.getId());
        updatedItem.setCreateDate(item.getCreateDate());
        trackerSQL.update(updatedItem);
        assertThat(trackerSQL.findById(item.getId()).getName(), Is.is("UpdatedItem"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenItemsHasNoItem() {
        trackerSQL.add(item);
        trackerSQL.delete(item);
        assertNull(trackerSQL.findById(item.getId()));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenAddTwoItemsThenFindTwoItems() {
        trackerSQL.add(item);
        trackerSQL.add(item1);
        List<Item> expected = new ArrayList<>();
        expected.add(item);
        expected.add(item1);
        List<Item> result = new ArrayList<>(trackerSQL.findAll());
        assertThat((result.get(0).getId()), Is.is(expected.get(0).getId()));
        assertThat((result.get(1).getId()), Is.is(expected.get(1).getId()));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenAddTwoItemWithSameNameAndAddThirdItemThenFindTwoItemsByName() {
        Item itemSameName = new Item("Item", "Item description");
        trackerSQL.add(item);
        trackerSQL.add(item1);
        trackerSQL.add(itemSameName);
        List<Item> expected = new ArrayList<>();
        expected.add(item);
        expected.add(itemSameName);
        List<Item> result = new ArrayList<>(trackerSQL.findByName("Item"));
        assertThat((result.get(0).getId()), Is.is(expected.get(0).getId()));
        assertThat((result.get(1).getId()), Is.is(expected.get(1).getId()));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenAddItemThenFindItemById() {
        trackerSQL.add(item);
        assertThat((trackerSQL.findById(item.getId())).getId(), is(item.getId()));
    }
}