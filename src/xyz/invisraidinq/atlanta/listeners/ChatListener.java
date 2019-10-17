package xyz.invisraidinq.atlanta.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.utils.C;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
    	if(Atlanta.config.getBoolean("format-chat") == true) {
    		@SuppressWarnings("deprecation")
    		String prefix = PermissionsEx.getUser(event.getPlayer()).getGroups()[0].getPrefix().replace("_", " ").replace("&", "§") + event.getPlayer().getName();
    		String message = event.getMessage().replace("%", "%%");
    		event.setFormat(C.chat(Atlanta.config.getString("format").replace("%playerName%", prefix).replace("%message%", message)));
    	} else {
    		return;
    	}
    }
}