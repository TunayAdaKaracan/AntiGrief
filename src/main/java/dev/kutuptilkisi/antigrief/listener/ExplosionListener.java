package dev.kutuptilkisi.antigrief.listener;

import dev.kutuptilkisi.antigrief.AntiGrief;
import dev.kutuptilkisi.antigrief.instance.Repairer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class ExplosionListener implements Listener {

    private final AntiGrief main;

    private final Vector first;
    private final Vector second;

    public ExplosionListener(AntiGrief main, Vector first, Vector second) {
        this.main = main;

        this.first = first;
        this.second = second;
    }

    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent e){
        Location loc = e.getBlock().getLocation();
        if(loc.getWorld().getName().equalsIgnoreCase("world") && loc.getX() > first.getX() && loc.getX() < second.getX() && loc.getZ() < first.getZ() && loc.getZ() > second.getZ()){
            e.setYield(0);
            new Repairer(e.blockList()).runTaskTimer(main, 0L, 1L);
            for(Block block : e.blockList()){
                if(block.getType().equals(Material.CHEST) ||
                        block.getType().equals(Material.FURNACE) ||
                        block.getType().equals(Material.SMOKER) ||
                        block.getType().equals(Material.BLAST_FURNACE) ||
                        block.getType().equals(Material.ARMOR_STAND) ||
                        block.getType().equals(Material.LANTERN) ||
                        block.getType().equals(Material.TORCH) ||
                        block.getType().equals(Material.REDSTONE_TORCH) ||
                        block.getType().equals(Material.ITEM_FRAME)){
                    block.setType(Material.AIR);
                }
            }
        }
    }

    @EventHandler
    public void onTntExplosion(EntityExplodeEvent e){
        Location loc = e.getLocation();
        if(loc.getWorld().getName().equalsIgnoreCase("world") && loc.getX() > first.getX() && loc.getX() < second.getX() && loc.getZ() < first.getZ() && loc.getZ() > second.getZ()){
            e.setYield(0);
            new Repairer(e.blockList()).runTaskTimer(main, 0L, 1L);
            for(Block block : e.blockList()){
                if(block.getType().equals(Material.CHEST) ||
                block.getType().equals(Material.FURNACE) ||
                block.getType().equals(Material.SMOKER) ||
                block.getType().equals(Material.BLAST_FURNACE) ||
                block.getType().equals(Material.ARMOR_STAND) ||
                block.getType().equals(Material.LANTERN) ||
                block.getType().equals(Material.TORCH) ||
                block.getType().equals(Material.REDSTONE_TORCH) ||
                block.getType().equals(Material.ITEM_FRAME)){
                    block.setType(Material.AIR);
                }
            }
        }
    }

    @EventHandler
    public void onLavaPlace(PlayerBucketEmptyEvent e){
        Location loc = e.getBlock().getLocation();
        if(loc.getWorld().getName().equalsIgnoreCase("world") && loc.getX() > first.getX() && loc.getX() < second.getX() && loc.getZ() < first.getZ() && loc.getZ() > second.getZ()){
            if(e.getBucket().equals(Material.LAVA_BUCKET)){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "Üzgünüm Ama Şehirde Yere Lav Koyamazsın");
            }
        }
    }

}
