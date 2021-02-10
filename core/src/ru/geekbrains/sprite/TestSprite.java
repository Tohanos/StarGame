package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class TestSprite extends Sprite {

    private Vector2 v;
    private Vector2 touch;
    private Vector2 tmp;

    public TestSprite(Texture region) {
        super(new TextureRegion(region));
        v = new Vector2(0, 0);
        tmp = new Vector2();
        touch = new Vector2();
        setHeightProportion(0.2f);

    }

    @Override
    public void draw(SpriteBatch batch) {
        tmp.set(touch);
        if (tmp.sub(pos).len() > v.len()) {
            pos.add(v);
        } else {
            pos.set(touch);
        }

        super.draw(batch);

    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
    }

    @Override

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        v = touch.cpy();
        v.sub(pos);
        v.nor();
        v.scl(0.001f);
        System.out.println("SpeedX =" + v.x + "SpeedY" + v.y);

        return super.touchDown(touch, pointer, button);
    }
}
