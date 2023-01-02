package cz.muni.fi.pv168.seminar01.beta.error;

import cz.muni.fi.pv168.seminar01.beta.ui.MainWindow;
import cz.muni.fi.pv168.seminar01.beta.ui.dialogs.ErrorDialog;

public class ShareCarRiderUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new ErrorDialog(MainWindow.getFrame(),  "Nastala neočekávaná chyba: " + e.getMessage());
    }
}
