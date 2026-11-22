package fr.azetr.adventurecore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdventureCommand implements CommandExecutor {
    @Override
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        player.openInventory(InvManager.MainInv(player));

        return false;
    }
}
