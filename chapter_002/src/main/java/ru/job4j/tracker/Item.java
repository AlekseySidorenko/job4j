package ru.job4j.tracker;

import java.util.Random;

/**
 * Class Item 2. Реализовать класс Tracker [#396]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 04.12.2017
 */
public class Item implements Comparable<Item> {
    private long id;
    private String name;
    private String desc;
    private String[] comments;
    private long createDate;
    private static final Random RN = new Random();

    public Item(String name, String desc) {
        this.id = System.currentTimeMillis() + RN.nextInt();
        this.name = name;
        this.desc = desc;
        this.createDate = System.currentTimeMillis();
    }

    /**
     * Getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter.
     * @return desc.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Getter.
     * @return create.
     */
    public long getCreateDate() {
        return this.createDate;
    }

    /**
     * Getter.
     * @return comments.
     */
    public String[] getComments() {
        return this.comments;
    }

    /**
     * Getter.
     * @return id.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Setter.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter.
     * @param desc description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Setter.
     * @param comments comments.
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     * Setter.
     * @param id id.
     */
    public void setId(long id) {
        this.id = id;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Override
    public int compareTo(Item o) {
        return (int) (this.getId() - o.getId());
    }
}
