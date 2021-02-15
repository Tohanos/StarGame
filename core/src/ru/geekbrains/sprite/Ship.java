package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {

    private static final float HEIGHT = 0.3f;
    private static final Vector2 initPos = new Vector2(0, -.35f);

    private Vector2 v;

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship").split(
                atlas.findRegion("main_ship").getRegionWidth() / 2,
                atlas.findRegion("main_ship").getRegionHeight())[0][1]);
        setHeightProportion(HEIGHT);
        pos.set(initPos);
        v = new Vector2(0, 0);
    }

    public void keyControl(int keyPressed) {
        switch (keyPressed) {
            case Input.Keys.UP:
                v.add(0, 0.01f);
                break;

            case Input.Keys.DOWN:
                v.add(0, -0.01f);
                break;

            case Input.Keys.LEFT:
                angle += 1f;
                v.rotateDeg(angle);
                break;

            case Input.Keys.RIGHT:
                angle -= 1f;
                v.rotateDeg(angle);
                break;
        }
    }



    @Override
    public void update(float delta) {

        pos.mulAdd(v, delta);


    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
    }




}
