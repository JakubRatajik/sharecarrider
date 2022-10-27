package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import javax.swing.table.AbstractTableModel;

/**
 * @author Jakub Ratajik
 */
public class VehicleTableModel extends ShareCarRiderTableModel {
    public VehicleTableModel() {
        super(new String[] {"Značka", "Typ", "Počet míst", "Průměrná spotřeba"},
                new Object[][] {});
    }

    @Override
    public Object getEntity(int modelRow) {
        return null;
    }
}
