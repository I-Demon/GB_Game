package dune.game.core.interfaces;

import com.badlogic.gdx.math.Vector2;
import dune.game.core.units.types.TargetType;

public interface Targetable {
    Vector2 getPosition();
    TargetType getType();
}
