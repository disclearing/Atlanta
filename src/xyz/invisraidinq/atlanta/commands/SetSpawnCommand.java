package xyz.invisraidinq.atlanta.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.utils.C;

public class SetSpawnCommand implements CommandExecutor, Listener {
    Atlanta main;

    public SetSpawnCommand(Atlanta plugin) {
        this.main = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("setspawn") && !(sender instanceof Player)) {
            sender.sendMessage(C.chat(Atlanta.config.getString("no_perm_message")));
            return true; 
        } else {
            if(sender.hasPermission("Atlanta.setspawn")) {
                Player p = (Player)sender;
                Location l = p.getLocation();
                int xc = l.getBlockX();
                int yc = l.getBlockY();
                int zc = l.getBlockZ();
                p.getWorld().setSpawnLocation(xc, yc, zc);
                p.sendMessage(C.chat(Atlanta.config.getString("set_spawn_message")));
            }

            return true;
        }
        
    }
}