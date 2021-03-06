package net.ehrenlos.lobbysystem.inventorys;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.utils.ItemBuilder;
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

public class GadgetInventory implements Listener {

    private static final Inventory gadgetInventory = Bukkit.createInventory(null, 27, "§7§lGadget");

    public static Inventory getGadgetInventory() {
        return gadgetInventory;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§6Gadget §8| §eRechtsklick")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                player.sendMessage(LobbySystem.getPrefix() + "§cGadgets werden geladen");
                player.updateInventory();
                player.openInventory(gadgetInventory);
                event.setCancelled(true);
            }
        }

        gadgetInventory.setItem(1, new ItemBuilder(Material.PLAYER_HEAD, (short) 3).setSkullOwner(player.getName()).setDisplayName("§e● §6Köpfe").build());
        gadgetInventory.setItem(4, new ItemBuilder(Material.ENDER_CHEST).setDisplayName("§e● §6Partikel").build());
        gadgetInventory.setItem(7, new ItemBuilder(Material.NETHER_STAR).setDisplayName("§e● §6Extras").build());

        for (int i = 0; i < gadgetInventory.getSize(); i++) {
            if (gadgetInventory.getItem(i) == null) {
                gadgetInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§r").build());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (Objects.equals(event.getClickedInventory(), gadgetInventory)) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§e● §6Köpfe":
                    player.updateInventory();
                    player.openInventory(HeadsInventory.getHeadsInventory());
                    event.setCancelled(true);
                    break;
                case "§e● §6Partikel":
                    player.updateInventory();
                    player.openInventory(ParticleInventory.getParticleInventory());
                    event.setCancelled(true);
                    break;
                case "§e● §6Extras":
                    player.updateInventory();
                    player.openInventory(ExtrasInventory.getExtrasInventory());
                    event.setCancelled(true);
                    break;
            }
        }
    }
}
