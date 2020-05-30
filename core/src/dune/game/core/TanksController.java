package dune.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TanksController extends ObjectPool<Tank> {
    private GameController gc;

    @Override
    protected Tank newObject() {
        return new Tank(gc);
    }

    public TanksController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void setup(float x, float y, Tank.Owner ownerType) {
        Tank t = getActiveElement();
        t.setup(ownerType, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int curActiveIndex = -1;
            for (int i = 0; i < activeList.size(); i++) {
                activeList.get(i).setActive(false);

                if ((Math.abs(activeList.get(i).getPosition().x - Gdx.input.getX()) < 40) &&
                        (Math.abs(activeList.get(i).getPosition().y - (720 - Gdx.input.getY())) < 40)) {
                    if (curActiveIndex == -1 ) {curActiveIndex = i;}        // если несколько танков находятся в одной точке, только один станет активным. Остальные деактивируем
                    if (curActiveIndex == i ) {activeList.get(i).setActive(true);}
                }
            }
        }
        checkPool();
    }
}
