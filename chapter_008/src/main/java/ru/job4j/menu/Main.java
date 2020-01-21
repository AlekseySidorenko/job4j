package ru.job4j.menu;


public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Element item1 = new Element("Task 1");
        Element item11 = new Element("Task 1.1");
        Element item12 = new Element("Task 1.2");
        Element item121 = new Element("Task 1.2.1");
        Element item2 = new Element("Task 2");
        item1.append(item11);
        item1.append(item12);
        item12.append(item121);

        menu.addElement(item1);
        menu.addElement(item2);
        menu.showMenu();
    }
}