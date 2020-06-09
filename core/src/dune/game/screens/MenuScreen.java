package dune.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import dune.game.core.Assets;
import dune.game.core.GameController;
import dune.game.core.PlayerLogic;
import dune.game.core.WorldRenderer;
import dune.game.core.gui.GuiPlayerInfo;
import dune.game.core.units.AbstractUnit;

//

public class MenuScreen extends AbstractScreen {
    private Stage stage;
    private GameController gameController;
    private WorldRenderer worldRenderer;


    public Stage getStage() {
        return stage;
    }

    public MenuScreen(SpriteBatch batch) {
        super(batch);


    }

    @Override
    public void show() {
        //this.gameController = new GameController();
        //this.worldRenderer = new WorldRenderer(batch, gameController);
        createGui();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.4f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void update(float dt) {
       /*
        if (Gdx.input.justTouched()) {
            ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
        }

        */

       stage.act(dt);

    }



    public void createGui() {
        //dshu Рисуем кнопки на экране меню для начала игры и выхода из игры

        stage = new Stage(ScreenManager.getInstance().getViewport(), ScreenManager.getInstance().getBatch());

        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());
        BitmapFont font14 = Assets.getInstance().getAssetManager().get("fonts/font14.ttf");

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                skin.getDrawable("smButton"), null, null, font14);
        final TextButton StartBtn = new TextButton("Start Game", textButtonStyle);
        StartBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
            }
        });

        final TextButton exitBtn = new TextButton("Exit", textButtonStyle);
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        Group menuGroup = new Group();
        StartBtn.setPosition(0, 50);
        exitBtn.setPosition(0, 0);
        menuGroup.addActor(StartBtn);
        menuGroup.addActor(exitBtn);
        menuGroup.setPosition(600, 400);

        stage.addActor(menuGroup);

        skin.dispose();
    }

    @Override
    public void dispose() {
    }
}
