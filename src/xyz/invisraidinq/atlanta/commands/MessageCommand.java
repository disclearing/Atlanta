package xyz.invisraidinq.atlanta.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.invisraidinq.atlanta.events.MessageEvent;
import xyz.invisraidinq.atlanta.utils.C;

public class MessageCommand implements CommandExecutor
{
	
public static List<String> toggle = new ArrayList<String>();
public static HashMap<CommandSender, Player> reply = new HashMap<CommandSender, Player>();
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  if (sender instanceof Player)
  {
    if (args.length <= 1)
    {
      sender.sendMessage(C.chat("&cUsage: /msg <player> <message>"));
    }
    else if (Bukkit.getPlayer(args[0]) == null)
    {
      sender.sendMessage(C.chat("&cCould not find that player"));
    }
    else if (Bukkit.getPlayer(args[0]).getName() == sender.getName())
    {
      sender.sendMessage(C.chat("&cYou cannot message yourself"));
    }
    else if ((toggle.contains(Bukkit.getPlayer(args[0]).getDisplayName())) && (!sender.hasPermission("base.togglepm.bypass")))
    {
      sender.sendMessage(MessageEvent.format("&a" + Bukkit.getPlayer(args[0]).getName() + "&e has their messages toghled"));
    }
    else
    {
      String msgto = MessageEvent.format("&7(&fTo &5" + Bukkit.getPlayer(args[0]).getName() + "&7)&7 " + MessageEvent.toString(args, 1));
      sender.sendMessage(msgto);
      
      String msgrecieve = MessageEvent.format("&7(&fFrom &5" + sender.getName() + "&7) &7" + MessageEvent.toString(args, 1));
      Bukkit.getPlayer(args[0]).sendMessage(msgrecieve);
      reply.put(sender, Bukkit.getPlayer(args[0]));
      reply.put(Bukkit.getPlayer(args[0]), (Player)sender);
      return true;
    }
  }
  return false;
	}
}