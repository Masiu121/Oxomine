package com.oxology.oxomine.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Material {
    AIR(),
    BEDROCK(new Texture(Gdx.files.internal("Bedrock00.png"))),
    STONE(new Texture(Gdx.files.internal("Stone00.png"))),
    GRASS(new Texture(Gdx.files.internal("Grass00.png"))),
    DIRT(new Texture(Gdx.files.internal("Dirt00.png"))),
    WATER_TOP(new Texture(Gdx.files.internal("Water00.png"))),
    WATER(new Texture(Gdx.files.internal("Water01.png")));

    Texture texture;
    Material(Texture texture) {
        this.texture = texture;
    }

    Material() {}

    public Texture getTexture() {
        return texture;
    }
}
