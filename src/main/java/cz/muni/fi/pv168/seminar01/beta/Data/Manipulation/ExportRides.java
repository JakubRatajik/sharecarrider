package cz.muni.fi.pv168.seminar01.beta.Data.Manipulation;

import cz.muni.fi.pv168.seminar01.beta.Model.HasID;
import cz.muni.fi.pv168.seminar01.beta.Model.Ride;

import java.io.BufferedWriter;
import java.util.Collection;
import java.util.List;

public class ExportRides extends AbstractExporter<Ride>{
    @Override
    protected String createCsvLine(Ride element) {
        return element.getId() + SEPARATOR +
                element.getDateUnformatted() + SEPARATOR +
                element.getTime() + SEPARATOR +
                element.getFrom() + SEPARATOR +
                element.getTo() + SEPARATOR +
                element.getDistance() + SEPARATOR +
                getIDs(element.getCategories()) + SEPARATOR +
                getIDs(element.getPassengers()) + SEPARATOR +
                element.getVehicle().getId() + SEPARATOR +
                element.getRepetition().name();
    }

    @Override
    protected void writeAfterMain(BufferedWriter writer) {
        return; // TODO - ride categories
    }

    private <T extends HasID> List<Integer> getIDs(Collection<T> elements) {
        return elements.stream().map(HasID::getId).toList();
    }
}
