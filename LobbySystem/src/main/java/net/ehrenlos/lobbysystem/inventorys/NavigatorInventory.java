package net.ehrenlos.lobbysystem.inventorys;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.ItemManager;
import net.ehrenlos.lobbysystem.manager.LocationManager;
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
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class NavigatorInventory implements Listener {

    private static final Inventory navigatorInventory = Bukkit.createInventory(null, 45, "§7§lNavigator");

    public static Inventory getNavigatorInventory() {
        return navigatorInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§6Navigator §8| §eRechtsklick")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.updateInventory();
                player.openInventory(navigatorInventory);
                event.setCancelled(true);
            }
        }

        navigatorInventory.setItem(22, new ItemManager(Material.GOLD_BLOCK).setDisplayName("§e● §6Spawn").setLore("§7Teleportiere dich zum Spawn").build());
        navigatorInventory.setItem(8, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner(player.getName()).setDisplayName("§e● §6Team").setLore("§7Teleportiere dich zum Team").build());
        navigatorInventory.setItem(4, new ItemManager(Material.GREEN_STAINED_GLASS).setDisplayName("§e● §6Jump And Runs").setLore("§7Teleportiere dich zu den Jump And Runs").build());
        navigatorInventory.setItem(0, new ItemManager(Material.GRASS_BLOCK).setDisplayName("§e● §6Bauserver").setLore("§7Teleportiere dich zum Bauserver").build());

        for (int i = 0; i < navigatorInventory.getSize(); i++) {
            if (navigatorInventory.getItem(i) == null) {
                navigatorInventory.setItem(i, new ItemManager(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), navigatorInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§e● §6Spawn":
                    player.teleport(LocationManager.getLocation("Spawn"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.sendTitle("§d§lTeleport", "§8» §aSpawn", 25, 25, 25);
                    player.closeInventory();
                    break;
                case "§e● §6Team":
                    player.teleport(LocationManager.getLocation("Team"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.sendTitle("§d§lTeleport", "§8» §aTeam", 25, 25, 25);
                    player.closeInventory();
                    break;
                case "§e● §6Jump And Runs":
                    player.teleport(LocationManager.getLocation("JumpAndRuns"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.sendTitle("§d§lTeleport", "§8» §aJump And Runs", 25, 25, 25);
                    player.closeInventory();
                    break;
                case "§e● §6Bauserver":
                    if (player.hasPermission("lobbysystem.bauserver")) {
                        ByteArrayOutputStream b = new ByteArrayOutputStream();
                        DataOutputStream out = new DataOutputStream(b);
                        try {
                            out.writeUTF("Connect");
                            out.writeUTF("Bauserver-1");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.sendPluginMessage(LobbySystem.getInstance(), "BungeeCord", b.toByteArray());
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
