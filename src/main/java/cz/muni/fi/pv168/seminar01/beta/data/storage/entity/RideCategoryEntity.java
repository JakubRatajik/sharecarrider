package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

public record RideCategoryEntity(
        Long id,
        String name
) {
    public RideCategoryEntity (String name) {
        this(null, name);
    }
}
