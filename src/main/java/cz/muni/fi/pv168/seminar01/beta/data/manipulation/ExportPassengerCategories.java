package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;

import java.util.Arrays;
import java.util.List;

public class ExportPassengerCategories extends AbstractExporter<PassengerCategory> {
    @Override
    protected String createCsvLine(PassengerCategory element) {
        List<String> data = Arrays.asList(
                String.valueOf(element.getId()),
                element.getName());
        return String.join(ManipulationUtils.SEPARATOR, data);
    }
}
