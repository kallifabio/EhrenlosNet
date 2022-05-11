package net.ehrenlos.trollsystem.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomEffect {

    public RandomEffect(Player player) {
        final Random random = new Random();
        switch (random.nextInt(9)) {
            case 0: {
                this.addPotionEffect(player, PotionEffectType.ABSORPTION);
                break;
            }
            case 1: {
                this.addPotionEffect(player, PotionEffectType.BLINDNESS);
                break;
            }
            case 2: {
                this.addPotionEffect(player, PotionEffectType.CONFUSION);
                break;
            }
            case 3: {
                this.addPotionEffect(player, PotionEffectType.WITHER);
                break;
            }
            case 4: {
                this.addPotionEffect(player, PotionEffectType.HUNGER);
                break;
            }
            case 5: {
                this.addPotionEffect(player, PotionEffectType.LEVITATION);
                break;
            }
            case 6: {
                this.addPotionEffect(player, PotionEffectType.SLOW_DIGGING);
                break;
            }
            case 7: {
                this.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE);
                break;
            }
            case 8: {
                this.addPotionEffect(player, PotionEffectType.WEAKNESS);
                break;
            }
            case 9: {
                this.addPotionEffect(player, PotionEffectType.POISON);
                break;
            }
        }
    }

    private void addPotionEffect(Player player, PotionEffectType type) {
        player.addPotionEffect(new PotionEffect(type, 60, 50));
    }
}
