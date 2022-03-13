package net.ehrenlos.hologram.manager;

import net.ehrenlos.hologram.utils.FileBuilder;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public static File hologram = new File("plugins/HologramSystem", "holograms.yml");
    public static FileConfiguration hologramData = YamlConfiguration.loadConfiguration(hologram);
    private static FileBuilder builder;

    public static FileConfiguration getHologramData() {
        return hologramData;
    }

    public static File getHologram() {
        return hologram;
    }

    public static FileBuilder getBuilder() {
        return builder;
    }

    public void saveHologramData() {
        try {
            hologramData.save(hologram);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(ChatColor.DARK_RED + "§lERROR");
        }
    }
}
