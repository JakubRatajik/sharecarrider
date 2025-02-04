package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.HasID;
import cz.muni.fi.pv168.seminar01.beta.model.Ride;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class creates one line for a csv exporter of rides.
 */
public class RideExporter extends AbstractExporter<Ride> {
    @Override
    protected String createCsvLine(Ride element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getDateUnformatted(),
                element.getDepartureFormatted(),
                element.getArrivalFormatted(),
                element.getFrom(),
                element.getTo(),
                String.valueOf(element.getDistance()),
                getIDs(element.getCategories()).toString(),
                getIDs(element.getPassengers()).toString(),
                String.valueOf(element.getVehicle().getId()),
                element.getRepetition().name(),
                element.getDescription());

        return String.join(ManipulationUtils.SEPARATOR, data);
    }

    private <T extends HasID> List<Long> getIDs(Collection<T> elements) {
        return elements.stream().map(HasID::getId).toList();
    }
}
