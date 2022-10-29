package cz.muni.fi.pv168.seminar01.beta.Data.Manipulation;

import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;

public final class ExportVehicle extends AbstractExporter<Vehicle> {

    @Override
    protected String createCsvLine(Vehicle element) {
        return element.getId() + SEPARATOR +
                element.getLicensePlate() + SEPARATOR +
                element.getBrand() + SEPARATOR +
                element.getType() + SEPARATOR +
                element.getCapacity() + SEPARATOR +
                element.getConsumption() + SEPARATOR +
                element.getFuelType().name();
    }
}
