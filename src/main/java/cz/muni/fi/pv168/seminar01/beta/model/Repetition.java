package cz.muni.fi.pv168.seminar01.beta.model;

/**
 * Types of repeating of a ride.
 */
public enum Repetition {
    NONE {
        @Override
        public String toString() {
            return "neopakovat";
        }
    },
    DAILY {
        @Override
        public String toString() {
            return "denně";
        }
    },
    WEEKLY {
        @Override
        public String toString() {
            return "týdně";
        }
    },
    MONTHLY {
        @Override
        public String toString() {
            return "měsíčně";
        }
    },
    YEARLY {
        @Override
        public String toString() {
            return "ročně";
        }
    };

    public static Repetition fromString(String repetition) {
        return switch (repetition) {
            case "neopakovat" -> NONE;
            case "denně" -> DAILY;
            case "týdně" -> WEEKLY;
            case "měsíčně" -> MONTHLY;
            case "ročně" -> YEARLY;
            default -> null;
        };
    }
}
