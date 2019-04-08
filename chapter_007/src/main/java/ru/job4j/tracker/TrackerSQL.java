package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Class Tracker Tracker SQL [#1734]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 08.04.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    private static TrackerSQL instance;
    private Connection connection;
    private final String initialQuery = "CREATE TABLE IF NOT EXISTS items ("
                                      + "id SERIAL PRIMARY KEY NOT NULL,"
                                      + "item_id BIGINT NOT NULL,"
                                      + "name VARCHAR(100) NOT NULL,"
                                      + "description VARCHAR(200),"
                                      + "create_date BIGINT NOT NULL)";

    private TrackerSQL() {
    }

    /**
     * Singleton.
     * @return TrackerSQL instance.
     */
    public static synchronized TrackerSQL getInstance() {
        if (instance == null) {
            instance = new TrackerSQL();
        }
        return instance;
    }

    /**
     * Init connection to database.
     * @return true if got connection.
     */
    public boolean initConnection() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error("Can't get properties for database connection");
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Create table for items if not exists.
     */
    public void initItemsTable() {
        if (initConnection()) {
            try {
                if (this.connection != null) {
                    Statement statement = this.connection.createStatement();
                    statement.executeUpdate(initialQuery);
                }
            } catch (SQLException e) {
                LOG.error("Problem with connection to database");
                e.printStackTrace();
            }
        }
    }

    /**
     * Drop table items if exists.
     */
    public void dropItemsTable() {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("DROP TABLE IF EXISTS items")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't drop table items");
            e.printStackTrace();
        }
    }

    /**
     * Close connection to database.
     */
    @Override
    public void close() throws Exception {
        this.connection.close();
    }


    /**
     * Add item.
     * @param item Item.
     * @return Added item.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                "INSERT INTO items(item_id, name, description, create_date) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setLong(1,    item.getId());
            preparedStatement.setString(2,  item.getName());
            preparedStatement.setString(3,  item.getDesc());
            preparedStatement.setLong(4,    item.getCreateDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't insert item");
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Update item.
     * @param item Item.
     */
    @Override
    public void update(Item item) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
               "UPDATE items SET name = ?, description = ? WHERE item_id = ?")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesc());
            preparedStatement.setLong(3, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't update item");
            e.printStackTrace();
        }
    }

    /**
     * Delete item.
     * @param item Item.
     */
    @Override
    public void delete(Item item) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                "DELETE FROM items WHERE item_id = ?")) {
            preparedStatement.setLong(1, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can't delete item");
            e.printStackTrace();
        }
    }

    /**
     * Get all items.
     * @return List of items.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                "SELECT * FROM items")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                                     resultSet.getString("description")
                                     );
                item.setId(resultSet.getLong("item_id"));
                item.setCreateDate(resultSet.getLong("create_date"));
                items.add(item);
            }
        } catch (SQLException e) {
            LOG.error("Can't get list of items");
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Find item(s) by name.
     * @param key Item's name.
     * @return List of items.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                "SELECT * FROM items WHERE name = ?")) {
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"));
                item.setId(resultSet.getLong("item_id"));
                item.setCreateDate(resultSet.getLong("create_date"));
                items.add(item);
            }
        } catch (SQLException e) {
            LOG.error("Can't find item by name");
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Find item by id.
     * @param id Item's id.
     * @return Item.
     */
    @Override
    public Item findById(long id) {
        Item item = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(
                "SELECT * FROM items WHERE item_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"));
                item.setId(resultSet.getLong("item_id"));
                item.setCreateDate(resultSet.getLong("create_date"));
            }
        } catch (SQLException e) {
            LOG.error("Can't find item by id");
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void showItemById(long id) {
        Item item = this.findById(id);
        System.out.println("Name:    " + item.getName());
        System.out.println("Id:      " + item.getId());
        System.out.println("Desc:    " + item.getDesc());
        System.out.println("Created: " + item.getCreateDate());
        if (item.getComments() != null) {
            String[] comments = item.getComments();
            System.out.println("Comments for this item:");
            Arrays.stream(comments).forEach(System.out::println);
        }
    }
}