package net.ehrenlos.trollsystem.inventorys;

import net.ehrenlos.trollsystem.TrollSystem;
import net.ehrenlos.trollsystem.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class TrollInventory implements Listener {

    private static final Inventory trollInventory = Bukkit.createInventory(null, 9, "§eTrollMenü");

    public static Inventory getTrollInventory() {
        return trollInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        String name = "";
        Player target = Bukkit.getServer().getPlayer(name);
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().contains("§eTrollMenü §8| §d")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.updateInventory();
                player.openInventory(trollInventory);
                event.setCancelled(true);
            }
        }

        trollInventory.setItem(0, new ItemBuilder(Material.ICE).setDisplayName("§aSpieler einfrieren").build());

        for (int i = 0; i < trollInventory.getSize(); i++) {
            if (trollInventory.getItem(i) == null) {
                trollInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        String name = "";
        Player target = Bukkit.getPlayer(name);
        if (Objects.equals(event.getClickedInventory(), trollInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§aSpieler einfrieren":
                    if (!TrollSystem.getInstance().freeze.contains(target)) {
                        TrollSystem.getInstance().freeze.add(target);
                        player.sendMessage(TrollSystem.getPrefix() + "§2Du hast den Spieler §7" + target.getName() + " §2eingefroren");
                        player.closeInventory();
                    } else {
                        TrollSystem.getInstance().freeze.remove(target);
                        player.sendMessage(TrollSystem.getPrefix() + "§cDu hast den Spieler §7" + target.getName() + " §centfroren");
                        player.closeInventory();
                    }
                    break;
            }
        }
    }
}
