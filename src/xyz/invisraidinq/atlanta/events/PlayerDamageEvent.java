package xyz.invisraidinq.atlanta.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import xyz.invisraidinq.atlanta.Atlanta;

public class PlayerDamageEvent implements Listener {
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if ((event.getEntity() instanceof Player)) {
			event.setCancelled(Atlanta.config.getBoolean("disable_player_damage"));
		}
	}

}
