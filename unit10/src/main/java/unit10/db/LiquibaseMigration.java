package unit10.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class LiquibaseMigration {

    public void runMigrations() throws SQLException, LiquibaseException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "user");
        connection.setAutoCommit(false);
        connection.setSchema("my_schema");
        Database database = DatabaseFactory
            .getInstance()
            .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("liquibase/changelog.xml",
                                            new ClassLoaderResourceAccessor(),
                                            database);
        liquibase.update();
        connection.commit();
        connection.setAutoCommit(true);
    }
}
