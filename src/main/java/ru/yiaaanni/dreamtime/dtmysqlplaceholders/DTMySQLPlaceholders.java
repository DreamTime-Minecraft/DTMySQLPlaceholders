package ru.yiaaanni.dreamtime.dtmysqlplaceholders;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class DTMySQLPlaceholders extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if(Bukkit.getPluginManager().getPlugin("PlaceholdersAPI") != null) {
            new DTMPExtension().register();
        } else {
            System.out.println("§c[DTMySQLPlaceholders] Установите PlaceholdersAPI для работы данного плагина!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        anysData();
    }

    private synchronized void anysData() {
        String host = getConfig().getString("mysql.host");
        int port = getConfig().getInt("mysq.port");
        Set<String> databases = getConfig().getConfigurationSection("mysql.databases").getKeys(false);
        for(String key : databases) {
            MySQL mysql = new MySQL(host, getConfig().getString("mysql.databases."+key+".user"),
                    getConfig().getString("mysql.databases."+key+".password"), port, key);

            Set<String> codes = getConfig().getConfigurationSection("get-from."+key).getKeys(false);
            for(String code : codes) {
                String table = getConfig().getString("get-from."+key+"."+code+".table");
                String select = getConfig().getString("get-from."+key+"."+code+".select");
                String whereWhat = getConfig().getString("get-from."+key+"."+code+".where-what");
                String whereEquals = getConfig().getString("get-from."+key+"."+code+".where-equals");
                getConfig().set("answers."+code, mysql.get(table, select, whereWhat, whereEquals));
            }

            try {
                mysql.getMamka().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {

    }
}
