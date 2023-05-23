package unit7.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {


    public Connection getConnection(){
        try {
            var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "user");
            connection.setSchema("my_schema");
            return connection;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
