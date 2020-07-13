package ru.yiaaanni.dreamtime.dtmysqlplaceholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.util.Set;

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

        return params;
    }



}
