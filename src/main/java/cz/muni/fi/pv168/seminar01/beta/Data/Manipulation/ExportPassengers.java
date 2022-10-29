package cz.muni.fi.pv168.seminar01.beta.Data.Manipulation;

import cz.muni.fi.pv168.seminar01.beta.Model.Passenger;

public class ExportPassengers extends AbstractExporter<Passenger> {

    @Override
    protected String createCsvLine(Passenger element) {
        return element.getId() + SEPARATOR +
                element.getFirstName() + SEPARATOR +
                element.getLastName() + SEPARATOR +
                element.getPhoneNumber() + SEPARATOR +
                element.getCategories();
    }
    // TODO - getCategories method should probably return names instead of toStrings
}
