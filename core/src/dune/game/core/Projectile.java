package dune.game.core;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private Texture textures;
    private float angle;
    private float speed;

    public Projectile(TextureAtlas atlas, float x, float y) {
        this.position = new Vector2(x, y);
        this.textures = new TextureRegion(atlas.findRegion("bullet")).split(64, 64)[0];
        this.speed = 300.0f;
        this.timePerFrame = 0.08f;
    }

    public void setup(Vector2 startPosition, float angle) {
        velocity.set(100.0f * MathUtils.cosDeg(angle), 0.0f);

    }

    public void update(float dt) {
        // position.x += velocity.x * dt;
        // position.y += velocity.y * dt;
        // position.mulAdd(velocity, dt);
    }
}

