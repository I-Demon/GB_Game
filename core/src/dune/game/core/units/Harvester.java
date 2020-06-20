package dune.game.core.units;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import dune.game.core.units.types.Owner;
import dune.game.core.*;
import dune.game.core.interfaces.Targetable;
import dune.game.core.units.types.UnitType;
import dune.game.core.users_logic.BaseLogic;
import dune.game.screens.utils.Assets;

//dshu правки
public class Harvester extends AbstractUnit {

    private BitmapFont font14;
    private String txt;
    private boolean needClearContainer;


    public boolean isNeedClearContainer() {
        return needClearContainer;
    }

    public void setNeedClearContainer(boolean needClearContainer) {
        this.needClearContainer = needClearContainer;
    }

    public Harvester(GameController gc) {
        super(gc);
        this.textures = Assets.getInstance().getAtlas().findRegion("tankcore").split(64, 64)[0];
        this.weaponTexture = Assets.getInstance().getAtlas().findRegion("harvester");
        this.containerCapacity = 10;
        this.minDstToActiveTarget = 5.0f;
        this.speed = 120.0f;
        this.weapon = new Weapon(4.0f, 1);
        this.hpMax = 500;
        this.unitType = UnitType.HARVESTER;

        this.font14 = Assets.getInstance().getAssetManager().get("fonts/font14.ttf");


    }

    @Override
    public void setup(BaseLogic baseLogic, float x, float y) {
        this.position.set(x, y);
        this.baseLogic = baseLogic;
        this.ownerType = baseLogic.getOwnerType();
        this.hp = this.hpMax;
        this.destination = new Vector2(position);
    }



    public void updateWeapon(float dt) {
        if (gc.getMap().getResourceCount(position) > 0) {
            int result = weapon.use(dt);
            if (result > -1) {
                container += gc.getMap().harvestResource(position, result);
                if (container > containerCapacity) {
                    container = containerCapacity;
                }
            }
        } else {
            weapon.reset();
        }
    }

    @Override
    public void commandAttack(Targetable target) {
        commandMoveTo(target.getPosition());
    }

    @Override
    public void renderGui(SpriteBatch batch) {
        super.renderGui(batch);
        if (weapon.getUsageTimePercentage() > 0.0f) {
            batch.setColor(0.2f, 0.2f, 0.0f, 1.0f);
            batch.draw(progressbarTexture, position.x - 32, position.y + 22, 64, 8);
            batch.setColor(1.0f, 1.0f, 0.0f, 1.0f);
            batch.draw(progressbarTexture, position.x - 30, position.y + 24, 60 * weapon.getUsageTimePercentage(), 4);
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }

        if (this.getOwnerType() == Owner.AI) {
            txt =   Integer.toString((int) (((float) container / containerCapacity) * 100)) + "%";  // "Pos: X= " + position.x + " Y= " + position.y + "  DST: X="  + destination.x + " Y= " + destination.y;  // Integer.toString((int) (((float) container / containerCapacity) * 100)) + "%";
            font14.draw(batch, txt, position.x - 32, position.y + 70, 100, 1, false);
        }


    }

    public void update(float dt) {
        super.update(dt);
        Building b = gc.getMap().getBuildingEntrance(getCellX(), getCellY());
        if (b != null && b.getType() == Building.Type.STOCK && b.getOwnerLogic() == this.baseLogic) {
            baseLogic.addMoney(container * 100);
            container = 0;
        }
    }
}