package ru.yiaaanni.dreamtime.dtmysqlplaceholders;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class DTMySQLPlaceholders extends JavaPlugin {

    public static DTMySQLPlaceholders ins;

    @Override
    public void onEnable() {
        ins = this;
        saveDefaultConfig();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new DTMPExtension().register();
        } else {
            System.out.println("§c[DTMySQLPlaceholders] Установите PlaceholderAPI для работы данного плагина!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        anysData();
    }

    private synchronized void anysData() {
        String host = getConfig().getString("mysql.host");
        int port = getConfig().getInt("mysql.port");
        Set<String> databases = getConfig().getConfigurationSection("mysql.databases").getKeys(false);
        for(String key : databases) {
            String user = getConfig().getString("mysql.databases."+key+".user");
            String password = getConfig().getString("mysql.databases."+key+".password");
            MySQL mysql = new MySQL(host, user, password, port, key);

            Set<String> codes = getConfig().getConfigurationSection("get-from."+key).getKeys(false);
            for(String code : codes) {
                String table = getConfig().getString("get-from."+key+"."+code+".table");
                String select = getConfig().getString("get-from."+key+"."+code+".select");
                String whereWhat = getConfig().getString("get-from."+key+"."+code+".where-what");
                String whereEquals = getConfig().getString("get-from."+key+"."+code+".where-equals");
                String get = mysql.get(table, whereWhat, whereEquals, select);
                getConfig().set("answers."+code, get);
                System.out.println("§a[DTMySQLPlaceholders] Хуйскл " + get + " сохранен под кодом "
                        + code + " в ебаный конфиг этого хуевого плагина.");
            }

            try {
                mysql.getMamka().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        saveConfig();
    }

    @Override
    public void onDisable() {

    }
}
