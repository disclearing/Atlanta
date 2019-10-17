package xyz.invisraidinq.atlanta.utils;

import org.bukkit.entity.Player;

import xyz.invisraidinq.atlanta.Atlanta;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class Tablist {
	
    private static File f;
    private static FileConfiguration data;
    
    static {
        Tablist.f = new File(Atlanta.getInstance().getDataFolder(), "Tablist.yml");
        Tablist.data = (FileConfiguration)YamlConfiguration.loadConfiguration(Tablist.f);
    }
    
    public static String getString(final String path) {
        if (!Tablist.data.contains(path)) {
            return "Invis broke something";
        }
        return ChatColor.translateAlternateColorCodes('&', Tablist.data.getString(path));
    }
    
    public static List<String> getList(final String path) {
        if (!Tablist.data.contains(path)) {
            return Arrays.asList("Invis broke something");
        }
        return (List<String>)Tablist.data.getStringList(path);
    }
    
    public static void sendList(final Player p, final String path) {
        if (!Tablist.data.contains(path)) {
            p.sendMessage("Invis broke something");
        }
        else {
            final List<String> list = (List<String>)Tablist.data.getStringList(path);
            for (final String s : list) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }
    }

}

