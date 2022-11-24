package cz.muni.fi.pv168.seminar01.beta.model;

/**
 * @author Jan Macecek
 */
public class RideCategory extends Category {


        public RideCategory(long id, String name) {
            super(id, name);
        }

        public RideCategory(String name) {
            super(name);
        }


        @Override
        public String toString() {
            return this.getName();
        }
}
