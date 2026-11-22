package fr.azetr.adventurecore;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InvManager {

    private static HashMap<UUID, Integer> Levels = new HashMap<>();

    public static Boolean HashmapcontainKey(Player player) {
        return Levels.containsKey(player.getUniqueId());
    }

    public static void AddPlayerLevels(Player player) {
        Levels.put(player.getUniqueId(), 0);
    }

    public static Inventory MainInv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, Component.text("Mon Menu"));

        inv.setItem(8, Items.Levels(Levels.get(player.getUniqueId())));
        inv.setItem(13, Items.Quests());

        return inv;
    }

    public static Inventory QuestsInv(Player player, QuestManager questManager) {

        Inventory inv = Bukkit.createInventory(null, 27, Component.text("Quests"));

        for (String questId : questManager.getQuestIds()) {

            ItemStack item = new ItemStack(questManager.getQuestMaterial(questId));
            ItemMeta meta = item.getItemMeta();

            int progress = questManager.getQuestProgress(player, questId);
            int objective = questManager.getQuestObjective(questId);

            boolean completed = questManager.isQuestCompleted(player, questId);
            boolean started = questManager.hasStartedQuest(player, questId);

            String status;

            if (completed) {
                status = "§aTerminée";
            } else if (started) {
                status = "§eEn cours";
            } else {
                status = "§cNon commencée";
            }

            meta.setDisplayName(questManager.getQuestName(questId));

            meta.setLore(List.of(
                    questManager.getQuestDescription(questId),
                    "§7Progression : §f" + progress + "/" + objective,
                    "§7Statut : " + status,
                    "§eClique pour commencer !"));

            item.setItemMeta(meta);
            inv.addItem(item);
        }

        return inv;
    }



}
