package cz.muni.fi.pv168.seminar01.beta.data.validation;

import cz.muni.fi.pv168.seminar01.beta.ui.model.TableCategory;
import cz.muni.fi.pv168.seminar01.beta.ui.utils.Shortcut;

public class RideCategoryValidator {
    public static void validateRideCategory(String id, String name) {
        if (id == null || !CommonValidator.isValidLongParsing(id) || Shortcut.getTableModel(TableCategory.RIDE_CATEGORY).getObjectById(Long.parseLong(id)) != null) {
            throw new ValidationException("Neplatné ID Kategorie jízd.");
        }
//        if (!CommonValidator.isValidLongAlphaSpaceString(name)) {
//            throw new ValidationException("Jméno musí obsahovat pouze písmena a mezeru a mít nejvýše 100 znaků.");
//        }
        //--TODO kvůli tomuto padá import
    }
}
