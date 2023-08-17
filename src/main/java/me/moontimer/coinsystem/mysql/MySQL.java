package me.moontimer.coinsystem.mysql;

import com.sun.rowset.CachedRowSetImpl;
import me.moontimer.coinsystem.CoinSystem;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

public class MySQL {
    String ip, database, username, password;
    Integer port;

    private Connection connection;

    public MySQL(MySQLFile mySQLFile) {
        this.ip = mySQLFile.getIp();
        this.database = mySQLFile.getDatabase();
        this.username = mySQLFile.getUsername();
        this.password = mySQLFile.getPassword();
        this.port = mySQLFile.getPort();

        String url = "jdbc:mysql://" + mySQLFile.getIp() + "/" + mySQLFile.getDatabase();
        try {
            connection = DriverManager.getConnection(url, mySQLFile.getUsername(), mySQLFile.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void execute(final String query) {
        new BukkitRunnable() {
            @Override
            public void run() {

                try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("[]------[ MYSQL-ERROR ]------[]");
                    System.out.println("Error whilest performing execute.");
                    System.out.println("Command: " + query);
                    System.out.println("-> " + e.getMessage());
                }
            }
        }.runTaskAsynchronously(CoinSystem.getInstance());
    }

    public ResultSet executeQuery(final String qry) {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        try {

            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(qry);

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return crs;
    }

    public String getSpezificString(String table, String whatreturn, String bedingung, String value) {
        ResultSet  rs = executeQuery("SELECT * FROM " + table + " WHERE " + bedingung + "='" + value + "'");
        String returner = null;

        try {
            if(rs.next()) {
                returner = rs.getString(whatreturn);
            }
        } catch (SQLException e) {
            return null;
        }

        return returner;
    }

    public String getString(String table, String whatreturn) {
        ResultSet  rs = executeQuery("SELECT * FROM " + table);
        String returner = null;

        try {
            if(rs.next()) {
                returner = rs.getString(whatreturn);
            }
        } catch (SQLException e) {
            return null;
        }

        return returner;
    }

}
