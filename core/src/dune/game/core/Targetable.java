package dune.game.core;

import com.badlogic.gdx.math.Vector2;
import dune.game.core.units.TargetType;

public interface Targetable {
    Vector2 getPosition();
    TargetType getType();
}
