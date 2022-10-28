package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import cz.muni.fi.pv168.seminar01.beta.Model.Vehicle;

/**
 * @author Jakub Ratajik
 */
public class VehicleTableModel extends ShareCarRiderTableModel {
    public VehicleTableModel() {
        super(new String[]{"Značka", "Typ", "Počet míst", "Průměrná spotřeba"},
                new Object[][]{});
    }

    @Override
    public Vehicle getEntity(int modelRow) {
        return null;
    }
}
