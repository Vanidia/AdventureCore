package fr.azetr.adventurecore;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InvManager {

    public static Inventory MainInv() {
        Inventory inv = Bukkit.createInventory(null, 27, Component.text("Mon Menu"));

        inv.setItem(8, Items.Levels());
        inv.setItem(13, Items.Quests());

        return inv;
    }
}
