package dev.kutuptilkisi.antigrief;

import dev.kutuptilkisi.antigrief.listener.ExplosionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class AntiGrief extends JavaPlugin {

    private Vector first;
    private Vector second;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        first = new Vector(getConfig().getInt("area.x1"), 0, getConfig().getInt("area.z1"));
        second = new Vector(getConfig().getInt("area.x2"), 0, getConfig().getInt("area.z2"));
        Bukkit.getPluginManager().registerEvents(new ExplosionListener(this, first, second), this);
    }
}
