package dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Flower extends GameObject implements Poolable {
    private TextureRegion texture;
    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Flower(GameController gc) {
        super(gc);
        this.texture = Assets.getInstance().getAtlas().findRegion("flower");
        position = new Vector2((float) Math.random() * 1200 + 40, (float) Math.random() * 640 + 40);
        this.active = true;
    }

    public void setup() {
       // this.texture = texture;
        //this.position.set(startPosition);
        //this.active = true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 16, position.y - 16);
    }

    public void update(float dt, Vector2 tankPosition) {
        if ((Math.abs(position.x - tankPosition.x) < 46) &&  (Math.abs(position.y - tankPosition.y) < 46)) {
            deactivate();
        }
    }

}
