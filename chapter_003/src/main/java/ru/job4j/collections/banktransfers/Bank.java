package ru.job4j.collections.banktransfers;

import java.util.*;

/**
 * Class Bank Task Solution: Bank transfers on Stream API [#24260]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.04.2018
 */
class Bank {
    private TreeMap<User, ArrayList<Account>> storage = new TreeMap<>();

    /**
     * Add user to storage.
     * @param user user.
     */
    void addUser(User user) {
        this.storage.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Remove user from storage.
     * @param user user.
     */
    void deleteUser(User user) {
        this.storage.remove(user);
    }

    /**
     * Add account to user in storage.
     * @param user user.
     * @param account account.
     */
    void addAccount(User user, Account account) {
        this.storage.get(user).add(account);

    }

    /**
     * Remove account from user in storage.
     * @param user user.
     * @param account account.
     */
    void deleteAccount(User user, Account account) {
        this.storage.get(user).remove(account);
    }

    /**
     * Get all user's account from storage.
     * @param user user.
     * @return List with user's accounts
     */
    List<Account> getUserAccounts(User user) {
        return this.storage.get(user);
    }

    /**
     * Find user in storage.
     * @param passport passport.
     * @return found user or null
     */
    User findUserInStorage(String passport) {
        User result = null;
        Optional<User> optionalUser;
        optionalUser = storage.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
        if (optionalUser.isPresent()) {
            result = optionalUser.get();
        }
        return result;
    }

    /**
     * Find account in storage.
     * @param requisites requisites.
     * @return found account or null
     */
    Account findAccountInStorage(String requisites) {
        Account result = null;
        Optional<Account> optionalAccount;
        List<Account> accountsList = new ArrayList<>();
        this.storage.forEach((user, accounts) -> accountsList.addAll(accounts));
        optionalAccount = accountsList.stream()
                .filter(account -> account.getRequisites().equals(requisites))
                .findFirst();
        if (optionalAccount.isPresent()) {
            result = optionalAccount.get();
        }
        return result;
    }

    /**
     * Validate possibility of money transfer.
     * @param srcPassport srcPassport.
     * @param srcRequisites srcRequisites.
     * @param destPassport destPassport.
     * @param destRequisites destRequisites.
     * @param amount amount.
     * @return validated (true) or not validated (false)
     */
    private boolean transferValidate(String srcPassport,
                                     String srcRequisites,
                                     String destPassport,
                                     String destRequisites,
                                     double amount) {
        boolean result = false;
        User srcUser = findUserInStorage(srcPassport);
        Account srcAccount = findAccountInStorage(srcRequisites);
        User destUser = findUserInStorage(destPassport);
        Account destAccount = findAccountInStorage(destRequisites);
        if ((srcUser != null)
                && (srcAccount != null)
                && (destUser != null)
                && (destAccount != null)
                && (storage.get(srcUser).contains(srcAccount))
                && (storage.get(destUser).contains(destAccount))
                && (srcAccount.getValue() >= amount)) {
            result = true;
        }
        return result;
    }

    /**
     * Transfer money from account to other account.
     * @param srcPassport srcPassport.
     * @param srcRequisites srcRequisites.
     * @param destPassport destPassport.
     * @param destRequisites destRequisites.
     * @param amount amount.
     * @return money transferred (true) or not transferred (false)
     */
    boolean transferMoney(String srcPassport, String srcRequisites,
                                 String destPassport, String destRequisites,
                                 double amount) {
        boolean result = false;
        if (transferValidate(srcPassport,
                             srcRequisites,
                             destPassport,
                             destRequisites,
                             amount)) {
            Account srcAccount = findAccountInStorage(srcRequisites);
            Account destAccount = findAccountInStorage(destRequisites);
            if ((srcAccount != null) && (destAccount != null)) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                destAccount.setValue(destAccount.getValue() + amount);
                result = true;
            }
        }
        return result;
    }
}