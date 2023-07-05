package unit8;

import java.sql.SQLException;
import liquibase.exception.LiquibaseException;
import unit8.db.LiquibaseMigration;

public class App {

    public static void main(String[] args) throws SQLException, LiquibaseException {
        new LiquibaseMigration().runMigrations();
        var executor = new Executor();
        executor.executeAll();
    }


}
