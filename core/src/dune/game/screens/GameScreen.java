package dune.game.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dune.game.core.GameController;
import dune.game.core.WorldRenderer;

public class GameScreen extends AbstractScreen {
    private SpriteBatch batch;
    private GameController gameController;
    private WorldRenderer worldRenderer;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        this.gameController = new GameController();
        this.worldRenderer = new WorldRenderer(batch, gameController);
    }

    @Override
    public void render(float delta) {
        gameController.update(delta);
        worldRenderer.render();
    }

    @Override
    public void dispose() {
    }
}
