package dev.lpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicDML {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            String artist = "Neil Young";
            String query = "SELECT * FROM artists WHERE artist_name='%s'"
                    .formatted(artist);
            boolean result = statement.execute(query);
            System.out.println("result = " + result);
            var rs = statement.getResultSet();
            boolean found = (rs != null && rs.next());
            System.out.println("Artist was " + (found ? "found" : "not found"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
