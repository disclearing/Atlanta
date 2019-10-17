package xyz.invisraidinq.atlanta.inventory;

import org.bukkit.inventory.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.spigotmc.event.entity.*;
import org.bukkit.event.block.Action;

import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.listeners.ItemStackBuilder;
import xyz.invisraidinq.atlanta.utils.C;

public class EnderButtListener implements Listener
{

    public static ItemStack getEnderButt() {
        final ItemStack enderButt = new ItemStack(Material.ENDER_PEARL, (Atlanta.getPlugin(Atlanta.class)).getConfig().getInt("amount_of_pearls"));
        final ItemMeta enderMeta = enderButt.getItemMeta();
        enderMeta.setDisplayName(C.chat(Atlanta.config.getString("enderbutt_name")));
        enderButt.setItemMeta(enderMeta);
        return enderButt;
    }
    
	@EventHandler
	public void onThrowEnder(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}
		if ((!event.hasItem()) || (!event.getItem().getType().equals(Material.ENDER_PEARL))
				|| ((!action.equals(Action.RIGHT_CLICK_AIR)) && (!action.equals(Action.RIGHT_CLICK_BLOCK)))) {
			return;
		}
		event.setCancelled(true);
		event.setUseItemInHand(Event.Result.DENY);
		event.setUseInteractedBlock(Event.Result.DENY);
		Item ep = player.getWorld().dropItem(player.getLocation().add(0.0D, 0.5D, 0.0D),
				ItemStackBuilder.get(Material.ENDER_PEARL, 1, (short) 0, UUID.randomUUID().toString(), null));
		ep.setPickupDelay(10000);
		ep.setVelocity(player.getLocation().getDirection().normalize().multiply(2.0F));
		ep.setPassenger(player);
		player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
		deleteItemWhenNeeded(ep);
		player.updateInventory();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	Set<Item> epItems = new HashSet();

	public void deleteItemWhenNeeded(final Item item) {
		new BukkitRunnable() {
			public void run() {
				if (item.isDead()) {
					cancel();
				}
				if ((item.getVelocity().getX() == 0.0D) || (item.getVelocity().getY() == 0.0D)
						|| (item.getVelocity().getZ() == 0.0D)) {
					Player p = (Player) item.getPassenger();
					EnderButtListener.this.epItems.remove(item);
					item.remove();
					if (p != null) {
						p.teleport(p.getLocation().add(0.0D, 0.5D, 0.0D));
					}
					cancel();
				}
			}
		}.runTaskTimer(Atlanta.getInstance(), 2L, 1L);
	}
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onProjectileLaunch(final ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            final Player p = (Player)e.getEntity().getShooter();
            if (e.getEntity() instanceof EnderPearl) {
                final Projectile proj = e.getEntity();
                if (proj.getType() == EntityType.ENDER_PEARL) {
                    p.spigot().setCollidesWithEntities(false);
                    proj.setPassenger((Entity)p);
                    p.setItemInHand(getEnderButt());
                    p.updateInventory();
                }
            }
        }
    }
    
    @EventHandler
    public void onEntityDismound(final EntityDismountEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (p != null && p.getVehicle() instanceof EnderPearl) {
                p.spigot().setCollidesWithEntities(true);
                p.eject();
                p.getInventory().setItem(Atlanta.config.getInt("enderbutt_slot"), getEnderButt());
                p.updateInventory();
            }
        }
    }

}
