package game;

import engine.*;


public class FollowBehaviour implements ActionFactory {

	private Actor target;


	public FollowBehaviour(Actor subject) {
		this.target = subject;

	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		GameMap playersMap = WorldAccessible.worlds.get(0).getActorLocations().locationOf(target).map();
		if(map.equals(playersMap)){
			Location here = map.locationOf(actor);
			Location there = map.locationOf(target);

			int currentDistance = distance(here, there);
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					int newDistance = distance(destination, there);
					if (newDistance < currentDistance) {
						return new MoveActorAction(destination, exit.getName());
					}
				}
			}
		}

		return null;
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}