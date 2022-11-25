package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

public record PassengerCategoryEntity(
        Long id,
        String name
) {
    public PassengerCategoryEntity(String name) {
        this(null, name);
    }
}
