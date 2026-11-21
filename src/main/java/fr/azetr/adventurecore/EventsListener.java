package fr.azetr.adventurecore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(player.getName());
    }

    @EventHandler
    public void onPlayerMine(BlockBreakEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(player.getName());

    }

    @EventHandler
    public void onPlayerPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        player.sendMessage(player.getName());

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Player killer = event.getEntity().getKiller();

        if (killer == null) return;

        killer.sendMessage(killer.getName());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTitle().equals("Mon Menu"))) return;

        event.setCancelled(true);

    }

}
