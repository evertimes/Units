package unit10.servlet.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import unit10.db.LiquibaseMigration;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent e) {
        new LiquibaseMigration().runMigrations();
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {

    }
}
