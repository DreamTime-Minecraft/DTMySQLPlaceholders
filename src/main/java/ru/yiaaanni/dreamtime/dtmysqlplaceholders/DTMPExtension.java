package ru.yiaaanni.dreamtime.dtmysqlplaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class DTMPExtension extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "mysql";
    }

    @Override
    public String getAuthor() {
        return "yiaaanni";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        saveToConfig(params.split("_"));
        return params;
    }

    private synchronized void saveToConfig(String[] args) {
        /*String database = args[0];
        String table = args[1];
        String anyname = args[2];
        String whatselect = args[3];
        String wherewhat = args[4];
        String whereequals = args[5];*/
        String code = args[0];
    }

}
