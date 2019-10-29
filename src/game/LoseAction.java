package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
public class LoseAction extends Action{
	
	private Actor actor;


	public LoseAction(Actor actor) {

	/**
	 * This is the constructor of this class. It would initialize the player to perform this task
	 * @param actor: the actor to perform this task
	 */

		this.actor = actor;
	}
	/**
	 * This method allowed player to quit the game
	 * @param actor: The actor to quit game
	 * @param map: the current game map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(this.actor);
		return "Player loses.";
	}
	@Override
	public String menuDescription(Actor actor) {
		return "Lose Game";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
