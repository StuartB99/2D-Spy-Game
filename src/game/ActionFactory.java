package game;

import engine.Action;
import engine.Actor;
import engine.GameMap;

public interface ActionFactory {
	Action getAction(Actor actor, GameMap map);
}
