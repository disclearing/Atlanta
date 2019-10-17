package xyz.invisraidinq.atlanta.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import xyz.invisraidinq.atlanta.Atlanta;

public class PlayerEvents implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.getPlayer().isOp()) {
			event.setCancelled(Atlanta.config.getBoolean("disable_block_placing"));
		}
	}

	@EventHandler
	public void bucketFill(PlayerBucketEmptyEvent event) {
		if (!event.getPlayer().isOp()) {
			event.setCancelled(Atlanta.config.getBoolean("disable_bucket_fill"));
		}
	}

	@EventHandler
	public void bucketEmpty(PlayerBucketFillEvent event) {
		event.setCancelled(Atlanta.config.getBoolean("disable_bucket_empty"));
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		e.setCancelled(Atlanta.config.getBoolean("disable_item_dropping"));
	}

	@EventHandler
	public void onPlayerPickItem(PlayerPickupItemEvent e) {
		e.setCancelled(Atlanta.config.getBoolean("disable_item_pickups"));
		e.getItem().remove();
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setCancelled(Atlanta.config.getBoolean("disable_hunger"));
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		e.setCancelled(Atlanta.config.getBoolean("disable_entity_damage"));
	}

}