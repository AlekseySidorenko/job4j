package ru.job4j.generics;

/**
 * Class RoleStore | Task Solution: Implement Store<T extends Base> [#157]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 27.07.2018
 */
class RoleStore<Role> extends AbstractStore {

    /**
     * Constructor.
     */
    public RoleStore(int size) {
        super(size);
    }
}