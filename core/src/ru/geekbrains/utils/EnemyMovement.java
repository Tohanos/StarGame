package ru.geekbrains.utils;

import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;

public class EnemyMovement {
    private EnemyMovementType movementType;
    private Vector2 v;
    private Vector2 v0;
    private Vector2 pos;
    private Rect worldBounds;
    private float upLimit;

    public EnemyMovement (Vector2 v0, Vector2 v, Vector2 pos, float upLimit, Rect worldBounds) {
        movementType = EnemyMovementType.APPEARING;
        this.v0 = v0;
        this.v = v;
        this.pos = pos;
        this.upLimit = upLimit;
        this.worldBounds = worldBounds;


    }

    public void next() {
        switch (movementType) {
            case APPEARING:
                v.set(0, -0.2f);
                if (pos.y <= upLimit) {
                    movementType = EnemyMovementType.FIGHTING;
                }
                break;
            case FIGHTING:
                v.set(this.v0);
                //TODO add some logic for fighting movement

                //movementType = EnemyMovementType.DYING;
                break;
            case DYING:
                //TODO add some loogic for dying movement
                v.set(0, 0);
                break;
        }
    }

    public void setV0(Vector2 v) {
        this.v = v;
    }
}
