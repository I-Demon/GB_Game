package dune.game.core.users_logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import dune.game.core.BattleMap;
import dune.game.core.GameController;
import dune.game.core.units.AbstractUnit;
import dune.game.core.units.BattleTank;
import dune.game.core.units.Harvester;
import dune.game.core.units.types.Owner;
import dune.game.core.units.types.UnitType;

import java.util.ArrayList;
import java.util.List;

public class AiLogic extends BaseLogic {
    private float timer;

    private List<BattleTank> tmpAiBattleTanks;
    private List<Harvester> tmpPlayerHarvesters;
    private List<BattleTank> tmpPlayerBattleTanks;
    private List<Harvester>  tmpAiHarvesters;

    public AiLogic(GameController gc) {
        this.gc = gc;
        this.money = 1000;
        this.unitsCount = 10;
        this.unitsMaxCount = 100;
        this.ownerType = Owner.AI;
        this.tmpAiBattleTanks = new ArrayList<>();
        this.tmpAiHarvesters = new ArrayList<>();
        this.tmpPlayerHarvesters = new ArrayList<>();
        this.tmpPlayerBattleTanks = new ArrayList<>();
        this.timer = 10000.0f;
    }

    public void update(float dt) {
        timer += dt;
        if (timer > 2.0f) {
            timer = 0.0f;
            gc.getUnitsController().collectTanks(tmpAiBattleTanks, gc.getUnitsController().getAiUnits(), UnitType.BATTLE_TANK);
            gc.getUnitsController().collectTanks(tmpAiHarvesters, gc.getUnitsController().getAiUnits(), UnitType.HARVESTER);
            gc.getUnitsController().collectTanks(tmpPlayerHarvesters, gc.getUnitsController().getPlayerUnits(), UnitType.HARVESTER);
            gc.getUnitsController().collectTanks(tmpPlayerBattleTanks, gc.getUnitsController().getPlayerUnits(), UnitType.BATTLE_TANK);
            for (int i = 0; i < tmpAiBattleTanks.size(); i++) {
                BattleTank aiBattleTank = tmpAiBattleTanks.get(i);
                aiBattleTank.commandAttack(findNearestTarget(aiBattleTank, tmpPlayerBattleTanks));
            }

            for (int i = 0; i < tmpAiHarvesters.size(); i++) {
                Harvester aiHarvester = tmpAiHarvesters.get(i);
                aiHarvester.moveBy(findNearestResourse(tmpAiHarvesters));
            }
        }
    }

    public <T extends AbstractUnit> T findNearestTarget(AbstractUnit currentTank, List<T> possibleTargetList) {
        T target = null;
        float minDist = 1000000.0f;
        for (int i = 0; i < possibleTargetList.size(); i++) {
            T possibleTarget = possibleTargetList.get(i);
            float currentDst = currentTank.getPosition().dst(possibleTarget.getPosition());
            if (currentDst < minDist) {
                target = possibleTarget;
                minDist = currentDst;
            }
        }
        return target;
    }


    public Vector2 findNearestResourse(List<Harvester> possibleTargetList) {
        Vector2 target = new Vector2();
        Vector2 tmp = new Vector2();
        float minWeightRes = 1000000.0f;
        //float weightRes;

        //  Cell tmpCell;
        int cellSize = gc.getMap().CELL_SIZE;



        // dshu Begin
        for (int k = 0; k < possibleTargetList.size(); k++) {
            AbstractUnit possibleTarget = possibleTargetList.get(k);

            for (int i = 0; i < gc.getMap().COLUMNS_COUNT; i++) {
                for (int j = 0; j < gc.getMap().ROWS_COUNT; j++) {
                    tmp.set(i * cellSize - (cellSize/2), j * cellSize - (cellSize/2));
                    float currentDst = possibleTarget.getPosition().dst(tmp);
                    float curweightRes = gc.getMap().getResourceCount(tmp) * currentDst;

                    if (curweightRes < minWeightRes) {
                        target.set(tmp);
                        minWeightRes = curweightRes;
                    }
                }
                }
            // dshu End
        }
        return target;
    }
}
