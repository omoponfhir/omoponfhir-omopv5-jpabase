package edu.gatech.chai.flyway;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FlywayPostgresMigrationsTest {
    public static JdbcDatabaseContainer postgres;

    public static HikariDataSource datasource;

    @BeforeAll
    public static void setup() {
        postgres = new PostgreSQLContainer<>().withInitScript("ddl/postgresql/omop.cdm.v5.3.1.pg.sql");
        postgres.start();
    }

    // From here: https://github.com/testcontainers/testcontainers-java/blob/aa1c65b6efcc6d052fe71043d9985d69df70771f/modules/jdbc-test/src/test/java/org/testcontainers/junit/AbstractContainerDatabaseTest.java
    HikariDataSource getDataSource(JdbcDatabaseContainer container) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(container.getJdbcUrl());
        hikariConfig.setUsername(container.getUsername());
        hikariConfig.setPassword(container.getPassword());
        hikariConfig.setDriverClassName(container.getDriverClassName());

        return new HikariDataSource(hikariConfig);
    }

    ResultSet performQuery(JdbcDatabaseContainer container, String sql) throws SQLException {
        datasource = getDataSource(container);
        Statement statement = datasource.getConnection().createStatement();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        resultSet.next();
        return resultSet;
    }

    ResultSet getTableIndices(JdbcDatabaseContainer container, String tableName) throws SQLException {
        datasource = getDataSource(container);
        return datasource.getConnection().getMetaData().getIndexInfo(null, null, tableName, false, false);
    }

    ResultSet getTableMetadata(JdbcDatabaseContainer container, String tableName) throws SQLException {
        datasource = getDataSource(container);
        return datasource.getConnection().getMetaData().getTables(null, null, tableName, null);
    }

    @BeforeEach
    public void runMigrations() throws Exception {
        Flyway flyway = Flyway
            .configure()
            .dataSource(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())
            .locations("classpath:flyway/migrations/postgresql/")
            .baselineOnMigrate(true)
            .load();

        flyway.migrate();
    }

    @Test
    public void testFPerson() throws Exception {
        // Check the table exists
        ResultSet resultSet = performQuery(postgres, "SELECT * from f_person");

        // Count the number of columns
        assertEquals("Ensure we have the correct number of columns", 13, resultSet.getMetaData().getColumnCount());

        // Check the index was created
        resultSet = getTableIndices(postgres, "f_person");

        boolean indexExists = false;
        while (resultSet.next()) {
            if (resultSet.getString("INDEX_NAME").equals("f_person_person_id_idx")) {
                indexExists = true;
            }
        }

        assertEquals("Ensure index exists", true, indexExists);
    }

    @Test
    public void testObservationView() throws Exception {
        // Check the view exists
        ResultSet resultSet = null;

        try {
            resultSet = performQuery(postgres, "SELECT * from  f_observation_view");
        } catch (Exception e) {
            assertNull("Ensure table exists", e);
        }

        // Count the columns
        assertEquals("Ensure we have the correct number of columns", 21, resultSet.getMetaData().getColumnCount());

        // Check that it's a view
        resultSet = getTableMetadata(postgres, "f_observation_view");

        resultSet.next();
        assertEquals("Ensure we have a view", true, resultSet.getString("TABLE_TYPE").equals("VIEW"));
    }

    @Test
    public void testIdMapping() throws Exception {
        // Check the table exists
        ResultSet resultSet = performQuery(postgres, "SELECT * from id_mapping");

        // Count the number of columns
        assertEquals("Ensure we have the correct number of columns", 4, resultSet.getMetaData().getColumnCount());

        // Check the index was created
        resultSet = getTableIndices(postgres, "id_mapping");

        boolean indexExists = false;
        while (resultSet.next()) {
            if (resultSet.getString("INDEX_NAME").equals("id_mapping_pkey")) {
                indexExists = true;
            }
        }

        assertEquals("Ensure index exists", true, indexExists);
    }

    @AfterAll
    public static void cleanup() {
        if (datasource != null) {
            datasource.close();
        }

        if (postgres != null) {
            postgres.stop();
        }
    }
}
