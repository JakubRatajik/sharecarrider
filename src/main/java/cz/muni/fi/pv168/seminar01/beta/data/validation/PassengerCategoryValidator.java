package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

public class PassengerCategoryValidator {
    public static void validatePassengerCategory(String id, String name) {
        if (id == null || !CommonValidator.isValidLongParsing(id) || Shortcut.getTableModel(TableCategory.PASSENGER_CATEGORY).getObjectById(Long.parseLong(id)) != null) {
            throw new ValidationException("Neplatné ID Kategorie pasažérů.");
        }
//        if (!CommonValidator.isValidLongAlphaSpaceString(name)) {
//            throw new ValidationException("Jméno musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
//        }
        //--TODO kvůli tomuto padá import
    }
}
