package cz.muni.fi.pv168.seminar01.beta.data.manipulation;

import cz.muni.fi.pv168.seminar01.beta.model.Category;
import cz.muni.fi.pv168.seminar01.beta.model.Passenger;
import cz.muni.fi.pv168.seminar01.beta.model.PassengerCategory;
import cz.muni.fi.pv168.seminar01.beta.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.model.PassengerCategoryTableModel;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Long> cats = ((PassengerCategoryTableModel) Shortcut.getTableModel(TableCategory.PASSENGERCATEGORY)).getCategories().stream().map(Category::getId).toList();
        System.out.println("START");
        for (long cat: cats) {
            System.out.println(cat);
        }
        System.out.println("END");
        return String.join(SEPARATOR, data);
    }

    // TODO - getCategories method should probably return names instead of toStrings
}
