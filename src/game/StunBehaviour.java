package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;
//Demo
public class StunBehaviour implements ActionFactory{
	private StunnedPlayer target;
	/**
	 * The constructor of the StunBehaviour class. 
	 * @param subject: The target subject of the actor who performs this class
	 */
	public StunBehaviour(StunnedPlayer subject) {
		this.target = subject;
	}

	/**
	 * The function is to justify which kind of action should the actor perform and return the corresponding action
	 * @param actor: The actor to execute this behavior
	 * @param map: The current game map
	 * @return: return a SkipTurnAction() if the actor cannot see the target. Else return the moveAwayBehaviour();
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (!see(actor,map)) {
			return new SkipTurnAction();
		}
		int randNum= (int) (Math.random()*2+1);
		if (!(target.getStatus()) ||(target.getStunedTurns()==2 && target.getStatus())) {
			if (randNum==1) {
				target.setStatus(true);
				}
		}
		return new MoveAwayBehaviour(target).getAction(actor, map);
	}
	/**
	 * This function is to decide whether the actor can see the target or not.
	 * @param actor: The actor to perform this function
	 * @param map: The current game map
	 * @return: return true if the actor can see its target. Else return false
	 */
	public boolean see(Actor actor, GameMap map) {
		boolean see=false;
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		if (distance(here,there)<=5) {
			see=true;
		}
		return see;
	}
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
