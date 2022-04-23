package net.ehrenlos.lobbysystem.inventorys;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class ExtrasInventory implements Listener {

    private static final Inventory extrasInventory = Bukkit.createInventory(null, 36, "§7§lExtras-Menü");

    public static Inventory getExtrasInventory() {
        return extrasInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§e● §6Extras")) {
            player.openInventory(extrasInventory);
            event.setCancelled(true);
        }

        extrasInventory.setItem(0, new ItemBuilder(Material.ENDER_PEARL).setDisplayName("§aEnderperle").setLore("§8● §cPreis§8: §6Coming Soon").build());
        extrasInventory.setItem(35, new ItemBuilder(Material.LAVA_BUCKET).setDisplayName("§2Extras entfernen").build());

        for (int i = 0; i < extrasInventory.getSize(); i++) {
            if (extrasInventory.getItem(i) == null) {
                extrasInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), extrasInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§aEnderperle":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast das Extra §6Enderperle §2ausgewählt");
                    player.getInventory().setItem(2, new ItemBuilder(Material.ENDER_PEARL).setDisplayName("§aEnderperle").build());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§2Extras entfernen":
                    player.getInventory().setItem(2, new ItemBuilder(Material.BARRIER).setDisplayName("§cDu hast derzeit kein Gadget ausgewählt").build());
                    player.sendMessage(LobbySystem.getPrefix() + "§aDein Extra wurde entfernt");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
            }
        }
    }
}
