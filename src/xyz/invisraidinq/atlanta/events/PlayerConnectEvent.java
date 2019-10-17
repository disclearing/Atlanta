package xyz.invisraidinq.atlanta.events;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.inventory.EnderButtListener;
import xyz.invisraidinq.atlanta.inventory.ServerSelector;
import xyz.invisraidinq.atlanta.utils.C;

public class PlayerConnectEvent implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
        for (final String msg : Atlanta.getPlugin(Atlanta.class).getConfig().getStringList("welcome_message")) {
            p.sendMessage(C.chat(msg));
		p.teleport(p.getWorld().getSpawnLocation());
		p.setAllowFlight(Atlanta.config.getBoolean("allow_hub_flight"));
		if(Atlanta.config.getBoolean("force_survival_on_logon") == true) {
			p.setGameMode(GameMode.SURVIVAL);
			}
        }
	}
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Item item : this.epItems) {
			if (item.getPassenger().equals(p)) {
				item.eject();
			}
		}
		
		
	
	
		p.getInventory().clear();
		p.setWalkSpeed(0.4F);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().setItem(Atlanta.config.getInt("server_selector_inv_slot"), ServerSelector.selector);
        p.getInventory().setItem(Atlanta.config.getInt("enderbutt_slot"), EnderButtListener.getEnderButt());
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	Set<Item> epItems = new HashSet();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}
	
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.getWorld().setWeatherDuration(0);
        e.setCancelled(false);
    }

    @EventHandler
    public void onLoadEvent(PluginEnableEvent e){
        Bukkit.getWorlds().get(0).setTime(6000);
    }
}

