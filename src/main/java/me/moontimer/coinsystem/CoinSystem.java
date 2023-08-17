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

        coinAPI = new CoinAPI();
        coinAPI.createTable();
        getCommand("money").setExecutor(new MoneyCommand());
        new MoneyExpansion().register();
        getLogger().info("");
        getLogger().info("");
        getLogger().info("");
        getLogger().info(" _______  _______ _________ _        _______  _______ _________\n" +
                "(  ____ \\(  ___  )\\__   __/( (    /|(  ___  )(  ____ )\\__   __/\n" +
                "| (    \\/| (   ) |   ) (   |  \\  ( || (   ) || (    )|   ) (   \n" +
                "| |      | |   | |   | |   |   \\ | || (___) || (____)|   | |   \n" +
                "| |      | |   | |   | |   | (\\ \\) ||  ___  ||  _____)   | |   \n" +
                "| |      | |   | |   | |   | | \\   || (   ) || (         | |   \n" +
                "| (____/\\| (___) |___) (___| )  \\  || )   ( || )      ___) (___\n" +
                "(_______/(_______)\\_______/|/    )_)|/     \\||/       \\_______/");
        getLogger().info("");
        getLogger().info("");
        getLogger().info("                                        §aBy MoonTimer");
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
