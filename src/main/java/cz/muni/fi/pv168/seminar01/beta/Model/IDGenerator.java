package cz.muni.fi.pv168.seminar01.beta.Model;

public final class IDGenerator {
    private static int ID = 0;

    public static int getNewID() {
        int id = ID;
        ID++;
        return id;
    }
}
