package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Vehicle;

import java.util.Arrays;
import java.util.List;

public final class ExportVehicles extends AbstractExporter<Vehicle> {

    @Override
    protected String createCsvLine(Vehicle element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getLicensePlate(),
                element.getBrand(),
                element.getType(),
                String.valueOf(element.getCapacity()),
                String.valueOf(element.getConsumption()),
                element.getFuelType().name());

        return String.join(SEPARATOR, data);
    }
}
