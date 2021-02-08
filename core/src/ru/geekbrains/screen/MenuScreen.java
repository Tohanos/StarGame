package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture background;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 imgPos;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("black3.jpg");
        touch = new Vector2();
        v = new Vector2(0, 0);
        imgPos = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.6f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, imgPos.x, imgPos.y);
        batch.end();


        imgPos.add(v);
        if (imgPos.epsilonEquals(touch, 0.5f)) {
            v.set(0 , 0);
        }
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = touch.cpy();
        v.sub(imgPos);
        v.nor();
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                touch.y += 10;
                break;
            case Input.Keys.DOWN:
                touch.y -= 10;
                break;
            case Input.Keys.RIGHT:
                touch.x += 10;
                break;
            case Input.Keys.LEFT:
                touch.x -= 10;
                break;
        }
        return false;
    }
}
