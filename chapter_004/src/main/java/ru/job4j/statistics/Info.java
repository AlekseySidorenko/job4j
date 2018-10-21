package ru.job4j.statistics;

import java.util.List;

/**
 * Class Info | Task Solution: Collection statistics [#45889]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 08.10.2018
 */
class Info {

    private int added = 0;
    private int changed = 0;
    private int deleted = 0;

    /**
     * Compare Lists.
     * @param previous List of elements.
     * @param current  Possibly modified list of elements.
     */
    public void compare(List<User> previous, List<User> current) {
        checkDeletedElements(previous, current);
        checkAddedElements(previous, current);
        checkChangedElements(previous, current);
    }

    /**
     * Calculate added elements
     * @param previous List of elements.
     * @param current  Possibly modified list of elements.
     */
    void checkAddedElements(List<User> previous, List<User> current) {
        boolean flag = false;
        for (User currUser : current) {
            for (User prevUser : previous) {
                if ((currUser.equals(prevUser)) || (currUser.getId() == prevUser.getId())) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) {
                added++;
            }
        }
    }

    /**
     * Calculate changed elements
     * @param previous List of elements.
     * @param current  Possibly modified list of elements.
     */
    void checkChangedElements(List<User> previous, List<User> current) {
        for (User prevUser : previous) {
            for (User currUser : current) {
                if (prevUser.equals(currUser)) {
                    break;
                }
                if (prevUser.getId() == currUser.getId()) {
                    changed++;
                    break;
                }
            }
        }
    }

    /**
     * Calculate deleted elements
     * @param previous List of elements.
     * @param current  Possibly modified list of elements.
     */
    void checkDeletedElements(List<User> previous, List<User> current) {
        boolean flag = false;
        for (User prevUser : previous) {
            for (User currUser : current) {
                if ((prevUser.equals(currUser)) || (prevUser.getId() == currUser.getId())) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) {
                deleted++;
            }
        }
    }

    /**
     * Getter.
     */
    int getNumberOfAddedElements() {
        return added;
    }

    /**
     * Getter.
     */
    int getNumberOfChangedElements() {
        return changed;
    }

    /**
     * Getter.
     */
    int getNumberOfDeletedElements() {
        return deleted;
    }
}
