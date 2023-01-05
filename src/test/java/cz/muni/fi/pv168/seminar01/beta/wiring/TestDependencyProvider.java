package cz.muni.fi.pv168.seminar01.beta.wiring;

import cz.muni.fi.pv168.seminar01.beta.data.storage.db.DatabaseManager;

/**
 * Dependency provider for test environment
 */
public final class TestDependencyProvider extends CommonDependencyProvider {

    public TestDependencyProvider(DatabaseManager databaseManager) {
        super(databaseManager);
    }
}
