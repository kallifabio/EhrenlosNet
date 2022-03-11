package net.ehrenlos.lobbysystem.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    public static final int DEFAULT_COOLDOWN = 86400;
    private final Map<Player, Integer> cooldowns = new HashMap<>();

    public void setCooldown(Player player, int time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public int getCooldown(Player player) {
        return cooldowns.getOrDefault(player, 0);
    }
}
