package xyz.invisraidinq.atlanta.utils;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import xyz.invisraidinq.atlanta.Atlanta;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class Ranks {
	
    private static File f;
    private static FileConfiguration data;
    
    static {
        Ranks.f = new File(Atlanta.getInstance().getDataFolder(), "Ranks.yml");
        Ranks.data = (FileConfiguration)YamlConfiguration.loadConfiguration(Ranks.f);
    }
    
    public static String getString(final String path) {
        if (!Ranks.data.contains(path)) {
            return "Invis broke something";
        }
        return ChatColor.translateAlternateColorCodes('&', Ranks.data.getString(path));
    }
    
    public static List<String> getList(final String path) {
        if (!Ranks.data.contains(path)) {
            return Arrays.asList("Invis broke something");
        }
        return (List<String>)Ranks.data.getStringList(path);
    }
    
    public static void sendList(final Player p, final String path) {
        if (!Ranks.data.contains(path)) {
            p.sendMessage("Invis broke something");
        }
        else {
            final List<String> list = (List<String>)Ranks.data.getStringList(path);
            for (final String s : list) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }
    }
    
    @SuppressWarnings("deprecation")
	public static String getRank(final Player player) {
	      String prefix = new ColorUtils().translateFromString("");
	      final PermissionUser permissionUser = PermissionsEx.getUser(player);
	      for (final String s : permissionUser.getGroupNames()) {
	          switch (s) {
	              case "Owner": {
	                  prefix = Ranks.getString("ranks.Owner");
	                  break;
	              }
	              case "Co-Owner": {
	            	  prefix = Ranks.getString("ranks.Co-Owner");
	            	  break;
	              }
	              case "Developer": {
	                  prefix = Ranks.getString("ranks.Developer");
	                  break; 
	              }
	              case "Builder": {
	            	  prefix = Ranks.getString("ranks.Builder");
	            	  break;
	              }
	              case "Manager": {
	                  prefix = Ranks.getString("ranks.Manager");
	                  break; 
	              }
	              case "Platform-Admin": {
	            	  prefix = Ranks.getString("ranks.Platform-Admin");
	            	  break;
	              }
	              case "SrAdmin": {
	                  prefix = Ranks.getString("ranks.SrAdmin");
	                  break; 
	              }
	              case "Admin": {
	                  prefix = Ranks.getString("ranks.Admin");
	                  break; 
	              }
	              case "SrMod": {
	                  prefix = Ranks.getString("ranks.SrMod");
	                  break; 
	              }
	              case "Mod": {
	                  prefix = Ranks.getString("ranks.Mod");
	                  break; 
	              }
	              case "Trial-Mod": {
	                  prefix = Ranks.getString("ranks.Trial-Mod");
	                  break; 
	              }
	              case "Helper": {
	            	  prefix = Ranks.getString("ranks.Helper");
	            	  break;
	              }
	              case "Saruman": {
	                  prefix = Ranks.getString("ranks.Saruman");
	                  break; 
	              }
	              case "Radagast": {
	                  prefix = Ranks.getString("ranks.Radagast");
	                  break; 
	              }
	              case "Alatar": {
	                  prefix = Ranks.getString("ranks.Alatar");
	                  break; 
	              }
	              case "Azure": {
	                  prefix = Ranks.getString("ranks.Azure");
	                  break; 
	              }
	              case "Wizard": {
	                  prefix = Ranks.getString("ranks.Wizard");
	                  break; 
	              }
	              case "YouTube": {
	                  prefix = Ranks.getString("ranks.YouTube");
	                  break; 
	              }
	              case "Famous": {
	            	  prefix = Ranks.getString("ranks.Famous");
	            	  break;
	              }
	              case "Partner": {
	            	  prefix = Ranks.getString("ranks.Partner");
	            	  break;
	              }
	              default: {
	                  prefix = Ranks.getString("ranks.default");
	                  break;
	              }
	          }
	      }
	      return prefix;
	  }

}

