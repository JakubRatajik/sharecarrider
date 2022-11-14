package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;

import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.List;

public class ExportPassengers extends AbstractExporter<Passenger> {

    @Override
    protected String createCsvLine(Passenger element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getFirstName(),
                element.getLastName(),
                element.getPhoneNumber(),
                element.getCategories().toString());

        return String.join(SEPARATOR, data);
    }

    @Override
    protected void writeFooter(BufferedWriter writer) {
        return;
    }
    // TODO - getCategories method should probably return names instead of toStrings
}
