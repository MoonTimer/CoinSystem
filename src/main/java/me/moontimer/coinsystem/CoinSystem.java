package me.moontimer.coinsystem;

import me.moontimer.coinsystem.api.CoinAPI;
import me.moontimer.coinsystem.commands.MoneyCommand;
import me.moontimer.coinsystem.mysql.MySQL;
import me.moontimer.coinsystem.mysql.MySQLFile;
import me.moontimer.coinsystem.placeholder.MoneyExpansion;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoinSystem extends JavaPlugin {

    private static CoinSystem instance;
    MySQLFile mySQLFile = new MySQLFile("plugins/CoinSystem/mysql.yml");
    MySQL mysql = null;
    CoinAPI coinAPI;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        mySQLFile.create();
        mySQLFile.loadDatas();
        mysql = new MySQL(mySQLFile);

        coinAPI = new CoinAPI(mysql);
        coinAPI.createTable();
        getCommand("money").setExecutor(new MoneyCommand());
        new MoneyExpansion().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CoinSystem getInstance() {
        return instance;
    }

    public MySQL getMysql() {
        return mysql;
    }

    public CoinAPI getCoinAPI() {
        return coinAPI;
    }
}
