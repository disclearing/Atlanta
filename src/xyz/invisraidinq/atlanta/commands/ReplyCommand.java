package xyz.invisraidinq.atlanta.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.invisraidinq.atlanta.events.MessageEvent;
import xyz.invisraidinq.atlanta.utils.C;



public class ReplyCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (args.length == 0) {
            sender.sendMessage(C.chat("&cUsage: /reply <message>"));
        }
        else if (MessageCommand.reply.get(sender) == null) {
            sender.sendMessage(C.chat("&cYou have nobody to reply to"));
        }
        else if (MessageCommand.reply.get(sender).getName() == sender.getName()) {
            sender.sendMessage(C.chat("&cYou cannot message yourself"));
        }
        else if (args[0].equalsIgnoreCase("who")) {
            sender.sendMessage(MessageEvent.format(C.chat("&7You are currently in a conversation with &5" + MessageCommand.reply.get(sender).getName())));
        }
        else {
            if (!MessageCommand.toggle.contains(MessageCommand.reply.get(sender).getName()) || sender.hasPermission("Atlanta.togglepm.bypass")) {
                final String msgto = MessageEvent.format(C.chat("&7(&fTo &5" + MessageCommand.reply.get(sender).getName() + "&7)&7 " + MessageEvent.toString(args, 0)));
                sender.sendMessage(msgto);
                final String msgget = MessageEvent.format(C.chat("&7(&fFrom &5" + sender.getName() + "&7)&7 " + MessageEvent.toString(args, 0)));
                MessageCommand.reply.get(sender).sendMessage(MessageEvent.format(msgget));
                return true;
            }
            sender.sendMessage(MessageEvent.format(C.chat("&5" + MessageCommand.reply.get(sender).getName() + " &7has their messages toggled")));
        }
        return false;
    }
}
