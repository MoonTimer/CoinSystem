package me.moontimer.coinsystem.api;

import me.moontimer.coinsystem.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinAPI {

    MySQL mysql;
    public CoinAPI(MySQL mysql) {
        this.mysql = mysql;
    }

    public void createTable() {
        mysql.execute("CREATE TABLE IF NOT EXISTS coins(uuid TEXT, coins INT)");
    }

    public void loadCoins(Player p) {
        String result = mysql.getSpezificString("coins", "coins", "uuid", String.valueOf(p.getUniqueId()));
        Integer coins = 0;
        if (result == null) {
            mysql.execute("INSERT INTO coins(uuid, coins) VALUES ('" + p.getUniqueId() + "','100')");
        } else {
            coins = Integer.valueOf(result);
        }
    }

    public Integer getOfflineCoins(UUID uuid) {
        String result = mysql.getSpezificString("coins", "coins", "uuid", String.valueOf(uuid));
        Integer coins = 0;
        if (result == null) {
            coins = 0;
        } else {
            coins = Integer.valueOf(result);
        }
        return coins;
    }

    public Integer getCoins(UUID uuid) {
        ResultSet resultSet = mysql.executeQuery("SELECT coins FROM coins WHERE uuid='" + uuid + "'");
        try {
            if (resultSet.next()) {
                return resultSet.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasMinimumCoins(Player p, Integer coins) {
        Integer has = getCoins(p.getUniqueId());
        if (has >= coins) {
            return true;
        }
        return false;
    }

    public void setCoins(UUID uuid, Integer coins) {
        mysql.execute("UPDATE coins SET coins='" + coins + "' WHERE uuid='" + uuid + "'");
    }

    public void addCoins(UUID uuid, Integer coins) {
        mysql.execute("UPDATE coins SET coins='" + (coins + getCoins(uuid)) + "' WHERE uuid='" + uuid + "'");
    }

    public void removeCoins(UUID uuid, Integer coins) {
        mysql.execute("UPDATE coins SET coins='" + (getCoins(uuid) - coins) + "' WHERE uuid='" + uuid + "'");
    }
}