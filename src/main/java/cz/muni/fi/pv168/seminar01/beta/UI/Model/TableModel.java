package cz.muni.fi.pv168.seminar01.beta.UI.Model;

import cz.muni.fi.pv168.seminar01.beta.UI.TabFrame;
import cz.muni.fi.pv168.seminar01.beta.UI.Utils.TableInitializer;

import javax.swing.*;

/**
 * @author Jan Macecek
 *
 * TODO - temporary class!!!
 */
public class TableModel {
    private JTable table;
    private TabCategory category;

    public TableModel(TabCategory category) {
        this.category = category;
    }

    // TODO - KUBIK, it would be great, if you can load actual JTable object to table variable
    public void initializeFrame(TabFrame frame) {
        TableInitializer.initializeTab(frame, category);
    }

    public JTable getTable() {
        return table;
    }
}
