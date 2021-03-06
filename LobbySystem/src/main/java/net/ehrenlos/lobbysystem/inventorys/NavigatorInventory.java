package net.ehrenlos.lobbysystem.inventorys;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.LocationManager;
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

        navigatorInventory.setItem(22, new ItemBuilder(Material.GOLD_BLOCK).setDisplayName("§e● §6Spawn").setLore("§7Teleportiere dich zum Spawn").build());
        navigatorInventory.setItem(8, new ItemBuilder(Material.PLAYER_HEAD, (short) 3).setSkullOwner(player.getName()).setDisplayName("§e● §6Team").setLore("§7Teleportiere dich zum Team").build());
        navigatorInventory.setItem(4, new ItemBuilder(Material.GREEN_STAINED_GLASS).setDisplayName("§e● §6Jump And Runs").setLore("§7Teleportiere dich zu den Jump And Runs").build());
        navigatorInventory.setItem(0, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§e● §6Bauserver").setLore("§7Teleportiere dich zum Bauserver").build());
        navigatorInventory.setItem(20, new ItemBuilder(Material.OAK_WOOD).setDisplayName("§e● §6FreeBuild").setLore("§7Teleportiere dich zu FreeBuild").build());
        navigatorInventory.setItem(24, new ItemBuilder(Material.QUARTZ_BLOCK).setDisplayName("§e● §6CityBuild").setLore("§7Teleportiere dich zu CityBuild").build());
        navigatorInventory.setItem(36, new ItemBuilder(Material.COBBLESTONE).setDisplayName("§e● §6Daily Jump And Run 1").setLore("§7Teleportiere dich zum Daily Jump And Run 1").build());

        for (int i = 0; i < navigatorInventory.getSize(); i++) {
            if (navigatorInventory.getItem(i) == null) {
                navigatorInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
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
                case "§e● §6FreeBuild":
                    player.teleport(LocationManager.getLocation("FreeBuild"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.sendTitle("§d§lTeleport", "§8» §aFreeBuild", 25, 25, 25);
                    player.closeInventory();
                    break;
                case "§e● §6CityBuild":
                    player.teleport(LocationManager.getLocation("CityBuild"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.sendTitle("§d§lTeleport", "§8» §aCityBuild", 25, 25, 25);
                    player.closeInventory();
                    break;
                case "§e● §6Daily Jump And Run 1":
                    player.teleport(LocationManager.getLocation("DailyJumpAndRun1"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.sendTitle("§d§lTeleport", "§8» §aDaily Jump And Run 1", 25, 25, 25);
                    player.closeInventory();
                    break;
            }
        }
    }
}
