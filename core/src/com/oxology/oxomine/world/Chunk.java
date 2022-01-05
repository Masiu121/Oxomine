package com.oxology.oxomine.world;

import com.oxology.oxomine.entites.Entity;
import com.oxology.oxomine.world.objects.Block;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
    public static final int CHUNK_SIZE = 16;

    private World world;
    private Location location;

    private List<Entity> entities;
    private List<Block> blocks;

    private int x;
    private int y;

    public Chunk(Location location) {
        this.location = location;
        this.world = this.location.getWorld();

        entities = new ArrayList<>();
        blocks = new ArrayList<>();

        x = (int) this.location.getX()/CHUNK_SIZE;
        y = (int) this.location.getY()/CHUNK_SIZE;
    }

    public Location getLocation() {
        return location;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int x, int y, int z) {
        for(Block block : blocks)
            if(block.getX() == x && block.getY() == y && block.getZ() == z)
                return block;
        return null;
    }

    public Block getBlock(Location location) {
        for(Block block : blocks)
            if(block.getLocation().compare(location))
                return block;
        return null;
    }
}
