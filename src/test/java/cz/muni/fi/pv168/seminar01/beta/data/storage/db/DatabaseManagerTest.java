package cz.muni.fi.pv168.seminar01.beta.data.storage.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

final class DatabaseManagerTest {
    private DatabaseManager manager;

    @BeforeEach
    void setUp() {
        this.manager = DatabaseManager.createTestInstance();
    }

    @AfterEach
    void tearDown() {
        manager.destroySchema();
    }

    /**
     * The {@link DatabaseManager#initSchema()} is called inside {@link DatabaseManager#createTestInstance()}
     * this is the second time it is called
     */
    @Test
    void schemaInitializationShouldBeIdempotent() {
        assertThatCode(() -> manager.initSchema()).doesNotThrowAnyException();
    }

    @Test
    void destroySchemaShouldByValid() {
        assertThatCode(() -> manager.destroySchema()).doesNotThrowAnyException();
    }
}
