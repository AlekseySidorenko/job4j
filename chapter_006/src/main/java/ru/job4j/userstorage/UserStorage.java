package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * Class Count | Task Solution: Threadsafe UserStorage class [#1104]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 25.12.2018
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Set<User> users = new HashSet<>();

    /**
     * Add user to storage.
     * @param user user.
     * @return true if user was added.
     */
    public synchronized boolean add(User user) {
        return users.add(user);
    }

    /**
     * Delete user from storage.
     * @param user user.
     * @return true if user was deleted.
     */
    public synchronized boolean delete(User user) {
        boolean result = false;
        if (users.contains(user)) {
            users.remove(user);
            result = true;
        }
        return result;
    }

    /**
     * Update user int storage.
     * @param user user.
     * @param amount new amount
     * @return true if user was updated.
     */
    public synchronized boolean update(User user, int amount) {
        boolean result = false;
        if (users.contains(user)) {
            user.setAmount(user.getAmount() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Transfer amount between users.
     * @param fromId Source user's id.
     * @param toId   Target user's id.
     * @param amount amount
     * @return true if transfer is ok.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User sourceUser = null;
        User targetUser = null;
        boolean result = false;
        for (User element : users) {
            if (element.getId() == fromId) {
                sourceUser = element;
            }
            if (element.getId() == toId) {
                targetUser = element;
            }
        }
        if (sourceUser != null && targetUser != null) {
            this.update(sourceUser, -amount);
            this.update(targetUser, amount);
            result = true;
        }
        return result;
    }
}