package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;

import java.util.Arrays;
import java.util.List;

/**
 * This class creates one line for a csv exporter of passengers.
 */
public class ExportPassengers extends AbstractExporter<Passenger> {

    @Override
    protected String createCsvLine(Passenger element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getFirstName(),
                element.getLastName(),
                element.getPhoneNumber(),
                element.getCategoryIDs().toString());

        return String.join(SEPARATOR, data);
    }

    // TODO - getCategories method should probably return names instead of toStrings
}
