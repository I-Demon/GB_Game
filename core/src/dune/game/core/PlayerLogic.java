package dune.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dune.game.core.units.AbstractUnit;
import dune.game.core.units.BattleTank;
import dune.game.core.units.Owner;
import dune.game.core.units.UnitType;

//

public class PlayerLogic {
    private GameController gc;
    private int money;
    private int unitsCount;
    private int unitsMaxCount;
    private int score;  // dshu для подсчета собранных ресурсов

    public int getMoney() {
        return money;
    }

    public int getUnitsCount() {
        return unitsCount;
    }

    public int getScore() {
        return score;
    }

    public int getUnitsMaxCount() {
        return unitsMaxCount;
    }

    public PlayerLogic(GameController gc) {
        this.gc = gc;
        this.money = 1000;
        this.unitsCount = 10;
        this.unitsMaxCount = 100;
    }

    public void update(float dt) {
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            for (int i = 0; i < gc.getSelectedUnits().size(); i++) {
                AbstractUnit u = gc.getSelectedUnits().get(i);
                if (u.getOwnerType() == Owner.PLAYER) {
                    unitProcessing(u);
                }
            }
        }
    }

    public void unitProcessing(AbstractUnit unit) {
        if (unit.getUnitType() == UnitType.HARVESTER) {


            //dshu {Begin}  Если харвестер приехал в точку сброса ресурсов и у него полный контейнер, то освобождаем контейнер и увеличиваем счет игрока
            if ((unit.getCellX() <= 4) && (unit.getCellY() <= 2) && (unit.getContainer() == unit.getContainerCapacity()) ) {
              score += unit.getContainer();
              unit.setContainer(0);
            }
            //dshu {End}


            unit.commandMoveTo(gc.getMouse());
            return;
        }
        if (unit.getUnitType() == UnitType.BATTLE_TANK) {
            AbstractUnit aiUnit = gc.getUnitsController().getNearestAiUnit(gc.getMouse());
            if (aiUnit == null) {
                unit.commandMoveTo(gc.getMouse());
            } else {
                unit.commandAttack(aiUnit);
            }
        }
    }
}
