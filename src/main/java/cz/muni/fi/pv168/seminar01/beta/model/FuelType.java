package cz.muni.fi.pv168.seminar01.beta.model;

public enum FuelType {
    DIESEL {
        @Override
        public String toString() {
            return "nafta";
        }
    },
    GASOLINE {
        @Override
        public String toString() {
            return "benzín";
        }
    },
    LPG,
    CNG,
    ELECTRICITY {
        @Override
        public String toString() {
            return "elektřina";
        }
    }

}
