package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class QuitGameAction extends Action{
	
	private Actor actor;


	public QuitGameAction(Actor actor) {

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
		return "Game Over.";
	}
	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
