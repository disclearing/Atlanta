package xyz.invisraidinq.atlanta.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.utils.C;

public class NightCommand implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] arguments) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
                if (arguments.length == 0) {
                    player.setPlayerTime(14000L, false);
                    player.sendMessage(C.chat(Atlanta.config.getString("night_time_message")));
                } else {
                    player.sendMessage(C.chat("&cUsage: /night"));
                }
            } else {
            sender.sendMessage(C.chat("&cPlayer Command Only"));
        }
        return true;
    }
}
