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

public class ParticleInventory implements Listener {

    private static final Inventory particleInventory = Bukkit.createInventory(null, 36, "§7§lPartikel-Menü");

    public static Inventory getParticleInventory() {
        return particleInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§e● §6Partikel")) {
            player.openInventory(particleInventory);
            event.setCancelled(true);
        }

        particleInventory.setItem(0, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aHerzen").setLore("§8● §cPreis§8: §6Coming Soon").build());
        particleInventory.setItem(1, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aFeuer").setLore("§8● §cPreis§8: §6Coming Soon").build());
        particleInventory.setItem(2, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aWasser").setLore("§8● §cPreis§8: §6Coming Soon").build());
        particleInventory.setItem(3, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aRauch").setLore("§8● §cPreis§8: §6Coming Soon").build());
        particleInventory.setItem(4, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aZauber").setLore("§8● §cPreis§8: §6Coming Soon").build());
        particleInventory.setItem(5, new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§aWolken").setLore("§8● §cPreis§8: §6Coming Soon").build());
        particleInventory.setItem(35, new ItemBuilder(Material.LAVA_BUCKET).setDisplayName("§2Partikel entfernen").build());

        for (int i = 0; i < particleInventory.getSize(); i++) {
            if (particleInventory.getItem(i) == null) {
                particleInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), particleInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§aHerzen":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast die Partikel §6Herzen §2ausgewählt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setBoots(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aFeuer":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast die Partikel §6Feuer §2ausgewählt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setBoots(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aWasser":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast die Partikel §6Wasser §2ausgewählt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setBoots(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aRauch":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast die Partikel §6Rauch §2ausgewählt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setBoots(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aZauber":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast die Partikel §6Zauber §2ausgewählt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setBoots(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§aWolken":
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast die Partikel §6Wolken §2ausgewählt");
                    player.getInventory().remove(event.getCurrentItem());
                    player.getInventory().setBoots(event.getCurrentItem());
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
                case "§2Partikel entfernen":
                    player.getInventory().setBoots(null);
                    player.sendMessage(LobbySystem.getPrefix() + "§aDeine Partikel wurde entfernt");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                    player.closeInventory();
                    break;
            }
        }
    }
}
