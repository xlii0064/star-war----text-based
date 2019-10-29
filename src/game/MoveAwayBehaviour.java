
package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class MoveAwayBehaviour implements ActionFactory {
	private Actor target;

	public MoveAwayBehaviour(Actor subject) {
		this.target = subject;
	}
	/**
	 * Choose a location that far from player.
	 * @param actor of Ninjas.
	 * @param the map that current run.
	 * @return ninjas move to a location that away from player.
	 * */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance > currentDistance) {	
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}