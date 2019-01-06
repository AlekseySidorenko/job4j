package ru.job4j.nonblockingcache;

/**
 * Class Base | Task Solution: Non-blocking cache [#4741]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.01.2019
 */
public class Base {

    private final int id;
    private int version = 0;

    /**
     * Constructor.
     */
    public Base(int id) {
        this.id = id;
    }

    /**
     * Getter
     */
    public int getId() {
        return id;
    }

    /**
     * Getter
     */
    public int getVersion() {
        return version;
    }

    /**
     * Increment version.
     */
    public void setVersion() {
        this.version++;
    }
}
