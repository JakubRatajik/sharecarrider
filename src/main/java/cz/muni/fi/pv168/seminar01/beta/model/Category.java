package cz.muni.fi.pv168.seminar01.beta.model;

/**
 * @author Jan Macecek
 */
public abstract class Category implements HasID {
    private final long id;
    private String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.id = IDGenerator.getNewID(Category.class);
        this.name = name;
    }

    public long getId() {
        return this.id;
    }
}
