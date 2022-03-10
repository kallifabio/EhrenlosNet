package net.ehrenlos.lobbysystem.listeners;

import net.ehrenlos.lobbysystem.manager.ItemManager;
import net.ehrenlos.lobbysystem.manager.LocationManager;
import net.ehrenlos.lobbysystem.manager.MySQLManager;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PlayerListener implements Listener {

    static PreparedStatement statement;
    static ResultSet result;
    Player player;
    //</editor-fold>

    /**
     * Register Player, Amount
     */

    //<editor-fold defaultstate="collapsed" desc="register">
    public static void register(Player player) {
        try {
            statement = MySQLManager.getStatement("INSERT INTO Coins (Player, Amount) VALUES (?, ?)");
            if (statement != null) {
                statement.setString(1, player.getName());
                statement.setInt(2, 0);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    /**
     * Is Player registered
     */

    //<editor-fold defaultstate="collapsed" desc="isRegistered">
    public static boolean isRegistered(String name) {
        try {
            statement = MySQLManager.getStatement("SELECT * FROM Coins WHERE Player = ?");
            if (statement != null) {
                statement.setString(1, name);
                result = statement.executeQuery();
            }
            boolean user = result.next();
            result.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //</editor-fold>

    /**
     * Get Coins from Player
     */

    //<editor-fold defaultstate="collapsed" desc="getCoins">
    public static int getCoins(String name) {
        try {
            statement = MySQLManager.getStatement("SELECT * FROM Coins WHERE Player = ?");
            if (statement != null) {
                statement.setString(1, name);
                result = statement.executeQuery();
            }
            result.next();
            int coins = result.getInt("Amount");
            result.close();
            return coins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    //</editor-fold>

    /**
     * Set Coins from Player
     */

    //<editor-fold defaultstate="collapsed" desc="setCoins">
    public static void setCoins(String name, int amount) {
        if (isRegistered(name)) {
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, amount);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Player player = Bukkit.getPlayer(name);
            register(player);
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, amount);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();
        event.setJoinMessage(null);

        player.teleport(LocationManager.getLocation("Spawn"));

        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemManager(Material.COMPASS).setDisplayName("§6Navigator §8| §eRechtsklick").build());
        player.getInventory().setItem(2, new ItemManager(Material.BARRIER).setDisplayName("§cDu hast derzeit kein Gadget ausgewählt").build());
        player.getInventory().setItem(4, new ItemManager(Material.CHEST).setDisplayName("§6Gadget §8| §eRechtsklick").build());
        player.getInventory().setItem(6, new ItemManager(Material.BLAZE_ROD).setDisplayName("§6PlayerHider §8| §eRechtsklick").build());
        player.getInventory().setItem(8, new ItemManager(Material.LEGACY_WATCH).setDisplayName("§6LobbySwitcher §8| §eRechtsklick").build());

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.setPlayerWeather(WeatherType.CLEAR);

        player.sendTitle("§7Willkommen auf §6ehrenlos.net§e", player.getName(), 25, 30, 25);

        Bukkit.getWorlds().forEach(current -> {
            current.setPVP(false);
            current.setDifficulty(Difficulty.PEACEFUL);

            if (current.isThundering()) {
                current.setThundering(false);
            }
        });

        if (!isRegistered(player.getName())) {
            setCoins(player.getName(), 0);
        }

        Firework fwrl = (Firework) LocationManager.getLocation("Spawn").getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwrlmeta = fwrl.getFireworkMeta();
        fwrlmeta.addEffects(FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build());
        fwrlmeta.setPower(1);
        fwrl.setFireworkMeta(fwrlmeta);

        Firework fwgb = (Firework) LocationManager.getLocation("Spawn").getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwgbmeta = fwgb.getFireworkMeta();
        fwgbmeta.addEffects(FireworkEffect.builder().withColor(Color.GREEN).with(FireworkEffect.Type.BURST).build());
        fwgbmeta.setPower(1);
        fwgb.setFireworkMeta(fwgbmeta);

        addScoreBoard(player);
    }

    private void addScoreBoard(Player player) {
        int coins = getCoins(player.getName());

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
        } else if (player.hasPermission("lobbysystem.rang.developer")) {
            objective.getScore("§8» §bDeveloper").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.moderator")) {
            objective.getScore("§8» §cModerator").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.builder")) {
            objective.getScore("§8» §2Builder").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.supporter")) {
            objective.getScore("§8» §9Supporter").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.youtuber")) {
            objective.getScore("§8» §5YouTuber").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.jryoutuber")) {
            objective.getScore("§8» §dJrYouTuber").setScore(5);
        } else {
            objective.getScore("§8» §8Spieler").setScore(5);
        }

        objective.getScore("§b").setScore(4);
        objective.getScore("§eDeine Coins").setScore(3);
        objective.getScore("§8» §a" + coins).setScore(2);
        objective.getScore("§f").setScore(1);
        objective.getScore("§eDeine Spielzeit").setScore(0);
        objective.getScore("§8» §cIn Arbeit").setScore(-1);
        objective.getScore("§1").setScore(-2);
        objective.getScore("§eTeamspeak§7/§eWebsite").setScore(-3);
        objective.getScore("§8» §aehrenlos.net").setScore(-4);

        for (Player all : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.setKeepInventory(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Objects.requireNonNull(player.getInventory().getBoots()).getType() == Material.LEATHER_BOOTS) {
            if (Objects.requireNonNull(player.getInventory().getBoots().getItemMeta()).getDisplayName().equalsIgnoreCase("§aHerzen")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.HEART, player.getLocation(), 10);
                }
            } else if (player.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§aFeuer")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.FLAME, player.getLocation(), 10);
                }
            } else if (player.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§aWasser")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.WATER_SPLASH, player.getLocation(), 10);
                }
            } else if (player.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§aRauch")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.SMOKE_NORMAL, player.getLocation(), 10);
                }
            } else if (player.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§aZauber")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.SPELL_WITCH, player.getLocation(), 10);
                }
            } else if (player.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§aWolken")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.spawnParticle(Particle.CLOUD, player.getLocation(), 10);
                }
            } else {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }
}
