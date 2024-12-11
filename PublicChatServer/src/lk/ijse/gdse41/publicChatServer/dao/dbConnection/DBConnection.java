package lk.ijse.gdse41.publicChatServer.dao.dbConnection;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by oshan on 18-Nov-17.
 */
public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException,SQLException{
        Properties dbPro=new Properties();
//        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("settings.properties");
        try {
            InputStream input = Files.newInputStream(new File("dbSettings/settings.properties").toPath());
            dbPro.load(input);
            String setDB = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC",
                    dbPro.getProperty("ip"),
                    dbPro.getProperty("port"),
                    dbPro.getProperty("database"));
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(setDB, dbPro.getProperty("username"), dbPro.getProperty("password"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
