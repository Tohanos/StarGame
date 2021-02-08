package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture background;

	int x, y, screenWidth, screenHeight, dirX, dirY;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		background = new Texture("black3.jpg");
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		dirX = 1;
		dirY = 1;


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		x += dirX;
		y += dirY;
		if ((x > (screenWidth - img.getWidth())) || (x < 0)) dirX = -dirX;
		if ((y > (screenHeight - img.getHeight())) || (y < 0)) dirY = -dirY;
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
