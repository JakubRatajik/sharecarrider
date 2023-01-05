package cz.muni.fi.pv168.seminar01.beta.ui.workers;

import cz.muni.fi.pv168.seminar01.beta.data.manipulation.ImporterBase;

import javax.swing.*;
import java.io.File;

public class AsyncImporter {
    public void importData(File rides, File vehicles, File passengers, File passengersCategories, File ridesCategories) {
        var asyncWorker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                ImporterBase.loadData(rides, vehicles, passengers, passengersCategories, ridesCategories);
                return null;
            }
        };
        asyncWorker.execute();
    }
}
