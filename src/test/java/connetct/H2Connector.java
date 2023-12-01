package connetct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Connector {
    public static Connection get() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");
        String content = new String(Files.readAllBytes(Paths.get("src/test/java/resources/init.sql")));
        Statement stmt = connection.createStatement();
        stmt.execute(content);
        stmt.close();
        return connection;
    }
}
