package me.moontimer.coinsystem.mysql;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLFile {
    String source;
    String ip, database, username, password;
    Integer port;

    File file;
    FileConfiguration fileConfiguration;

    public MySQLFile(String source) {
        this.source = source;

        file = new File(source);
        fileConfiguration = YamlConfiguration.loadConfiguration(file);

    }

    public void create() {

        if(fileConfiguration.getString("ip") == null) {

            fileConfiguration.set("ip", "127.0.0.1");
            fileConfiguration.set("database", "DATABASE");
            fileConfiguration.set("username", "USERNAME");
            fileConfiguration.set("password", "PASSWORD");
            fileConfiguration.set("port", 3306);

            try {
                fileConfiguration.save(file);
            } catch(IOException ex) {
                Bukkit.getConsoleSender().sendMessage("§4Error while saving MySQL-File Configuration.");
                ex.printStackTrace();
            }

        }

    }

    public void loadDatas() {
        ip = fileConfiguration.getString("ip");
        database = fileConfiguration.getString("database");
        username = fileConfiguration.getString("username");
        password = fileConfiguration.getString("password");
        port = fileConfiguration.getInt("port");
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
