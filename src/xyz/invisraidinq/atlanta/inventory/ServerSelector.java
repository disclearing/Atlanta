package xyz.invisraidinq.atlanta.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import xyz.invisraidinq.atlanta.Atlanta;
import xyz.invisraidinq.atlanta.listeners.ItemStackBuilder;
import xyz.invisraidinq.atlanta.utils.C;

public class ServerSelector implements Listener {
	
	//Selector Compass
    static List<String> serverSelectorLore = new ArrayList<>();
    static {
    serverSelectorLore.add(C.chat(Atlanta.config.getString("server_selector_lore")));
    }
    public static ItemStack selector = ItemStackBuilder.get(Material.COMPASS, 1, (short)0, Atlanta.config.getString("server_selector_name"), serverSelectorLore);
    public static Inventory inv = Bukkit.createInventory(null, Atlanta.config.getInt("selector_inv_size"), C.chat(Atlanta.config.getString("selector_inv_title")));
    
    static List<String> triosLore = new ArrayList<>();
    static List<String> kitsLore = new ArrayList<>();
    static List<String> infoLore = new ArrayList<>();
    static List<String> buildLore = new ArrayList<>();
    
    static {
        
        for (final String squadslore : Atlanta.config.getStringList("trios_info")) {
            triosLore.add(C.chat(squadslore));
        }
        
        for (final String infolore : Atlanta.config.getStringList("server_info")) {
            infoLore.add(C.chat(infolore));
        }
        
        for (final String kitslore : Atlanta.config.getStringList("kits_info")) {
            kitsLore.add(C.chat(kitslore));
        }
        
        for (final String buildlore : Atlanta.config.getStringList("build_info")) {
        	buildLore.add(C.chat(buildlore));
        }

    }

    public static ItemStack factions = ItemStackBuilder.get(Material.FISHING_ROD, 1, (short)0, Atlanta.config.getString("trios_item_title"), triosLore);
    public static ItemStack kitmap = ItemStackBuilder.get(Material.DIAMOND_SWORD, 1, (short)0, Atlanta.config.getString("kits_item_name"), kitsLore);
    public static ItemStack info = ItemStackBuilder.get(Material.NETHER_STAR, 1, (short)0, Atlanta.config.getString("info_item_name"), infoLore);
    public static ItemStack build = ItemStackBuilder.get(Material.WOOD_AXE, 1, (short)0, Atlanta.config.getString("build_item_name"), buildLore);


    public ServerSelector()
    {
        inv.setItem(Atlanta.config.getInt("trios_selector_slot"), factions);
        inv.setItem(Atlanta.config.getInt("info_selector_slot"), info);
        inv.setItem(Atlanta.config.getInt("kits_selector_slot"), kitmap);
        inv.setItem(Atlanta.config.getInt("build_selector_slot"), build);
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent e)
    {
        e.setCancelled(true);
        if ((!(e.getWhoClicked() instanceof Player)) || (e.getCurrentItem() == null)) {
            return;
        }
        if (e.getInventory().getType().equals(InventoryType.PLAYER)) {
            e.setCancelled(false);
        }
        Player p = (Player)e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (item.isSimilar(factions))
        {
        	p.sendMessage(C.chat(Atlanta.config.getString("trios_queue_join_message")));
            Bukkit.dispatchCommand(p, Atlanta.config.getString("trios_queue_command"));
            p.closeInventory();
        }
        else if (item.isSimilar(kitmap)) {
        	p.sendMessage(C.chat(Atlanta.config.getString("kits_queue_join_message")));
        	Bukkit.dispatchCommand(p, Atlanta.config.getString("kits_queue_command"));
        	p.closeInventory();
        }
        else if (item.isSimilar(info)) {
        	p.sendMessage(C.chat("&7Well what were you expecting..."));
        	p.playSound(p.getLocation(), Sound.ANVIL_LAND, 2F, 1F);
        }
        else if (item.isSimilar(build)) {
        	p.sendMessage(C.chat(Atlanta.config.getString("build_queue_join_message")));
        	Bukkit.dispatchCommand(p, Atlanta.config.getString("build_queue_command"));
        	p.closeInventory();
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if ((!e.getAction().equals(Action.PHYSICAL)) && (e.getItem() != null) && (e.getItem().isSimilar(selector)))
        {
            Player p = e.getPlayer();
            p.openInventory(inv);
            e.setCancelled(true);
        }
    }
}