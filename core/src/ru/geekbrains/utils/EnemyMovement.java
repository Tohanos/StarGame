package ru.geekbrains.utils;

import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;

public class EnemyMovement {
    private EnemyMovementType movementType;
    private Vector2 v;
    private Vector2 v0;
    private Vector2 vMod;
    private Vector2 pos;
    private Rect worldBounds;

    private final float UP_LIMIT = 0.1f;

    public EnemyMovement (Vector2 v0, Vector2 v, Vector2 pos, Rect worldBounds, Vector2 vMod) {
        movementType = EnemyMovementType.APPEARING;
        this.v0 = v0;
        this.v = v;
        this.vMod = vMod;
        this.pos = pos;
        this.worldBounds = worldBounds;


    }

    public void next(float delta) {
        switch (movementType) {
            case APPEARING:
                v.set(0, -0.2f);
                if (pos.y <= worldBounds.getTop() - UP_LIMIT) {
                    movementType = EnemyMovementType.FIGHTING;
                    v.set(v0);
                }
                break;
            case FIGHTING:
                v.mulAdd(vMod, delta);
                if(pos.x > worldBounds.getRight()) {
                    pos.x = worldBounds.getRight();
                    v.x = - v.x;
                    vMod.x = - vMod.x;
                }

                if(pos.x < worldBounds.getLeft()) {
                    pos.x = worldBounds.getLeft();
                    v.x = - v.x;
                    vMod.x = - vMod.x;
                }

                break;
            case DYING:

                v.set(0, 0);
                break;
        }
    }

    public void setV0(Vector2 v) {
        this.v = v;
    }

    public EnemyMovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(EnemyMovementType movementType) {
        this.movementType = movementType;
    }
}
