package cz.muni.fi.pv168.seminar01.beta.Model;

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
    }
}
