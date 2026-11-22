package fr.azetr.adventurecore;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Items {

    public static ItemStack Quests() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Quests");

        meta.setLore(List.of(
                    "Permet d'accéder "
                        +"aux Quests"));

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack Levels(int level) {
        ItemStack item =  new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Levels");
        meta.setLore(List.of(
                "Level: " + level));

        item.setItemMeta(meta);

        return item;
    }


}
