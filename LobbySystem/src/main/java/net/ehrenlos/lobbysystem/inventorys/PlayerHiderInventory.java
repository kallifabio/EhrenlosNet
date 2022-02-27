package net.ehrenlos.lobbysystem.inventorys;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.ItemManager;
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

import java.util.Objects;

public class PlayerHiderInventory implements Listener {

    private static final Inventory playerhiderInventory = Bukkit.createInventory(null, 9, "§7§lPlayerHider");

    public static Inventory getPlayerhiderInventory() {
        return playerhiderInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§6PlayerHider §8| §eRechtsklick")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.updateInventory();
                player.openInventory(playerhiderInventory);
                event.setCancelled(true);
            }
        }

        playerhiderInventory.setItem(1, new ItemManager(Material.GREEN_DYE).setDisplayName("§e● §2Spieler anzeigen").build());
        playerhiderInventory.setItem(4, new ItemManager(Material.PURPLE_DYE).setDisplayName("§e● §dNur VIPs anzeigen").build());
        playerhiderInventory.setItem(7, new ItemManager(Material.RED_DYE).setDisplayName("§e● §cSpieler verstecken").build());

        for (int i = 0; i < playerhiderInventory.getSize(); i++) {
            if (playerhiderInventory.getItem(i) == null) {
                playerhiderInventory.setItem(i, new ItemManager(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), playerhiderInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§e● §2Spieler anzeigen":
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(LobbySystem.getInstance(), all);
                    }
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast alle Spieler angezeigt");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§e● §cSpieler verstecken":
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(LobbySystem.getInstance(), all);
                    }
                    player.sendMessage(LobbySystem.getPrefix() + "§4Du hast alle Spieler versteckt");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§e● §dNur VIPs anzeigen":
                    if (player.hasPermission("lobbysystem.vip")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            player.hidePlayer(LobbySystem.getInstance(), all);
                        }
                    }
                    player.sendMessage(LobbySystem.getPrefix() + "§dDir werden nur VIPs angezeigt");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
            }
        }
    }
}
