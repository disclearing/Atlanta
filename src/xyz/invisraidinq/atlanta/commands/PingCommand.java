package xyz.invisraidinq.atlanta.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import xyz.invisraidinq.atlanta.utils.C;

public class PingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ping")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(C.chat("&cYou're not a player"));
			}
			Player player = (Player) sender;
			int ping = ((CraftPlayer) player).getHandle().ping;
			sender.sendMessage(C.chat("&7Your ping is &5" + ping));
		}
		return false;
	}
}