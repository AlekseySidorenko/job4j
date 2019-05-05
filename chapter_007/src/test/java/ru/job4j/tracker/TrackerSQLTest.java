package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 08.04.2019
 */
public class TrackerSQLTest {

    TrackerSQL trackerSQL;
    Item item = new Item("Item", "Item description");
    Item item1 = new Item("Item1", "Item1 description");

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddItemThenGetSameItem() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
        trackerSQL.add(item);
        assertThat(trackerSQL.findById(item.getId()).getId(), is(item.getId()));
    }

    /**
     * Test update.
     */
    @Test
    public void whenUpdateItemNameThenReturnNewItemName() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
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
    public void whenDeleteItemThenItemsHasNoItem() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
        trackerSQL.add(item);
        trackerSQL.delete(item);
        assertNull(trackerSQL.findById(item.getId()));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenAddTwoItemsThenFindTwoItems() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
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
    public void whenAddTwoItemWithSameNameAndAddThirdItemThenFindTwoItemsByName() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
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
    public void whenAddItemThenFindItemById() throws SQLException {
        trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()));
        trackerSQL.add(item);
        assertThat((trackerSQL.findById(item.getId())).getId(), is(item.getId()));
    }
}