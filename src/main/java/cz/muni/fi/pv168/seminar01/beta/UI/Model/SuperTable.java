package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import cz.muni.fi.pv168.seminar01.beta.Model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.UI.ShareCarRiderTable;
import cz.muni.fi.pv168.seminar01.beta.UI.TabFrame;

import javax.swing.*;

/**
 * @author Jan Macecek
 * <p>
 * TODO - temporary class!!!
 */
public class SuperTable {
    private final TableCategory category;
    private JTable table;

    public SuperTable(TableCategory category) {
        this.category = category;
    }

    // TODO - KUBIK, it would be great, if you can load actual JTable object to table variable
    public void initializeFrame(TabFrame frame) {
        new ShareCarRiderTable(category);
    }

    public JTable getTable() {
        return table;
    }
}
