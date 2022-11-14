package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.HasID;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;

import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ExportRides extends AbstractExporter<Ride> {
    @Override
    protected String createCsvLine(Ride element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getDateUnformatted(),
                element.getTime(),
                element.getFrom(),
                element.getTo(),
                String.valueOf(element.getDistance()),
                getIDs(element.getCategories()).toString(),
                getIDs(element.getPassengers()).toString(),
                String.valueOf(element.getVehicle().getId()),
                element.getRepetition().name());

        return String.join(SEPARATOR, data);
    }

    @Override
    protected void writeFooter(BufferedWriter writer) {
        return; // TODO - ride categories
    }

    private <T extends HasID> List<Long> getIDs(Collection<T> elements) {
        return elements.stream().map(HasID::getId).toList();
    }
}
