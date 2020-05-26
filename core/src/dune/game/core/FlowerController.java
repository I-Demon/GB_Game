package dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class FlowerController extends ObjectPool<Flower> {
    private GameController gc;
    private TextureRegion flowerTexture;
    private final int flowerCount = 10;

    @Override
    protected Flower newObject() {
        return new Flower(gc);
    }

    public FlowerController(GameController gc) {
        this.gc = gc;
        this.flowerTexture = Assets.getInstance().getAtlas().findRegion("flower");
        for (int i = 0; i < flowerCount; i++) {
            this.getActiveElement();
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void update(float dt, Vector2 tankPosition) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt, tankPosition);
        }
        checkPool();
    }
}
