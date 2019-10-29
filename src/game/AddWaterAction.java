package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class AddWaterAction extends Action{
	
	
	private Actor actor;
	private WaterItem w = new WaterItem();
	int i =0;
	/**
	 * This is the constructor of this class. It would initialize the player to perform this task
	 * @param actor: the actor to perform this task
	 */
	public AddWaterAction(Actor actor) {
		this.actor = actor;
	}
	/**
	 * This method check player water pistol has water or not.
	 * @param actor: The actor hold water pistol
	 * @param map: the current game map
	 * @return: return a statement if the don't have water in water pistol. Otherwise return add water failed message.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		boolean b = false;
		for (int i=0;i<actor.getInventory().size();i++) {
			if(actor.getInventory().get(i) instanceof WaterPistolItem) {
				b = true;
			}
		}
		if(b) {
			for (int i=0;i<actor.getInventory().size();i++) {
			if(actor.getInventory().get(i) instanceof WaterItem) {
				return "Add Water Failure";
			}
			}
			}
		actor.addItemToInventory(w);
		
		return "Add Water Successful";
	}
	/**
	 * This method is to decide which statement would be shown on the game console
	 * @param actor : the actor have water pistol
	 * @return : return a statement that add water to water pistol
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor+" add water to water pistol ";
	}
	
	@Override
	public String hotKey() {
		return "";
	}
}
