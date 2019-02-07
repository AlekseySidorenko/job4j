package ru.job4j.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class NonBlockingCache | Task Solution: Non-blocking cache [#4741]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.01.2019
 */
public class NonBlockingCache {

    ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<>();

    /**
     * Add model to storage.
     * @param model model.
     */
    public void add(Base model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * Delete model from storage.
     * @param model model.
     */
    public void delete(Base model) {
        storage.remove(model.getId(), model);
    }

    /**
     * Get model from storage.
     * @param model model.
     * @return model model.
     */
    public Base getModel(Base model) {
        return storage.get(model.getId());
    }

    /**
     * Update model in storage.
     * @param model model.
     */
    public void update(Base model) {
        storage.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != storage.get(key).getVersion()) {
                throw new OptimisticException("Versions are different");
            }
            model.setVersion();
            return model;
        });
    }
}