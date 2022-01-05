package com.oxology.oxomine.world;

public class Location {
    private World world;
    private float x;
    private float y;
    private float z;

    public Location(World world, float x, float y, float z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public World getWorld() {
        return world;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public boolean compare(Location location) {
        return (location.world == this.world && location.getX() == this.x && location.getY() == this.y && location.getZ() == this.z);
    }
}
