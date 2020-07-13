package ru.yiaaanni.dreamtime.dtmysqlplaceholders;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DTMySQLPlaceholders extends JavaPlugin {

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholdersAPI") != null) {
            new DTMPExtension().register();
        } else {
            System.out.println("§c[DTMySQLPlaceholders] Установите PlaceholdersAPI для работы данного плагина!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

    }
}
