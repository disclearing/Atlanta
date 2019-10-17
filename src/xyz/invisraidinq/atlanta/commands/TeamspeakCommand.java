package xyz.invisraidinq.atlanta.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.utils.C;

public class TeamspeakCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("teamspeak")) {
			sender.sendMessage(C.chat(Atlanta.config.getString("teamspeak_ip")));
		}
		return false;
	}

}
