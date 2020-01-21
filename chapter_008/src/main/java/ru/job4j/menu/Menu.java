package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Menu | Create menu [#4748]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 14.01.2020
 */
public class Menu {

    List<MenuContainable> elements;

    /**
     * Constructor.
     */
    public Menu() {
        this.elements = new ArrayList<>();
    }

    /**
     * Add element to menu.
     * @param element element.
     */
    public void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Add subelement to element.
     * @param rootElement rootElement.
     * @param element appendable element.
     */
    public void addSubElement(Element rootElement, Element element) {
        rootElement.append(element);
    }

    /**
     * Show menu.
     */
    public void showMenu() {
        for (MenuContainable element : elements) {
            element.show();
        }
    }
}