package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.EnemyShip;

public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.15f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 2f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 10;

    private final Vector2 enemySmallBulletV = new Vector2(0, -0.3f);
    private final Vector2 enemyMediumBulletV = new Vector2(0, -0.25f);
    private final Vector2 enemyBigBulletV = new Vector2(0, -0.2f);
    private final Vector2 enemySmallV = new Vector2(0, -0.2f);
    private final Vector2 enemyMediumV = new Vector2(0, -0.03f);
    private final Vector2 enemyBigV = new Vector2(0, -0.005f);
    private final Vector2 enemySmallVMod = new Vector2(0.06f, 0);
    private final Vector2 enemyMediumVMod = new Vector2(0.04f, 0);
    private final Vector2 enemyBigVMod = new Vector2(-0.03f, 0);

    private TextureRegion[] enemySmallRegions;
    private TextureRegion[] enemyMediumRegions;
    private TextureRegion[] enemyBigRegions;

    private Rect worldBounds;
    private TextureRegion bulletRegion;
    private EnemyPool enemyPool;

    private float generateInterval = 4f;
    private float generateTimer;

    private int level;

    public EnemyEmitter(TextureAtlas atlas, Rect worldBounds, EnemyPool enemyPool) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;

        enemySmallRegions = Regions.split(atlas.findRegion("enemy0"), 1, 2, 2);
        enemyMediumRegions = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        enemyBigRegions = Regions.split(atlas.findRegion("enemy2"), 1, 2, 2);

        bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generate(float delta, int frags) {
        level = frags / 10 + 1;
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyPool.obtain();
            float enemyType = (float) Math.random();
            if (enemyType < 0.5f) {
                enemyShip.set(
                        enemySmallRegions,
                        enemySmallV,
                        enemySmallVMod,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        enemySmallBulletV,
                        ENEMY_SMALL_BULLET_DAMAGE * level,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        EnemyMovementType.APPEARING
                );
            } else if (enemyType < 0.8f) {
                enemyShip.set(
                        enemyMediumRegions,
                        enemyMediumV,
                        enemyMediumVMod,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        enemyMediumBulletV,
                        ENEMY_MEDIUM_BULLET_DAMAGE * level,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        EnemyMovementType.APPEARING
                );
            } else {
                enemyShip.set(
                        enemyBigRegions,
                        enemyBigV,
                        enemyBigVMod,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        enemyBigBulletV,
                        ENEMY_BIG_BULLET_DAMAGE * level,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        EnemyMovementType.APPEARING
                );
            }
            enemyShip.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + enemyShip.getHalfWidth(),
                    worldBounds.getRight() - enemyShip.getHalfWidth()
            );
            enemyShip.setBottom(worldBounds.getTop());
        }
    }

    public int getLevel() {
        return level;
    }
}