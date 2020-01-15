package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Element | Create menu [#4748]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.01.2020
 */
public class Element implements MenuContainable, Executable {

    private List<MenuContainable> appendedElements;
    private final String name;
    /**
     * Contains amount of this element's parents
     */
    private int parentsAmount;

    /**
     * Constructor.
     */
    public Element(String name) {
        this.appendedElements = new ArrayList<>();
        this.name = name;
    }

    /**
     * Get elements, appended to this element.
     * @return Appended elements.
     */
    public List<MenuContainable> getAppendedElements() {
        return appendedElements;
    }

    @Override
    public int getParentsAmount() {
        return parentsAmount;
    }

    @Override
    public void setParentsAmount(int parentsAmount) {
        this.parentsAmount = parentsAmount;
    }

    @Override
    public void append(MenuContainable appendable) {
        appendedElements.add(appendable);
        appendable.setParentsAmount(appendable.getParentsAmount() + 1);
    }

    @Override
    public void execute() {
    }

    @Override
    public void show() {
        for (int i = 0; i <= this.getParentsAmount(); i++) {
            System.out.print("--- ");
        }
        System.out.println(this.name);
        List<MenuContainable> subElements = this.getAppendedElements();
        if (subElements.size() > 0) {
            for (MenuContainable element : subElements) {
                element.show();
            }
        }
    }
}