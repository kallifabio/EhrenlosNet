package net.ehrenlos.bungeesystem.utils;

import java.util.ArrayList;
import java.util.List;

public enum TimeUnit {

    SECOND("", 1L, "s"),
    MINUTE("", 60L, "m"),
    HOUR("", 3600L, "h"),
    DAY("", 86400L, "d"),
    WEEK("", 604800L, "w");

    private String name;
    private long toSeconds;
    private String shortcut;

    TimeUnit(String name, long toSeconds, String shortcut) {
        this.name = name;
        this.toSeconds = toSeconds;
        this.shortcut = shortcut;
    }

    public long getToSeconds() {
        return this.toSeconds;
    }

    public String getName() {
        return this.name;
    }

    public String getShortcut() {
        return this.shortcut;
    }

    public static List<String> getUnitsAsString() {
        List<String> units = new ArrayList<>();
        byte b;
        int i;
        TimeUnit[] arrayOfTimeUnit;
        for (i = (arrayOfTimeUnit = values()).length, b = 0; b < i; ) {
            TimeUnit unit = arrayOfTimeUnit[b];
            units.add(unit.getShortcut().toLowerCase());
            b++;
        }
        return units;
    }

    public static TimeUnit getUnit(String unit) {
        byte b;
        int i;
        TimeUnit[] arrayOfTimeUnit;
        for (i = (arrayOfTimeUnit = values()).length, b = 0; b < i; ) {
            TimeUnit units = arrayOfTimeUnit[b];
            if (units.getShortcut().toLowerCase().equals(unit.toLowerCase()))
                return units;
            b++;
        }
        return null;
    }
}
