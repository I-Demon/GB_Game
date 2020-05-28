package dune.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameController {
    private BattleMap map;
    private ProjectilesController projectilesController;
    private Tank tank;
    private FlowerController flowerController;


    public Tank getTank() {
        return tank;
    }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public FlowerController getFlowerController() { return flowerController;  }

    public BattleMap getMap() {
        return map;
    }

    // Инициализация игровой логики
    public GameController() {
        Assets.getInstance().loadAssets();
        this.map = new BattleMap();
        this.projectilesController = new ProjectilesController(this);
        this.tank = new Tank(this, 200, 200);;
        this.flowerController = new FlowerController(this);
    }

    public void update(float dt) {
        tank.update(dt);
        projectilesController.update(dt);
        flowerController.update(dt, tank.getPosition());
        checkCollisions(dt);
    }

    public void checkCollisions(float dt) {

    }
}
