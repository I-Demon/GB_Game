package dune.game.core.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dune.game.core.GameController;
import dune.game.core.utils.ObjectPool;
import dune.game.core.units.BattleTank;
import dune.game.core.users_logic.BaseLogic;

public class BattleTanksController extends ObjectPool<BattleTank> {
    private GameController gc;

    @Override
    protected BattleTank newObject() {
        return new BattleTank(gc);
    }

    public BattleTanksController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void setup(float x, float y, BaseLogic baseLogic) {
        BattleTank t = activateObject();
        t.setup(baseLogic, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
