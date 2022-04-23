package net.ehrenlos.lobbysystem.inventorys;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class LobbySwitcherInventory implements Listener {

    private static final Inventory lobbyswitcherInventory = Bukkit.createInventory(null, 9, "§7§lLobbySwitcher");

    public static Inventory getLobbyswitcherInventory() {
        return lobbyswitcherInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§6LobbySwitcher §8| §eRechtsklick")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.updateInventory();
                player.openInventory(lobbyswitcherInventory);
                event.setCancelled(true);
            }
        }

        lobbyswitcherInventory.setItem(0, new ItemBuilder(Material.PAPER).setDisplayName("§e● §6Lobby-1").build());
        lobbyswitcherInventory.setItem(1, new ItemBuilder(Material.PAPER).setDisplayName("§e● §6Lobby-2").build());
        lobbyswitcherInventory.setItem(8, new ItemBuilder(Material.GLOWSTONE_DUST).setDisplayName("§e● §6PremiumLobby").build());

        for (int i = 0; i < lobbyswitcherInventory.getSize(); i++) {
            if (lobbyswitcherInventory.getItem(i) == null) {
                lobbyswitcherInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), lobbyswitcherInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§e● §6Lobby-1":
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    try {
                        out.writeUTF("Connect");
                        out.writeUTF("Lobby-1");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendPluginMessage(LobbySystem.getInstance(), "BungeeCord", b.toByteArray());
                    player.sendMessage(LobbySystem.getPrefix() + "§7Du wirst mit Lobby-1 verbunden");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§e● §6Lobby-2":
                    ByteArrayOutputStream b2 = new ByteArrayOutputStream();
                    DataOutputStream out2 = new DataOutputStream(b2);
                    try {
                        out2.writeUTF("Connect");
                        out2.writeUTF("Lobby-2");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendPluginMessage(LobbySystem.getInstance(), "BungeeCord", b2.toByteArray());
                    player.sendMessage(LobbySystem.getPrefix() + "§7Du wirst mit Lobby-2 verbunden");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§e● §6PremiumLobby":
                    if (player.hasPermission("lobbysystem.premiumlobby")) {
                        ByteArrayOutputStream b3 = new ByteArrayOutputStream();
                        DataOutputStream out3 = new DataOutputStream(b3);
                        try {
                            out3.writeUTF("Connect");
                            out3.writeUTF("PremiumLobby-1");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.sendPluginMessage(LobbySystem.getInstance(), "BungeeCord", b3.toByteArray());
                        player.sendMessage(LobbySystem.getPrefix() + "§7Du wirst mit PremiumLobby-1 verbunden");
                    } else {
                        player.sendMessage(LobbySystem.getPrefix() + LobbySystem.getNoPerms());
                    }
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
            }
        }
    }
}
