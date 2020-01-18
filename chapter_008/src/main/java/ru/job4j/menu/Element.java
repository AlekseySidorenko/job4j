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
     * Contains deep level of this element's in menu
     */
    private int menuDeepLevel;

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
    public int getMenuDeepLevel() {
        return menuDeepLevel;
    }

    @Override
    public void setMenuDeepLevel(int menuDeepLevel) {
        this.menuDeepLevel = menuDeepLevel;
    }

    @Override
    public void append(MenuContainable appendable) {
        appendedElements.add(appendable);
        appendable.setMenuDeepLevel(appendable.getMenuDeepLevel() + 1);
    }

    @Override
    public void execute() {
    }

    @Override
    public void show() {
        for (int i = 0; i <= this.getMenuDeepLevel(); i++) {
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