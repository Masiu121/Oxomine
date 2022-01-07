package com.oxology.oxomine.world;

import com.oxology.oxomine.world.objects.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private final int smothness = 30;
    private final int factor = 20;

    private String name;
    public int seed;

    private List<Chunk> chunks;

    private PerlinNoise perlin;
    private Random random;

    public World(String name, int seed) {
        this.name = name;
        this.seed = seed;

        chunks = new ArrayList<>();

        perlin = new PerlinNoise(seed);
        random = new Random(seed);
    }

    public World(String name) {
        this.name = name;
        this.seed = new Random().nextInt();

        chunks = new ArrayList<>();

        perlin = new PerlinNoise(seed);
        random = new Random(seed);
    }

    public void generateChunk(int x, int y) {
        Chunk chunk = new Chunk(new Location(this, (float) x * Chunk.CHUNK_SIZE, (float) y * Chunk.CHUNK_SIZE, 0));

        for(int i = (int) chunk.getLocation().getX(); i < (int) chunk.getLocation().getX()+16; i++) {

            //Generate height based on perlin noise
            int j = (int) (factor * perlin.generate(((float) i) / smothness) + 64);
            System.out.println(j);
            if(j >= chunk.getLocation().getY() && j < chunk.getLocation().getY()+16) {
                chunk.getBlock(i - (int) chunk.getLocation().getX(), j - (int) chunk.getLocation().getY(), 0).setType(Material.STONE);
                chunk.getBlock(i - (int) chunk.getLocation().getX(), j - (int) chunk.getLocation().getY(), 1).setType(Material.STONE);
            }

            //Fill everything under generated height by stone
            for(int z = 0; z < 16; z++) {
                if(j - (int) chunk.getLocation().getY() > z) {
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), z, 0).setType(Material.STONE);
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), z, 1).setType(Material.STONE);
                }
            }

            //Set everything under level 64 to be water
            for(int z = 0; z < 16; z++) {
                if(64 - (int) chunk.getLocation().getY() > z && chunk.getBlock(i - (int) chunk.getLocation().getX(), z, 1).getType() == Material.AIR) {
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), z, 0).setType(Material.WATER);
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), z, 1).setType(Material.WATER);
                }
            }

            //Set bedrock
            if(chunk.getY() == 0) {
                chunk.getBlock(i - (int) chunk.getLocation().getX(), 0, 0).setType(Material.BEDROCK);
                chunk.getBlock(i - (int) chunk.getLocation().getX(), 0, 1).setType(Material.BEDROCK);
                if(random.nextDouble() > 0.1) {
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 1, 0).setType(Material.BEDROCK);
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 1, 1).setType(Material.BEDROCK);
                }
                if(random.nextDouble() > 0.2) {
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 2, 0).setType(Material.BEDROCK);
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 2, 1).setType(Material.BEDROCK);
                }
                if(random.nextDouble() > 0.4) {
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 3, 0).setType(Material.BEDROCK);
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 3, 1).setType(Material.BEDROCK);
                }
                if(random.nextDouble() > 0.5) {
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 4, 0).setType(Material.BEDROCK);
                    chunk.getBlock(i - (int) chunk.getLocation().getX(), 4, 1).setType(Material.BEDROCK);
                }
            }
        }

        chunks.add(chunk);
    }

    public String getName() {
        return name;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public List<Chunk> getChunksTower(int x) {
        List<Chunk> chunksTower = new ArrayList<>();
        for(Chunk chunk : chunks) {
            if(chunk.getX() == x)
                chunksTower.add(chunk);
        }
        return chunksTower;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        this.perlin = new PerlinNoise(seed);
        this.random = new Random(seed);
    }

    enum Biome {
        MOUNTAINS(70, 200),
        OCEAN(20, 20),
        FOREST(30, 40),
        LAND(30, 20);

        int smoothness;
        int factor;
        int defaultHeight;

        Biome(int smoothness, int factor) {
            this.smoothness = smoothness;
            this.factor = factor;
        }

        public int getFactor() {
            return factor;
        }

        public int getSmoothness() {
            return smoothness;
        }
    }
}
