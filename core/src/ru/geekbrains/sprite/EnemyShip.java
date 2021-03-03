package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.utils.EnemyMovement;
import ru.geekbrains.utils.EnemyMovementType;

public class EnemyShip extends Ship {

    private EnemyMovement movement;
    private Vector2 vMod;

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.sound = sound;

        v = new Vector2();
        v0 = new Vector2();
        vMod = new Vector2();
        bulletPos = new Vector2();
        bulletV = new Vector2();
        movement = new EnemyMovement(v0, v, pos, worldBounds, vMod);
    }

    @Override
    public void update(float delta) {
//        if (getTop() > worldBounds.getTop()) {
//            reloadTimer = reloadInterval * 0.8f;
//        } else if (!v.equals(v0)) {
//            v.set(v0);
//        }
        movement.next(delta);
        super.update(delta);
        bulletPos.set(pos.x, pos.y - getHalfHeight());
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
        if (isDestroyed()) {
            movement.setMovementType(EnemyMovementType.DYING);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            Vector2 vMod,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            float reloadInterval,
            float height,
            int hp,
            EnemyMovementType movementType
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.vMod.set(vMod);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
        movement.setMovementType(movementType);
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );
    }
}