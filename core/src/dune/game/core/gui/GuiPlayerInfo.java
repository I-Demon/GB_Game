package dune.game.core.gui;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.StringBuilder;
import dune.game.core.PlayerLogic;

public class GuiPlayerInfo extends Group {
    private PlayerLogic playerLogic;
    private Label money;
    private Label unitsCount;
    private Label score;        // счет собранных ресурсов
    private StringBuilder tmpSB;

    public GuiPlayerInfo(PlayerLogic playerLogic, Skin skin) {
        this.playerLogic = playerLogic;
        this.money = new Label("", skin, "simpleLabel");
        this.unitsCount = new Label("", skin, "simpleLabel");
        this.money.setPosition(10, 10);
        this.unitsCount.setPosition(210, 10);

        this.score = new Label("", skin, "simpleLabel");  //dshu вывод инфо о текущем счете
        this.score.setPosition(310,10);
        this.addActor(money);
        this.addActor(unitsCount);
        this.addActor(score);
        this.tmpSB = new StringBuilder();
    }

    public void update(float dt) {
        tmpSB.clear();
        tmpSB.append("MINERALS: ").append(playerLogic.getMoney());
        money.setText(tmpSB);
        tmpSB.clear();
        tmpSB.append("UNITS: ").append(playerLogic.getUnitsCount()).append(" / ").append(playerLogic.getUnitsMaxCount());
        unitsCount.setText(tmpSB);


        // dshu выводим информацию о счете
        tmpSB.clear();
        tmpSB.append("SCORE: ").append(playerLogic.getScore());
        unitsCount.setText(tmpSB);
    }
}
