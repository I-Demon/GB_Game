package dune.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameController {
    private BattleMap map;
    private Tank tank;

    public Tank getTank() {
        return tank;
    }

    public BattleMap getMap() {
        return map;
    }

    // РРЅРёС†РёР°Р»РёР·Р°С†РёСЏ РёРіСЂРѕРІРѕР№ Р»РѕРіРёРєРё
    public GameController() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("game.pack"));
        this.map = new BattleMap(atlas);
        this.tank = new Tank(atlas,200, 200);;
    }

    public void update(float dt) {
        tank.update(dt);
        checkCollisions(dt);
    }

    public void checkCollisions(float dt) {

    }
}