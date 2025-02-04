package cz.muni.fi.pv168.seminar01.beta.wiring;


import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;

/**
 * Dependency provider for production environment
 */
public final class ProductionDependencyProvider extends CommonDependencyProvider {

    public ProductionDependencyProvider() {
        super(getDatabaseManager());
    }

    private static DatabaseManager getDatabaseManager() {
        DatabaseManager databaseManager = DatabaseManager.createProductionInstance();
        databaseManager.initSchema();

        return databaseManager;
    }
}
