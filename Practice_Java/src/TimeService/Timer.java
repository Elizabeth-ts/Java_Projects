/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimeService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class Timer {

    public static void main(final String[] args) {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNext5;

        zonedNext5 = zonedNow.withHour(21).withMinute(0).withSecond(0);
        if (zonedNow.compareTo(zonedNext5) > 0) {
            zonedNext5 = zonedNext5.plusDays(1);
        }

        Duration duration = Duration.between(zonedNow, zonedNext5);
        long initalDelay = duration.getSeconds();

        ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1);
        schedular.scheduleAtFixedRate(new MyRunnableTask(), initalDelay, 30, TimeUnit.SECONDS);

    }
}

class MyRunnableTask implements Runnable, MySQL_DB_Query {

    private static int id = 0;
    private Connection jdbcConnection = null;
    private Statement jdbcStatement = null;
    private ResultSet jdbcResultSet = null;

    public MyRunnableTask() {
        try {
            id++;
            jdbcConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            jdbcStatement = jdbcConnection.createStatement();
            jdbcStatement.execute(CREATE_DATABASE);
            jdbcStatement.execute(CREATE_TABLE);
        } catch (SQLException ex) {
            Logger.getLogger(MyRunnableTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("ID: " + id + " - " + new Timestamp(new Date().getTime()));
            jdbcResultSet = jdbcStatement.executeQuery("SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME + ";");
            while (jdbcResultSet.next()) {
                System.out.println("Name: + " + jdbcResultSet.getString(TABLE_COLUMN_NAME)
                        + "Score: " + jdbcResultSet.getDouble(TABLE_COLUMN_SCORE));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyRunnableTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
