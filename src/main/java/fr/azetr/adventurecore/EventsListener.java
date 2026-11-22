package fr.azetr.adventurecore;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class EventsListener implements Listener {

    private final QuestManager questManager;

    public EventsListener(QuestManager questManager) {
        this.questManager = questManager;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!InvManager.HashmapcontainKey(player)) InvManager.AddPlayerLevels(player);

        player.sendMessage(player.getName());
    }

    @EventHandler
    public void onPlayerMine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material brokenBlock = event.getBlock().getType();

        for (String questId : questManager.getQuestIds()) {

            if (questManager.getQuestMaterial(questId) == brokenBlock) {
                questManager.progressQuest(player, questId);
            }
        }

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
        if (!event.getView().getTitle().equals("Quests")) return;

        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (event.getRawSlot() >= event.getView().getTopInventory().getSize()) return;

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        String itemName = clickedItem.getItemMeta().getDisplayName();

        for (String questId : questManager.getQuestIds()) {

            if (questManager.getQuestName(questId).equals(itemName)) {
                questManager.startQuest(player, questId);
                return;
            }
        }
    }
}
