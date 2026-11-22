package fr.azetr.adventurecore;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class InvManager {

    private static HashMap<UUID, Integer> Levels = new HashMap<>();

    public static Inventory MainInv() {
        Inventory inv = Bukkit.createInventory(null, 27, Component.text("Mon Menu"));

        inv.setItem(8, Items.Levels());
        inv.setItem(13, Items.Quests());

        return inv;
    }

    public static Inventory QuestsInv() {
        Inventory inv = Bukkit.createInventory(null, 27, Component.text("Quests"));
        int slotNumber = -1;

        return inv;
    }

}
