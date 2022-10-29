package cz.muni.fi.pv168.seminar01.beta.UI.Utils;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import java.time.LocalDate;

public final class JDatePickerDateGetter {
    public static LocalDate getLocalDate(JDatePicker picker) {
        DateModel<?> dateModel = picker.getModel();
        // January is 0th month, so +1 has to be added
        return LocalDate.of(dateModel.getYear(), dateModel.getMonth() + 1, dateModel.getDay());
    }
}
