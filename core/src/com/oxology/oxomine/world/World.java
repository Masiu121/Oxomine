package com.oxology.oxomine.world;

import com.oxology.oxomine.world.objects.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private final int smothness = 10;
    private final int factor = 10;

    private String name;
    private int seed;

    private List<Chunk> chunks;

    PerlinNoise perlin;

    public World(String name, int seed) {
        this.name = name;
        this.seed = seed;
        perlin = new PerlinNoise(seed);
        chunks = new ArrayList<>();
    }

    public World(String name) {
        this.name = name;
        this.seed = new Random().nextInt();
        perlin = new PerlinNoise(seed);
        chunks = new ArrayList<>();
    }

    public Chunk generateChunk(int x, int y) {
        PerlinNoise perlin = new PerlinNoise(seed);
        Chunk chunk = new Chunk(new Location(this, (float) x*Chunk.CHUNK_SIZE, (float) y*Chunk.CHUNK_SIZE, 0));
        for(int i = 0; i < Chunk.CHUNK_SIZE; i++) {
            float j = factor* perlin.generate(((float) i)/smothness);
            for(int k = 0; k < j; k++) {
                chunk.getBlock(k, i, 0).setType(Material.STONE);
                chunk.getBlock(k, i, 1).setType(Material.STONE);
            }
        }
        return chunk;
    }
}
