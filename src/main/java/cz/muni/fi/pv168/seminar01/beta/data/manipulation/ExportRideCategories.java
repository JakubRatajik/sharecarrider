package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.RideCat;

import java.util.Arrays;
import java.util.List;

public class ExportRideCategories extends AbstractExporter<RideCat>{

    @Override
    protected String createCsvLine(RideCat element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getName());
        return String.join(ManipulationUtils.SEPARATOR, data);
    }
}
