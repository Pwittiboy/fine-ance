package fineance.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import fineance.framework.ScreensFramework;

/**
 * Class for checking connection to database
 * @author cfriis
 *
 */
public class DbConnect {
   
   private static final Logger LOGGER = Logger.getLogger(DbConnect.class); 

   /**
    * Check connection to database
    * @return
    */
   public static boolean connect() {

      String url = "jdbc:mysql://localhost:3306/";
      String database = "fineance";
      String username = ScreensFramework.DB_USERNAME;
      String password = ScreensFramework.DB_PASSWORD;
      
      try (Connection connection = DriverManager.getConnection(url+database, username, password)) {
         LOGGER.info("Database connection: Successful");
         return true;
      } catch (MySQLSyntaxErrorException e) {
         LOGGER.error(e);
         e.printStackTrace();
         return false;
      } catch (Exception e) {
         LOGGER.error(e);
         e.printStackTrace();
         return false;
      }
   }

}
