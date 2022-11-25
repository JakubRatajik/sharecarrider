package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

public record PassengerEntity(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber
) {
    public PassengerEntity(
            String firstName,
            String lastName,
            String phoneNumber
    ) {
        this(null, firstName, lastName, phoneNumber);
    }
}
