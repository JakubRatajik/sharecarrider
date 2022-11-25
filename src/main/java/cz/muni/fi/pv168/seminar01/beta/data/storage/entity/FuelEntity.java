package cz.muni.fi.pv168.seminar01.beta.data.storage.entity;

import java.math.BigDecimal;

public record FuelEntity(
        String fuelType,
        BigDecimal price
) {
}
