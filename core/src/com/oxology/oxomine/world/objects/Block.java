package com.oxology.oxomine.world.objects;

import com.oxology.oxomine.world.Location;
import com.oxology.oxomine.world.World;

public class Block {
    World world;
    Location location;

    int x;
    int y;
    int z;

    Material material;

    public Block(Location location) {
        this.location = location;
        this.world = this.location.getWorld();

        this.x = (int) location.getX();
        this.y = (int) location.getY();
        this.z = (int) location.getZ();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Location getLocation() {
        return location;
    }

    public void setType(Material material) {
        this.material = material;
    }
}
