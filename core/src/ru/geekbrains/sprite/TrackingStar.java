package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class TrackingStar extends Star {

    private final Vector2 sumV;
    private final Vector2 trackingV;
    private float scaleFactor;
    private final float SCALE_LIMIT = 1.5f;

    public TrackingStar(TextureAtlas atlas) {
        super(atlas);
        sumV = new Vector2();
        trackingV = new Vector2();
        scale = 1f - 0.5f * (float)Math.random();
        scaleFactor = 0.01f * (1f - 0.5f * (float)Math.random());
    }

    public void update(float delta, float xv) {
        trackingV.set(xv, 0);
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        pos.mulAdd(sumV, delta);
        checkBounds();
        scale += scaleFactor;
        if ((scale > SCALE_LIMIT) || (scale < 0)) {
            scaleFactor = - scaleFactor;
        }
    }
}