package dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion textures;
    private float angle;
    private float speed;

    public Projectile(TextureAtlas atlas, float x, float y) {
        this.position = new Vector2(x, y);
        this.textures = new TextureRegion(atlas.findRegion("bullet"));
        this.speed = 300.0f;
        this.velocity =   position.cpy(); //.add(0,10); // new Vector2(10, 10);
    }

    public void setup(Vector2 startPosition, float angle) {
        velocity.set(speed * MathUtils.cosDeg(angle), speed  * MathUtils.sinDeg(angle));

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void update(float dt) {
         position.x += velocity.x * dt;
         position.y += velocity.y * dt;
         position.mulAdd(velocity, dt);
    }

    public void render(SpriteBatch batch) {
        batch.draw(textures, position.x - 8, position.y - 8, 8, 8, 16, 16, 1, 1, angle);
    }
}
