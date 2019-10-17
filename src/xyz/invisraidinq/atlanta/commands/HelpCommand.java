package xyz.invisraidinq.atlanta.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.utils.C;

public class HelpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lebel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("help")) {
	        for (final String msg : Atlanta.config.getStringList("help_message")) {
	            sender.sendMessage(C.chat(msg));
	        }
		}
		return false;
	}
}
