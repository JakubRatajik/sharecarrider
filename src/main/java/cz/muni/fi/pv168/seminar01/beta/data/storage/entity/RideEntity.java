package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

import cz.muni.fi.pv168.seminar01.beta.model.Repetition;

import java.time.LocalDate;
import java.time.LocalTime;

public record RideEntity(
        Long id,
        LocalDate date,
        LocalTime departure,
        LocalTime arrival,
        String startDest,
        String endDest,
        int distance,
        long vehicleId,
        Repetition repetition,
        String description
) {
    public RideEntity(
            LocalDate date,
            LocalTime departure,
            LocalTime arrival,
            String startDest,
            String endDest,
            int distance,
            long vehicleId,
            Repetition repetition,
            String description
    ) {
        this(null, date, departure, arrival, startDest, endDest, distance, vehicleId, repetition, description);
    }
}
