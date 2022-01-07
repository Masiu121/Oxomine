package com.oxology.oxomine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oxology.oxomine.world.Chunk;
import com.oxology.oxomine.world.World;
import com.oxology.oxomine.world.objects.Block;
import com.oxology.oxomine.world.objects.Material;

import java.util.Random;

public class Oxomine extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	OrthographicCamera camera;
	Random random;

	float height;
	float width;

	@Override
	public void create () {
		random = new Random();

		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();

		camera = new OrthographicCamera(256, 256 * (height/width));
		camera.position.set(0, 0, 0);
		camera.update();

		batch = new SpriteBatch();
		world = new World("world");
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Input.Keys.G)) {
			world.getChunks().clear();
			world.setSeed(random.nextInt());
			for(int i = -10; i <= 10; i++) {
				for(int j = 0; j <= 16; j++) {
					world.generateChunk(i, j);
				}
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W))
			camera.translate(0, 5);
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			camera.translate(0, -5);
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			camera.translate(5, 0);
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			camera.translate(-5, 0);

		if(Gdx.input.isKeyPressed(Input.Keys.Q))
			camera.zoom += 2;
		if(Gdx.input.isKeyPressed(Input.Keys.E))
			camera.zoom -= 2;

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		for(Chunk chunk : world.getChunks()) {
			for(Block block : chunk.getBlocks()) {
				if(block.getType() != Material.AIR) {
					batch.draw(block.getType().getTexture(), chunk.getX()*(Chunk.CHUNK_SIZE)*16+block.getLocation().getX()*16-128, chunk.getY()*(Chunk.CHUNK_SIZE-1)*16+block.getLocation().getY()*16-72, 16, 16);
				}
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
