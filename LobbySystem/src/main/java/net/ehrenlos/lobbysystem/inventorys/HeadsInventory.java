package net.ehrenlos.lobbysystem.inventorys;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.ItemManager;
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

public class HeadsInventory implements Listener {

    private static final Inventory headsInventory = Bukkit.createInventory(null, 36, "§7§lKöpfe-Menü");

    public static Inventory getHeadsInventory() {
        return headsInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§e● §6Köpfe")) {
            player.openInventory(headsInventory);
            event.setCancelled(true);
        }

        headsInventory.setItem(0, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner(player.getName()).setDisplayName("§a" + player.getName() + "'s Kopf").setLore("§8● §7Der hässlichste Kopf von allen", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(1, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("rewinside").setDisplayName("§arewinside's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(2, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("earliboy").setDisplayName("§aearliboy's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(3, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("Paluten").setDisplayName("§aPaluten's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(4, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("gamingguidesde").setDisplayName("§agamingguidesde's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(5, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("AviveHD").setDisplayName("§aAviveHD's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(6, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("TZimon").setDisplayName("§aTZimon's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(7, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("Niggo").setDisplayName("§aNiggo's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(8, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("Howaner").setDisplayName("§aHowaner's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(9, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("Clym").setDisplayName("§aClym's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(10, new ItemManager(Material.PLAYER_HEAD, (short) 3).setSkullOwner("ShortByte").setDisplayName("§aShortByte's Kopf").setLore("§8● §7Test Lore", "§8● §cPreis§8: §6Coming Soon").build());
        headsInventory.setItem(35, new ItemManager(Material.LAVA_BUCKET).setDisplayName("§2Kopf entfernen").build());

        for (int i = 0; i < headsInventory.getSize(); i++) {
            if (headsInventory.getItem(i) == null) {
                headsInventory.setItem(i, new ItemManager(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), headsInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a" + player.getName() + "'s Kopf")) {
                player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6dir §2aufgesetzt");
                player.getInventory().remove(event.getCurrentItem());
                player.getInventory().setHelmet(event.getCurrentItem());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                player.closeInventory();
            }

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§arewinside's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6rewinside §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aearliboy's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6earliboy §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aPaluten's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6Paluten §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§agamingguidesde's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6GamingGuidesDE §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aAviveHD's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6AviveHD §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aTZimon's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6TZimon §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aNiggo's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6Niggo §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aHowaner's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6Howaner §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aClym's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6Clym §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aShortByte's Kopf":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Kopf von §6ShortByte §2aufgesetzt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setHelmet(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§2Kopf entfernen":
                    player.getInventory().setHelmet(null);
                    player.sendMessage(LobbySystem.getPrefix() + "§aDein Kopf wurde entfernt");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
            }
        }
    }
}
