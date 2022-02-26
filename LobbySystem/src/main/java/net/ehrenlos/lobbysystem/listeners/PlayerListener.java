package net.ehrenlos.lobbysystem.listeners;

import net.ehrenlos.lobbysystem.manager.ItemManager;
import net.ehrenlos.lobbysystem.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerListener implements Listener {

    Player player;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();
        event.setJoinMessage(null);

        player.teleport(LocationManager.getLocation("Spawn"));

        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemManager(Material.COMPASS).setDisplayName("§6Navigator §8| §eRechtsklick").build());

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.setPlayerWeather(WeatherType.CLEAR);

        addScoreBoard(player);
    }

    private void addScoreBoard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6§lEhrenlosNet");

        objective.getScore("§8§m---------------").setScore(10);
        objective.getScore("§eDein Profil").setScore(9);
        objective.getScore("§8» §a" + player.getName()).setScore(8);
        objective.getScore(" ").setScore(7);
        objective.getScore("§eDein Rang").setScore(6);

        if (player.hasPermission("lobbysystem.rang.admin")) {
            objective.getScore("§8» §4Admin").setScore(5);
        } else {
            objective.getScore("§8» §8Spieler").setScore(5);
        }

        objective.getScore("§b").setScore(4);
        objective.getScore("§eTeamspeak§7/§eWebsite").setScore(3);
        objective.getScore("§8» §aehrenlos.net").setScore(2);

        for (Player all : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        player = (Player) event.getWhoClicked();
        if (player.getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        } else if (player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        } else if (player.getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }
}
