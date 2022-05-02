package dev.kutuptilkisi.antigrief.instance;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Repairer extends BukkitRunnable {

    private final List<BlockState> blocks;

    public Repairer(List<Block> blocks){
        this.blocks = new ArrayList<>();
        for(Block block : blocks){
            this.blocks.add(block.getState());
        }
    }

    @Override
    public void run() {
        int max = blocks.size()-1;
        if(max > -1){
            if(!this.blocks.get(max).getType().equals(Material.TNT)){
                this.blocks.get(max).update(true, false);
            }
            this.blocks.remove(max);
        } else {
            cancel();
        }
    }
}
