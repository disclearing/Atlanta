package xyz.invisraidinq.atlanta.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import xyz.invisraidinq.atlanta.Atlanta;

public class MobSpawnEvent implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		event.setCancelled(Atlanta.config.getBoolean("disable_mob_spawning"));
	}

}
