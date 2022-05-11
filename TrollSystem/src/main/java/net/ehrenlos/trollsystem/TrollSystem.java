package net.ehrenlos.trollsystem;

import net.ehrenlos.trollsystem.commands.trollCommand;
import net.ehrenlos.trollsystem.listeners.PlayerListener;
import net.ehrenlos.trollsystem.utils.RandomEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TrollSystem extends JavaPlugin {

    private static final String prefix = "§9Troll §8| §r";
    private static final String noPermission = "§4Du hast dazu keine Rechte";
    private static TrollSystem instance;
    private static int timer;
    public final ArrayList<Player> freezePlayer = new ArrayList<>();
    public final ArrayList<Player> tntPlayer = new ArrayList<>();
    public final ArrayList<Player> rotatePlayer = new ArrayList<>();
    public final ArrayList<Player> randomTPPlayer = new ArrayList<>();
    public final ArrayList<Player> effectPlayer = new ArrayList<>();
    public final HashMap<String, String> trolling = new HashMap<>();

    public static TrollSystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static int getTimer() {
        return timer;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin wurde aktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§cDas Plugin wurde deaktiviert");
    }

    private void registerCommands() {
        getCommand("troll").setExecutor(new trollCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(), this);
    }

    public void spawnTNTPlayerSpur(Player player) {
        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (tntPlayer.contains(player)) {
                TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
                tnt.setCustomName("Die Zerstörung");
            }
        }, 0L, 2L);
    }

    public void rotatePlayer(Player player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (rotatePlayer.contains(player))
                player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw() + 1.0F, player.getLocation().getPitch()));
        }, 0L, 2L);
    }

    public void randomTP(Player player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (randomTPPlayer.contains(player)) {
                Random random = new Random();
                int x = random.nextInt(2);
                int y = random.nextInt(2);
                int z = random.nextInt(2);
                player.teleport(new Location(player.getWorld(), player.getLocation().getX() + x, player.getLocation().getY() + y, player.getLocation().getZ() + z));
            }
        }, 0L, 5L);
    }

    public void createBox(Player player, Player target) {
        for (int x = (int) player.getLocation().getX(); x < player.getLocation().getX() + 2.0D; x++) {
            for (int y = (int) player.getLocation().getY(); y < player.getLocation().getBlockY() + 3; y++) {
                player.getLocation().getWorld().getBlockAt(new Location(player.getWorld(), x, y, player.getLocation().getZ())).setType(Material.BEDROCK);
                Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                loc.setX(loc.getX() + 2.0D);
                for (int xy = loc.getBlockZ(); xy < loc.getBlockZ() + 3; xy++) {
                    for (int yx = loc.getBlockY(); yx < loc.getBlockY() + 3; yx++)
                        loc.getWorld().getBlockAt(new Location(loc.getWorld(), loc.getBlockX(), yx, xy)).setType(Material.BEDROCK);
                }
            }
        }
        for (int z = player.getLocation().getBlockZ(); z < player.getLocation().getBlockZ() + 2; z++) {
            for (int y = player.getLocation().getBlockY(); y < player.getLocation().getBlockY() + 3; y++)
                player.getLocation().getWorld().getBlockAt(new Location(player.getWorld(), player.getLocation().getBlockX(), y, z)).setType(Material.BEDROCK);
        }
        Location blockX = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        blockX.setZ(blockX.getZ() + 2.0D);
        int i;
        for (i = blockX.getBlockX(); i < blockX.getBlockX() + 2; i++) {
            for (int y = blockX.getBlockY(); y < blockX.getBlockY() + 3; y++)
                blockX.getWorld().getBlockAt(new Location(blockX.getWorld(), i, y, blockX.getBlockZ())).setType(Material.BEDROCK);
        }
        for (i = player.getLocation().getBlockX(); i < player.getLocation().getBlockX() + 3; i++) {
            for (int j = player.getLocation().getBlockZ(); j < player.getLocation().getBlockZ() + 3; j++)
                player.getLocation().getWorld().getBlockAt(new Location(player.getWorld(), i, player.getLocation().getY() + 2.0D, j)).setType(Material.BEDROCK);
        }
        for (i = player.getLocation().getBlockX(); i < player.getLocation().getBlockX() + 3; i++) {
            for (int j = player.getLocation().getBlockZ(); j < player.getLocation().getBlockZ() + 3; j++)
                player.getLocation().getWorld().getBlockAt(new Location(player.getWorld(), i, player.getLocation().getY() - 1.0D, j)).setType(Material.BEDROCK);
        }
        player.getLocation().getWorld().getBlockAt(new Location(player.getWorld(), (player.getLocation().getBlockX() + 1), player.getLocation().getBlockY(), (player.getLocation().getBlockZ() + 1))).setType(Material.COBWEB);
        target.teleport(new Location(player.getWorld(), (player.getLocation().getBlockX() + 1), player.getLocation().getBlockY(), (player.getLocation().getBlockZ() + 1)));
        player.teleport(new Location(player.getWorld(), (player.getLocation().getBlockX() + 1), (player.getLocation().getBlockY() + 3), (player.getLocation().getBlockZ() + 1)));
    }

    public void createEffect(Player player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (effectPlayer.contains(player)) {
                new RandomEffect(player);
            } else {
                Bukkit.getScheduler().cancelTasks(instance);
            }
        }, 60L, 60L);
    }
}
